package com.dreamfactory.sampleapp.adapters;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactRecord;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class CreateGroupAdapter extends ContactListAdapter {
    protected BitSet selectedSet;

    public CreateGroupAdapter(Activity context, List<ContactRecord> records) {
        super(context, records);

        // store selectedSet contacts
        selectedSet = new BitSet(records.size());

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        int num_headers = getNumHeaders(position);
        boolean isHeader = mainSet.get(position);

        if(rowView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.selcetable_row_layout, null);
            GroupHolder holder = new GroupHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.selectable_row_label);
            holder.checkBox = (CheckBox) rowView.findViewById(R.id.selectable_row_checkbox);
            rowView.setTag(holder);
        }

        GroupHolder holder = (GroupHolder) rowView.getTag();
        ContactRecord record = mRecordsList.get(position - num_headers);

        if(isHeader){
            rowView.setClickable(true);
            holder.checkBox.setVisibility(View.GONE);
            holder.textView.setText(("" + record.getLastName().charAt(0)).toUpperCase());
            holder.textView.setBackgroundColor(context.getResources().getColor(R.color.contact_list_header));
        }
        else{
            rowView.setClickable(false);
            holder.textView.setText(record.getFirstName() + " " + record.getLastName());

            holder.record = record;

            holder.position = position - num_headers;

            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(selectedSet.get(position - num_headers));

            holder.textView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
        return rowView;
    }

    public void handle_click(View v){
        // used by multi modal
        GroupHolder groupHolder = (GroupHolder) v.getTag();
        selectedSet.flip(groupHolder.position);
        groupHolder.checkBox.setChecked(selectedSet.get(groupHolder.position));
    }

    public List<Integer> getSelectedContacts() {
        // returns list of contacts selected to be in group
        List<Integer> selectedContacts = new ArrayList<>();

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
