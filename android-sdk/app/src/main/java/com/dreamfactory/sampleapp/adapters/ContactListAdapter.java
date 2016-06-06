package com.dreamfactory.sampleapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ParcelableContactRecord;
import com.dreamfactory.sampleapp.ui.ContactViewActivity;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

import dfapi.ApiException;

public class ContactListAdapter extends BaseAdapter{
    protected Activity context;
    public List<ContactRecord> mRecordsList;


    protected BitSet mainSet; // track where section headers are in the list view
    protected BitSet compareSet; // declared up here for reuse

    // for deleting contacts (children don't delete contacts)
    private BitSet deleteSet;
    private Semaphore deleteSemaphore;
    private RemoveContactGroupRelationshipsTask removeContactGroupRelationshipsTask;
    private RemoveContactInfoTask removeContactInfoTask;
    private RemoveContactsTask removeContactsTask;

    public ContactListAdapter(Activity context, List<ContactRecord> records){
        this.context = context;
        this.mRecordsList = records;
        mainSet = null;
        setupStructures();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        setupStructures();
    }

    protected void setupStructures() {
        // sort it so it is in order properly
        Collections.sort(this.mRecordsList, new SortByLastName());

        // if every one has different last letter, bitset will be 2x size unless num contacts > 27
        mainSet = new BitSet(Math.min(this.mRecordsList.size() * 2, this.mRecordsList.size() + 27));
        compareSet = new BitSet(mainSet.size());
        deleteSet = new BitSet(mainSet.size());

        String previous = "";
        for(int i = 0; i < this.mRecordsList.size(); i++){
            // insert headers at first letter of last name
            if(!mRecordsList.get(i).getLastName().substring(0,1).equalsIgnoreCase(previous)){
                // index of header is at index of actual + cardinality of mainset
                mainSet.set(i + mainSet.cardinality());
                previous = mRecordsList.get(i).getLastName().substring(0,1).toUpperCase();
            }
        }
    }

    public class SortByLastName implements Comparator<ContactRecord>{
        @Override
        public int compare(ContactRecord lhs, ContactRecord rhs) {

            if(lhs.getLastName().equalsIgnoreCase(rhs.getLastName())){
                return lhs.getFirstName().compareTo(rhs.getFirstName());
            }
            return lhs.getLastName().compareToIgnoreCase(rhs.getLastName());
        }
    }

    @Override
    public int getCount() {
        return mRecordsList.size() + mainSet.cardinality();
    }

    @Override
    public Object getItem(int position) {
        // this is not threadsafe
        return mRecordsList.get(position - getNumHeaders(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        int num_headers = getNumHeaders(position);
        boolean isHeader = mainSet.get(position);

        if(rowView == null){
            // reuse views
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowlayout, null);
            ContactListHolder viewHolder = new ContactListHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.row_text_label);
            rowView.setTag(viewHolder);
        }

        // fill data
        ContactListHolder holder = (ContactListHolder) rowView.getTag();
        if(isHeader){
            rowView.setClickable(true);
            // set the header as the first char of the last name
            ContactRecord record = mRecordsList.get(position - num_headers);
            holder.text.setText(record.getLastName().substring(0, 1).toUpperCase());
            holder.text.setBackgroundColor(context.getResources().getColor(R.color.contact_list_header));
        }
        else {
            rowView.setClickable(false);
            ContactRecord record = mRecordsList.get(position - num_headers);
            holder.text.setText(record.getFirstName() + " " + record.getLastName());
            holder.text.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }


        return rowView;
    }

    protected int getNumHeaders(int position){
        // get cardinality of mainset from 0 to position
        compareSet.clear();
        compareSet.set(0, position);
        compareSet.and(mainSet);
        return compareSet.cardinality();
    }

    private class ContactListHolder {
        TextView text;
    }

    private void showContactView(ContactRecord contactRecord){
        Intent intent = new Intent(context, ContactViewActivity.class);
        ParcelableContactRecord parcelable = new ParcelableContactRecord(contactRecord);
        intent.putExtra("contactRecord", parcelable);
        // need to start for result so contact view can tell contact list if contact list
        // should reload the contact list
        context.startActivityForResult(intent, 2);
    }

    // for contextual action bar access
    public void handleClick(int position){
        // this is short click
        int realPosition = position - getNumHeaders(position);
        showContactView(mRecordsList.get(realPosition));
    }

    public void set(int position, boolean value) {
        deleteSet.set(position, value);
    }

    public void deselectAll(){
        deleteSet.clear();
    }

    public void RemoveAllSelected() {
        // remove all contacts selected by the delete adapter

        // build it here so it doesn't get rebuilt in all the requests
        JSONArray jsonArray = new JSONArray();
        JSONArray bodyArray = new JSONArray();
        try{
            for(int i = deleteSet.nextSetBit(0); i >= 0; i = deleteSet.nextSetBit(i + 1)){
                JSONObject tmp = new JSONObject();
                jsonArray.put(mRecordsList.get(i-getNumHeaders(i)).getId());
                tmp.put("id", mRecordsList.get(i-getNumHeaders(i)).getId());
                bodyArray.put(tmp);
                if(mRecordsList.get(i-getNumHeaders(i)).getImageUrl() != null &&
                        !mRecordsList.get(i-getNumHeaders(i)).getImageUrl().isEmpty()){
                    RemoveContactFoldersTask removeContactFoldersTask =
                            new RemoveContactFoldersTask(mRecordsList.get(i-getNumHeaders(i)).getId());
                    removeContactFoldersTask.execute();
                }
            }
        }
        catch (JSONException e){
            Log.e("contactListAdapter", "unexpected JSON exception " + e.getMessage());
            return;
        }

        // need to remove contact's children (contact relationships, info) before deleting contact
        // use semaphore to block removeContact until everything else is removed
        deleteSemaphore = new Semaphore(2, true);
        try{
            deleteSemaphore.acquire(2);
        }
        catch (Exception e){
            Log.e("contactListAdapter", "could not do initial semaphore lock: " + e.toString());
        }

        removeContactGroupRelationshipsTask =
                new RemoveContactGroupRelationshipsTask(bodyArray);
        removeContactGroupRelationshipsTask.execute();

        removeContactInfoTask = new RemoveContactInfoTask(bodyArray);
        removeContactInfoTask.execute();

        removeContactsTask = new RemoveContactsTask(jsonArray);
        removeContactsTask.execute();
    }

    private class RemoveContactGroupRelationshipsTask extends BaseAsyncRequest{
        private JSONArray idArray;
        public RemoveContactGroupRelationshipsTask(JSONArray idArray){ this.idArray = idArray; }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "removeContactGroupRelations";

            serviceName = AppConstants.DB_SVC;
            endPoint = "contact_group_relationship";

            verb = "DELETE";

            // since we don't know the contact_group_relationship ids, change the id field to contact_id
            queryParams = new HashMap<>();
            queryParams.put("id_field", "contact_id");

            // form of request is { "resource": [... ids ...] }
            requestBody = new JSONObject();
            requestBody.put("resource", idArray);

            applicationApiKey = AppConstants.API_KEY;
            sessionToken = PrefUtil.getString(context, AppConstants.SESSION_TOKEN);
        }

        @Override
        protected void onCompletion(boolean success) {
            removeContactGroupRelationshipsTask = null;
            if(success){
                deleteSemaphore.release();
            }
            else{
                // if this failed, cancel the remove contacts task
                removeContactsTask.cancel(true);
            }
        }
    }

    private class RemoveContactInfoTask extends BaseAsyncRequest {

        private JSONArray idArray;
        public RemoveContactInfoTask(JSONArray idArray){ this.idArray = idArray; }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "removeContactInfo";
            serviceName = AppConstants.DB_SVC;
            endPoint = "contact_info";

            verb = "DELETE";

            // since we don't know the contact_info ids, change the id field to contact_id
            queryParams = new HashMap<>();
            queryParams.put("id_field", "contact_id");
            queryParams.put("continue", "1"); // continue to delete even if one fails

            // form of request is { "resource": [... ids ...] }
            requestBody = new JSONObject();
            requestBody.put("resource", idArray);

            applicationApiKey = AppConstants.API_KEY;
            sessionToken = PrefUtil.getString(context, AppConstants.SESSION_TOKEN);
        }

        @Override
        protected void onCompletion(boolean success) {
            removeContactInfoTask = null;
            // an error here may be from record not found, which is OK
            deleteSemaphore.release();
        }
    }

    private class RemoveContactsTask extends BaseAsyncRequest {
        private JSONArray idArray;
        public RemoveContactsTask(JSONArray idArray){
            this.idArray = idArray;
        }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            try{
                // wait until all records referencing this one have been removed
                deleteSemaphore.acquire(2);
            }
            catch (Exception e){
                Log.e("contactListAdapter", "could not do final semaphore lock: " + e.toString());
                throw new ApiException(10, "could not lock the semaphore");
            }
            deleteSemaphore.release(2); // don't hold on to these guys

            callerName = "removeContactsTask";
            serviceName = AppConstants.DB_SVC;
            endPoint = "contact";

            verb = "DELETE";

            // delete contact records by record id
            // need to remove '[' and ']' from JSON array because ids doesn't take JSON
            queryParams = new HashMap<>();
            queryParams.put("ids", idArray.toString().replace("[", "").replace("]", ""));

            applicationApiKey = AppConstants.API_KEY;
            sessionToken = PrefUtil.getString(context, AppConstants.SESSION_TOKEN);
        }

        @Override
        protected void onCompletion(boolean success) {
            removeContactsTask = null;
            if(success){
                int numRemoved = 0;
                for(int i = deleteSet.nextSetBit(0); i >= 0; i = deleteSet.nextSetBit(i + 1)) {
                    // calculate the new position of object following prev deletes
                    int realPosition = i - getNumHeaders(i) - numRemoved;
                    mRecordsList.remove(realPosition);
                    numRemoved++;
                }
                // once everything gets through successfully, reload the input views
                notifyDataSetChanged();
            }
        }
    }

    private class RemoveContactFoldersTask extends BaseAsyncRequest{
        private int contactId;
        public RemoveContactFoldersTask(int contactId){ this.contactId = contactId; }

        @Override
        protected void doSetup() throws ApiException, JSONException {

            verb = "DELETE";
            callerName = "remove contact folders";
            serviceName = "files";
            applicationApiKey = AppConstants.API_KEY;
            // the file path ends in a '/' because we are targeting a folder
            endPoint = "profile_images/" + contactId + "/";
            // want to delete all the files and folders in the target folder
            queryParams = new HashMap<>();
            queryParams.put("force", "1");
            sessionToken = PrefUtil.getString(context, AppConstants.SESSION_TOKEN);
        }
    }
}
