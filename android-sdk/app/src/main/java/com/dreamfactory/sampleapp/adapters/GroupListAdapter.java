package com.dreamfactory.sampleapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.ui.ContactListActivity;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONException;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

import dfapi.ApiException;

public class GroupListAdapter extends BaseAdapter{
    private Activity context;
    public List<GroupRecord> records;
    private BitSet deleteSet;

    private RemoveContactGroupRelationshipsTask removeContactGroupRelationTask;
    private RemoveGroupsTask removeGroupsTask;

    public GroupListAdapter(Activity context, List<GroupRecord> records){
        this.context = context;
        this.records = records;
        deleteSet = new BitSet(records.size());
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void showContactList(int groupId, String groupName) {
        Intent intent = new Intent(context, ContactListActivity.class);
        intent.putExtra("groupRecordId", groupId);
        intent.putExtra("groupName", groupName);
        context.startActivity(intent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null){
            // reuse views
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowlayout, null);
            GroupListHolder viewHolder = new GroupListHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.row_text_label);
            rowView.setTag(viewHolder);
        }

        rowView.setClickable(false);
        // fill data
        GroupListHolder holder = (GroupListHolder) rowView.getTag();
        GroupRecord record = records.get(position);
        holder.text.setText(record.groupName);
        holder.record = record;

        return rowView;
    }

    private class GroupListHolder {
        TextView text;
        GroupRecord record;
    }

    public void handleClick(int position){
        GroupRecord record = (GroupRecord) getItem(position);
        showContactList(record.contactGroupId, record.groupName);
    }

    public void set(int position, boolean value){
        deleteSet.set(position, value);
    }

    public void deselectAll(){
        deleteSet.clear();
    }

    public void RemoveAllSelected() {
        // need to delete records with references to the contact_group record before
        // deleting the contact group record its self
        removeContactGroupRelationTask = new RemoveContactGroupRelationshipsTask();
        removeContactGroupRelationTask.execute();
    }

    private void removeFromList() {
        // remove selected groups from the list, called once the delete has been OK'd with the server
        int delete_offset = 0; // account for shift that happens as items are deleted from the list
        for(int i = deleteSet.nextSetBit(0); i >= 0; i = deleteSet.nextSetBit(i + 1)) {
            records.remove(i - delete_offset);
            delete_offset++;
        }
        notifyDataSetChanged();
    }

    private class RemoveContactGroupRelationshipsTask extends BaseAsyncRequest {
        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "removeGroupRelationships";

            serviceName = "db";
            endPoint = "contact_relationships";
            verb = "DELETE";

            // delete multiple records by chaining filters together
            StringBuilder builder = new StringBuilder();
            int i = deleteSet.nextSetBit(0);

            builder.append("contactGroupId=").append(records.get(i).contactGroupId);
            for(i = deleteSet.nextSetBit(i+1);i >=0; i = deleteSet.nextSetBit(i + 1)){
                builder.append("||contactGroupId=").append(records.get(i).contactGroupId);
            }

            queryParams = new HashMap<>();
            queryParams.put("filter", builder.toString());

            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(context, AppConstants.SESSION_ID);
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success) {
                removeGroupsTask = new RemoveGroupsTask();
                removeGroupsTask.execute();
            }
            removeContactGroupRelationTask = null;
        }
    }

    private class RemoveGroupsTask extends BaseAsyncRequest {
        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "removeGroups";
            serviceName = "db";
            endPoint = "contact_groups";
            verb = "DELETE";

            // delete records by ids
            StringBuilder builder = new StringBuilder();
            int i = deleteSet.nextSetBit(0);

            builder.append(records.get(i).contactGroupId);
            for(i = deleteSet.nextSetBit(i + 1);i >=0; i = deleteSet.nextSetBit(i + 1)){
                builder.append(",").append(records.get(i).contactGroupId);
            }

            // ids is a comma separated list of contact_group record ids
            queryParams = new HashMap<>();
            queryParams.put("ids", builder.toString());

            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(context, AppConstants.SESSION_ID);
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                // remove the groups from the display list
                removeFromList();
            }
            removeGroupsTask = null;
        }
    }
}
