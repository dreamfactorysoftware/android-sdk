package com.dreamfactory.sampleapp.adapters;

import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.ContactInfoService;
import com.dreamfactory.sampleapp.api.services.ContactService;
import com.dreamfactory.sampleapp.api.services.ImageService;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.FileRecord;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.activities.BaseActivity;
import com.dreamfactory.sampleapp.activities.ContactViewActivity;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactListAdapter extends BaseAdapter {

    protected BaseActivity activity;
    public List<ContactRecord> mRecordsList;

    protected BitSet mainSet; // track where section headers are in the list view
    protected BitSet compareSet; // declared up here for reuse

    // for deleting contacts (children don't delete contacts)
    private BitSet deleteSet;

    private boolean contactInfosRemoved = false;
    private boolean contactRemovedFromGroups = false;
    private boolean removeContactsCalled = false;

    public ContactListAdapter(BaseActivity activity, List<ContactRecord> records){
        this.activity = activity;
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

    protected class SortByLastName implements Comparator<ContactRecord>{
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
            LayoutInflater inflater = activity.getLayoutInflater();
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
            holder.text.setBackgroundColor(activity.getResources().getColor(R.color.contact_list_header));
        } else {
            rowView.setClickable(false);
            ContactRecord record = mRecordsList.get(position - num_headers);
            holder.text.setText(record.getFullName());
            holder.text.setBackgroundColor(activity.getResources().getColor(android.R.color.transparent));
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
        Intent intent = new Intent(activity, ContactViewActivity.class);
        intent.putExtra("contactRecord", (Parcelable) new ContactRecord.Parcelable(contactRecord));
        // need to start for result so contact view can tell contact list if contact list
        // should reload the contact list
        activity.startActivityForResult(intent, 2);
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

    public void removeAllSelected() {
        // remove all contacts selected by the delete adapter

        // build it here so it doesn't get rebuilt in all the requests
        final Resource<Long> contactIdsToRemove = new Resource<>();

        for(int i = deleteSet.nextSetBit(0); i >= 0; i = deleteSet.nextSetBit(i + 1)){
            contactIdsToRemove.addResource(mRecordsList.get(i-getNumHeaders(i)).getId());
            if(mRecordsList.get(i-getNumHeaders(i)).getImageUrl() != null &&
                    !mRecordsList.get(i-getNumHeaders(i)).getImageUrl().isEmpty()){

                final ImageService imageService = DreamFactoryAPI.getInstance().getService(ImageService.class);

                imageService.removeFolder(mRecordsList.get(i-getNumHeaders(i)).getId()).enqueue(new Callback<FileRecord>() {
                    @Override
                    public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                        if(!response.isSuccessful()) {
                            ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                            onFailure(call, e.toException());
                        }
                    }

                    @Override
                    public void onFailure(Call<FileRecord> call, Throwable t) {
                        activity.showError("Error while updating contact.", t);
                    }
                });
            }
        }

        if(contactIdsToRemove.getResource().size() == 0) return;

        final ContactGroupService contactGroupService = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        contactGroupService.deleteContactsFromGroups(contactIdsToRemove).enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                if(response.isSuccessful()){
                   contactRemovedFromGroups = true;

                   if(contactRemovedFromGroups && contactInfosRemoved) {
                       removeContacts(contactIdsToRemove);
                   }
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactsRelationalRecord>> call, Throwable t) {
                activity.showError("Error while removing contact from groups.", t);
            }
        });

        final ContactInfoService contactInfoService = DreamFactoryAPI.getInstance().getService(ContactInfoService.class);

        contactInfoService.removeContactInfos(contactIdsToRemove).enqueue(new Callback<Resource<ContactInfoRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactInfoRecord>> call, Response<Resource<ContactInfoRecord>> response) {
                if(response.isSuccessful()) {
                    contactInfosRemoved = true;

                    if(contactRemovedFromGroups && contactInfosRemoved) {
                        removeContacts(contactIdsToRemove);
                    }
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    // These two errors are fine for delete case
                    if(e.getError().getCode() == 404L || e.getError().getCode() == 400L) {
                        activity.logError("Error while removing contact infos.", e.toException());

                        contactInfosRemoved = true;

                        if(contactRemovedFromGroups && contactInfosRemoved) {
                            removeContacts(contactIdsToRemove);
                        }
                    } else {
                        onFailure(call, e.toException());
                    }
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactInfoRecord>> call, Throwable t) {
                activity.showError("Error while removing contact infos.", t);
            }
        });
    }

    private void removeContacts(Resource<Long> contactIdsToRemove) {
        if(removeContactsCalled) return;

        removeContactsCalled = true;

        final ContactService contactService = DreamFactoryAPI.getInstance().getService(ContactService.class);

        contactService.removeContacts(TextUtils.join(",", contactIdsToRemove.getResource())).enqueue(new Callback<Resource<ContactRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactRecord>> call, Response<Resource<ContactRecord>> response) {
                if(response.isSuccessful()){
                    int numRemoved = 0;
                    for(int i = deleteSet.nextSetBit(0); i >= 0; i = deleteSet.nextSetBit(i + 1)) {
                        // calculate the new position of object following prev deletes
                        int realPosition = i - getNumHeaders(i) - numRemoved;
                        mRecordsList.remove(realPosition);
                        numRemoved++;
                    }
                    // once everything gets through successfully, reload the input views
                    notifyDataSetChanged();
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactRecord>> call, Throwable t) {
                activity.showError("Error while removing contacts.", t);
            }
        });
    }
}
