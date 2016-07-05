package com.dreamfactory.sampleapp.adapters;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.activities.BaseActivity;
import com.dreamfactory.sampleapp.activities.ContactListActivity;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupListAdapter extends BaseAdapter {

    private BaseActivity activity;
    private List<GroupRecord> records;
    private BitSet deleteSet;

    public GroupListAdapter(BaseActivity activity, List<GroupRecord> records){
        this.activity = activity;
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

    public void showContactList(Long groupId, String groupName) {
        Intent intent = new Intent(activity, ContactListActivity.class);
        intent.putExtra("groupRecordId", groupId);
        intent.putExtra("groupName", groupName);
        activity.startActivity(intent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null){
            // reuse views
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowlayout, null);
            GroupListHolder viewHolder = new GroupListHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.row_text_label);
            rowView.setTag(viewHolder);
        }

        rowView.setClickable(false);
        // fill data
        GroupListHolder holder = (GroupListHolder) rowView.getTag();
        GroupRecord record = records.get(position);
        holder.text.setText(record.getName());
        holder.record = record;

        return rowView;
    }

    private class GroupListHolder {
        TextView text;
        GroupRecord record;
    }

    public void handleClick(int position){
        GroupRecord record = (GroupRecord) getItem(position);
        showContactList(record.getId(), record.getName());
    }

    public void set(int position, boolean value){
        deleteSet.set(position, value);
    }

    public void deselectAll(){
        deleteSet.clear();
    }

    public void removeAllSelected() {
        // need to delete records with references to the contact_group record before
        // deleting the contact group record its self
        final ContactGroupService contactGroupService = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        // delete multiple records by chaining filters together
        StringBuilder builder = new StringBuilder();

        final List<Long> groupIds = new ArrayList<>();

        int i = deleteSet.nextSetBit(0);

        builder.append("(contact_group_id=").append(records.get(i).getId()).append(")");
        groupIds.add(records.get(i).getId());

        for(i = deleteSet.nextSetBit(i+1);i >=0; i = deleteSet.nextSetBit(i + 1)){
            builder.append(" and (contact_group_id=").append(records.get(i).getId()).append(")");

            groupIds.add(records.get(i).getId());
        }

        contactGroupService.deleteContactsFromGroups(builder.toString()).enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                if(response.isSuccessful()){
                    contactGroupService.removeGroups(TextUtils.join(",", groupIds)).enqueue(new Callback<Resource<GroupRecord>>() {
                        @Override
                        public void onResponse(Call<Resource<GroupRecord>> call, Response<Resource<GroupRecord>> response) {
                            if(response.isSuccessful()) {
                                removeFromList();
                            } else {
                                ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                                onFailure(call, e.toException());
                            }
                        }

                        @Override
                        public void onFailure(Call<Resource<GroupRecord>> call, Throwable t) {
                            activity.showError("Error while removing contact groups.", t);
                        }
                    });
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
    }

    private void removeFromList() {
        // remove selected groups from the list, called once the delete has been OK'd with the server
        int deleteOffset = 0; // account for shift that happens as items are deleted from the list
        for(int i = deleteSet.nextSetBit(0); i >= 0; i = deleteSet.nextSetBit(i + 1)) {
            records.remove(i - deleteOffset);
            deleteOffset++;
        }
        notifyDataSetChanged();
    }

    public void setRecords(List<GroupRecord> records) {
        this.records = records;

        notifyDataSetChanged();
    }
}
