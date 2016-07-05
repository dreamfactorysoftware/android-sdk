package com.dreamfactory.sampleapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.activities.BaseActivity;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class CreateGroupAdapter extends ContactListAdapter {
    protected BitSet selectedSet;

    public CreateGroupAdapter(BaseActivity activity, List<ContactRecord> records) {
        super(activity, records);

        // store selectedSet contacts
        selectedSet = new BitSet(records.size());

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        int numHeaders = getNumHeaders(position);
        boolean isHeader = mainSet.get(position);

        if(rowView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.selcetable_row_layout, null);
            GroupHolder holder = new GroupHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.selectable_row_label);
            holder.checkBox = (CheckBox) rowView.findViewById(R.id.selectable_row_checkbox);
            rowView.setTag(holder);
        }

        GroupHolder holder = (GroupHolder) rowView.getTag();
        ContactRecord record = mRecordsList.get(position - numHeaders);

        if(isHeader){
            rowView.setClickable(true);
            holder.checkBox.setVisibility(View.GONE);
            holder.textView.setText(("" + record.getLastName().charAt(0)).toUpperCase());
            holder.textView.setBackgroundColor(activity.getResources().getColor(R.color.contact_list_header));
        }
        else{
            rowView.setClickable(false);
            holder.textView.setText(record.getFullName());

            holder.record = record;

            holder.position = position - numHeaders;

            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(selectedSet.get(position - numHeaders));

            holder.textView.setBackgroundColor(activity.getResources().getColor(android.R.color.transparent));
        }
        return rowView;
    }

    public void handleClick(View v){
        // used by multi modal
        GroupHolder groupHolder = (GroupHolder) v.getTag();
        selectedSet.flip(groupHolder.position);
        groupHolder.checkBox.setChecked(selectedSet.get(groupHolder.position));
    }

    public List<Long> getSelectedContacts() {
        // returns list of contacts selected to be in group
        List<Long> selectedContacts = new ArrayList<>();

        for(int i = selectedSet.nextSetBit(0); i >= 0; i = selectedSet.nextSetBit(i + 1)){
            selectedContacts.add(mRecordsList.get(i).getId());
        }
        return selectedContacts;
    }

    protected class GroupHolder {
        public ContactRecord record;

        public TextView textView;
        public CheckBox checkBox;

        public int position;
    }
}
