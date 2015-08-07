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

        getContactsInGroupTask = new GetContactsInGroupTask(this.record.contactGroupId);
        getContactsInGroupTask.execute();
    }

    @Override
    public List<Integer> getSelectedContacts() {
        // deselect the contacts already in group first
        BitSet tmp = selectedSet.get(0, selectedSet.size());
        selectedSet.andNot(inGroupSet);
        // remove the selected contacts from the in group set
        inGroupSet.andNot(tmp);

        return super.getSelectedContacts();
    }

    public List<Integer> getContactsToRemove() {
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
        protected int groupId;
        public GetContactsInGroupTask(int groupId){ this.groupId = groupId; }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "GetContactsInGroupTask";

            serviceName = "db";
            endPoint = "contact_relationships";

            verb = "GET";
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(context, AppConstants.SESSION_ID);

            // filter to only get the group we want
            queryParams = new HashMap<>();
            queryParams.put("filter", "contactGroupId=" + groupId);

            // request without related would return just {id, groupId, contactId}
            // set the related field to go get the contact records referenced by
            // each contactrelationships record
            queryParams.put("related", "contacts_by_contactId");
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            // response is in form
            // {
            //      < group info >,
            //      "contacts_by_contactId" : [
            //          { contact record }
            //      ]
            //  }
            ContactsRelationalRecords relationalRecords =
                    (ContactsRelationalRecords) ApiInvoker.deserialize(response, "",
                            ContactsRelationalRecords.class);

            List<ContactRecord> contactRecords = new ArrayList<>();
            for(ContactsRelationalRecord record : relationalRecords.record){
                contactRecords.add(record.contacts_by_contact_id);
            }


            // sort so we can find these guys in the big contacts list in ~ linear time
            Collections.sort(contactRecords, new SortByLastName());

            int j = 0;
            for(int i = 0; i < mRecordsList.size() && j < contactRecords.size(); i++){
                if(mRecordsList.get(i).contactId == contactRecords.get(j).contactId){
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
