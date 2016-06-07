package com.dreamfactory.sampleapp.adapters;

import android.app.Activity;

import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecords;
import com.dreamfactory.sampleapp.models.GroupRecord;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dfapi.ApiException;
import dfapi.ApiInvoker;

public class EditGroupAdapter extends CreateGroupAdapter {

    protected GroupRecord record;
    protected BitSet inGroupSet;
    protected GetContactsInGroupTask getContactsInGroupTask;

    public EditGroupAdapter(Activity context, List<ContactRecord> records, GroupRecord record) {
        super(context, records);

        this.record = record;

        inGroupSet = new BitSet(selectedSet.size());

        getContactsInGroupTask = new GetContactsInGroupTask(this.record.getId());
        getContactsInGroupTask.execute();
    }

    @Override
    public List<Long> getSelectedContacts() {
        // deselect the contacts already in group first
        BitSet tmp = selectedSet.get(0, selectedSet.size());
        selectedSet.andNot(inGroupSet);
        // remove the selected contacts from the in group set
        inGroupSet.andNot(tmp);

        return super.getSelectedContacts();
    }

    public List<Long> getContactsToRemove() {
        // called by groupActivity to delete contacts
        selectedSet = inGroupSet;
        return super.getSelectedContacts();
    }

    public boolean didGroupChange(){
        // checked if the group members changed
        if(inGroupSet.cardinality() != selectedSet.cardinality()){
            return true;
        }
        compareSet.clear();
        compareSet.or(inGroupSet);
        compareSet.and(selectedSet);

        // true if a contact is in the group set but not selected
        return compareSet.cardinality() != inGroupSet.cardinality();
    }


    protected class GetContactsInGroupTask extends BaseAsyncRequest{
        protected Long groupId;
        public GetContactsInGroupTask(Long groupId){ this.groupId = groupId; }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "GetContactsInGroupTask";

            serviceName = AppConstants.DB_SVC;
            endPoint = "contact_group_relationship";

            verb = "GET";
            applicationApiKey = AppConstants.API_KEY;
            sessionToken = PrefUtil.getString(context, AppConstants.SESSION_TOKEN);

            // filter to only get the group we want
            queryParams = new HashMap<>();
            queryParams.put("filter", "contact_group_id=" + groupId);

            // request without related would return just {id, contact_group_id, contact_id}
            // set the related field to go get the contact records referenced by
            // each contact_group_relationship record
            queryParams.put("related", "contact_by_contact_id");
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            // response is in form
            // {
            //      < group info >,
            //      "contact_by_contactId" : [
            //          { contact record }
            //      ]
            //  }
            ContactsRelationalRecords relationalRecords =
                    (ContactsRelationalRecords) ApiInvoker.deserialize(response, "",
                            ContactsRelationalRecords.class);

            List<ContactRecord> contactRecords = new ArrayList<>();
            for(ContactsRelationalRecord record : relationalRecords.record){
                contactRecords.add(record.getContact());
            }


            // sort so we can find these guys in the big contacts list in ~ linear time
            Collections.sort(contactRecords, new SortByLastName());

            int j = 0;
            for(int i = 0; i < mRecordsList.size() && j < contactRecords.size(); i++){
                if(mRecordsList.get(i).getId() == contactRecords.get(j).getId()){
                    // mark the contacts already in the group
                    // use inGroupSet so we can tell how things changed later
                    inGroupSet.set(i);
                    j++;
                }
            }
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                // indicate that they should be checked and update the adapter in the main thread
                selectedSet.or(inGroupSet);
                notifyDataSetChanged();
            }
            getContactsInGroupTask = null;
        }
    }
}
