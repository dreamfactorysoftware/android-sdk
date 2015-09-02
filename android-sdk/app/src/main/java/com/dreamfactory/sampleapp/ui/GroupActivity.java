package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.adapters.CreateGroupAdapter;
import com.dreamfactory.sampleapp.adapters.EditGroupAdapter;
import com.dreamfactory.sampleapp.models.ContactRecords;
import com.dreamfactory.sampleapp.models.GroupRecord;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import dfapi.ApiException;
import dfapi.ApiInvoker;

public class GroupActivity extends Activity {

    protected EditText groupName;

    protected GetAllContactsTask getAllContactsTask;

    protected CreateGroupAdapter createGroupAdapter;

    protected ListView listView;

    protected CreateGroupTask createGroupTask;

    protected boolean editingGroup;
    protected GroupRecord groupRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupName = (EditText) findViewById(R.id.group_edit_group_name);
        listView = (ListView) findViewById(R.id.group_edit_list);

        Intent intent = getIntent();

        if(intent.getIntExtra("contactGroupId", 0) != 0){
            editingGroup = true;
            groupRecord = new GroupRecord();
            groupRecord.id = intent.getIntExtra("contactGroupId", 0);
            groupRecord.name = intent.getStringExtra("groupName");
            groupName.setText(groupRecord.name);
        }
        else{
            editingGroup = false;
        }

        handleButtons();



        getAllContactsTask = new GetAllContactsTask();
        getAllContactsTask.execute();
    }

    protected void handleButtons(){
        ImageButton back_button = (ImageButton) findViewById(R.id.persistent_back_button);
        ImageButton edit_button = (ImageButton) findViewById(R.id.persistent_edit_button);
        ImageButton save_button = (ImageButton) findViewById(R.id.persistent_save_button);
        ImageButton add_button = (ImageButton) findViewById(R.id.persistent_add_button);

        add_button.setVisibility(View.INVISIBLE);

        back_button.setTag(this);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tmp = (Activity) v.getTag();
                tmp.finish();
            }
        });

        edit_button.setVisibility(View.INVISIBLE);

        save_button.setTag(this);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCompletion();
            }
        });
    }

    protected void handleCompletion (){
        if(editingGroup){
            if(!groupName.getText().toString().equals(groupRecord.name)){

                groupRecord.name = groupName.getText().toString();
                UpdateGroupName updateGroupName = new UpdateGroupName(groupRecord);
                updateGroupName.execute();
                setResult(Activity.RESULT_OK);
            }
            else if(((EditGroupAdapter) createGroupAdapter).didGroupChange()){
                // if the group is different now
                setResult(Activity.RESULT_OK);
            }
            else{
                // don't update calling activity
                setResult(Activity.RESULT_CANCELED);
            }

            if(((EditGroupAdapter) createGroupAdapter).didGroupChange()){
                // only update group members if the group changed
                CreateContactGroupRelationships createContactGroupRelationships =
                        new CreateContactGroupRelationships(groupRecord.id,
                                createGroupAdapter.getSelectedContacts());
                createContactGroupRelationships.execute();

                RemoveContactGroupRelationships removeContactGroupRelationships =
                        new RemoveContactGroupRelationships(groupRecord.id,
                                ((EditGroupAdapter)createGroupAdapter).getContactsToRemove());
                removeContactGroupRelationships.execute();
            }
            this.finish();
        }
        else {
            createGroupTask = new CreateGroupTask(this);
            createGroupTask.execute();
        }
    }

    protected class GetAllContactsTask extends BaseAsyncRequest {
        protected ContactRecords records;

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "GetAllContactsTask";
            // Not providing any query params gets all records in the table
            serviceName = "db";
            endPoint = "contact";
            verb = "GET";
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            // response is an array of contact records: { "record": [ {contactRecord}, {...} ] }
            records = (ContactRecords) ApiInvoker.deserialize(response, "", ContactRecords.class);
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                if(editingGroup){
                    createGroupAdapter = new EditGroupAdapter(GroupActivity.this, records.record, groupRecord);
                    listView.setAdapter(createGroupAdapter);

                }
                else {
                    createGroupAdapter = new CreateGroupAdapter(GroupActivity.this, records.record);
                    listView.setAdapter(createGroupAdapter);
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        createGroupAdapter.handle_click(view);
                    }
                });
            }
            getAllContactsTask = null;
        }
    }

    protected class CreateGroupTask extends BaseAsyncRequest {
        protected int groupId;
        protected Activity activity;
        protected String name;

        public CreateGroupTask(Activity tmp){
            activity = tmp;
            name = groupName.getText().toString();
        }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "CreateGroupTask";
            serviceName = "db";
            endPoint = "contact_group";
            verb = "POST";
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
            // only need to send the name in body
            // we don't have a group ID yet, so we can't provide one here
            requestString = "{\"name\":\"" + name + "\"}";
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            // need to get the groupId from the response to give to relational records
            GroupRecord record = (GroupRecord) ApiInvoker.deserialize(response, "", GroupRecord.class);
            groupId = record.id;
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                CreateContactGroupRelationships createContactGroupRelationships =
                        new CreateContactGroupRelationships(groupId, createGroupAdapter.getSelectedContacts());
                createContactGroupRelationships.execute();
                activity.setResult(Activity.RESULT_OK);
            }
            else{
                activity.setResult(Activity.RESULT_CANCELED);
            }
            createGroupTask = null;
            activity.finish();
        }
    }

    protected class CreateContactGroupRelationships extends BaseAsyncRequest{
        protected int groupId;
        protected List<Integer> contactIdList;
        public CreateContactGroupRelationships(int id, List<Integer> toAdd){
            groupId = id;
            contactIdList = toAdd;
        }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "CreateContactGroupRelationships";
            serviceName = "db";
            endPoint = "contact_group_relationship";

            verb = "POST";

            /*
             * Form of request is:
             *  {
             *      "record":[
             *          {
             *              "contact_group_id":id,
             *              "contact_id":id
             *          },
             *          {...}
             *      ]
             *  }
             */
            JSONArray jsonArray = new JSONArray();
            for(int contactId : contactIdList){
                JSONObject relation = new JSONObject();
                relation.put("contact_group_id", groupId);
                relation.put("contact_id", contactId);
                jsonArray.put(relation);
            }
            requestString = "{\"record\":" + jsonArray.toString() + "}";

            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }
    }

    protected class RemoveContactGroupRelationships extends BaseAsyncRequest{
        protected int groupId;
        protected List<Integer> contactIdList;
        public RemoveContactGroupRelationships(int id, List<Integer> toAdd){
            groupId = id;
            contactIdList = toAdd;
        }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "RemoveContactGroupRelationshipsTask";
            serviceName = "db";
            endPoint = "contact_group_relationship";

            verb = "DELETE";

            // do not know the ID of the record to remove
            // one value for groupId, but many values for contactId
            // instead of making a long SQL query, change what we use as identifiers
            queryParams = new HashMap<>();
            queryParams.put("id_field", "contact_group_id,contact_id");
            /*
             * Form of request is:
             *  {
             *      "record":[
             *          {
             *              "contact_group_id":id,
             *              "contact_id":id
             *          },
             *          {...}
             *      ]
             *  }
             */
            JSONArray jsonArray = new JSONArray();
            for(int contactId : contactIdList){
                JSONObject relation = new JSONObject();
                relation.put("contact_group_id", groupId);
                relation.put("contact_id", contactId);
                jsonArray.put(relation);
            }
            requestString = "{\"record\":" + jsonArray.toString() + "}";

            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }
    }

    protected class UpdateGroupName extends BaseAsyncRequest {
        private GroupRecord record;
        public UpdateGroupName(GroupRecord record){ this.record = record; }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "UpdateGroupTask";

            serviceName = "db";
            endPoint = "contact_group";
            verb = "PATCH";

            // send the record to patch, need to include record id
            //requestString = "{\"name\":\"" + record.name + "\",\"id\":" +
                    //record.id + "}";
            // form is { "id": id, "name": name }
            requestString = ApiInvoker.serialize(record);

            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }
    }
}
