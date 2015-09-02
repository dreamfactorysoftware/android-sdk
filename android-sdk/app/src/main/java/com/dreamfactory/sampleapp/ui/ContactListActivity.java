package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.adapters.ContactListAdapter;
import com.dreamfactory.sampleapp.adapters.DeletableContactListAdapter;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecords;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dfapi.ApiException;
import dfapi.ApiInvoker;

public class ContactListActivity extends Activity {
    private GetContactsInGroupTask getContactsInGroupTask;
    private ListView listView;
    private ContactListAdapter contactListAdapter;

    private int contactGroupId;
    private String groupName;

    private final int EDIT_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    private final int CREATE_CONTACT_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);


        // get the groupRecordId from the intent
        Intent intent = getIntent();
        contactGroupId = intent.getIntExtra("groupRecordId", 0);
        groupName = intent.getStringExtra("groupName");

        getContactsInGroupTask = new GetContactsInGroupTask(contactGroupId);
        getContactsInGroupTask.execute();

        listView = (ListView) findViewById(R.id.contactList);
        registerForContextMenu(listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);


        ImageButton back_button = (ImageButton) findViewById(R.id.persistent_back_button);
        ImageButton edit_button = (ImageButton) findViewById(R.id.persistent_edit_button);
        ImageButton save_button = (ImageButton) findViewById(R.id.persistent_save_button);
        ImageButton add_button = (ImageButton) findViewById(R.id.persistent_add_button);

        add_button.setTag(this);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tmp = (Activity) v.getTag();

                Intent intent = new Intent(tmp, CreateContactActivity.class);
                intent.putExtra("contactGroupId", contactGroupId);
                tmp.startActivityForResult(intent, CREATE_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

        back_button.setTag(this);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tmp = (Activity) v.getTag();
                tmp.finish();
            }
        });

        edit_button.setTag(this);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tmp = (Activity) v.getTag();
                Intent intent = new Intent(tmp, GroupActivity.class);
                intent.putExtra("contactGroupId", contactGroupId);
                intent.putExtra("groupName", groupName);
                tmp.startActivityForResult(intent, EDIT_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });
        save_button.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            // if a child activity changed the contacts or the relationships, reload
            if(getContactsInGroupTask != null){
                getContactsInGroupTask.cancel(true);
            }
            getContactsInGroupTask = new GetContactsInGroupTask(contactGroupId);
            getContactsInGroupTask.execute();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
    }

    public class GetContactsInGroupTask extends BaseAsyncRequest {
        private int groupId;
        private List<ContactRecord> contactRecords;
        public GetContactsInGroupTask(int id){
            groupId = id;
        }

        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "getContactsInGroup";

            serviceName = "db";
            endPoint = "contact_group_relationship";
            verb = "GET";

            // filter to only select the contacts in this group
            queryParams = new HashMap<>();
            queryParams.put("filter", "contact_group_id=" + groupId);

            // request without related would return just {id, contact_group_id, contact_id}
            // set the related field to go get the contact mRecordsList referenced by
            // each contact_group_relationship record
            queryParams.put("related", "contact_by_contact_id");

            // need to include the application name and session id
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            // response is in form
            // {
            //      <group info>,
            //      "contact_by_contact_id": [
            //          { <contact record> }
            //      ]
            //  }
            ContactsRelationalRecords relationalRecords =
                    (ContactsRelationalRecords) ApiInvoker.deserialize(response, "", ContactsRelationalRecords.class);
            contactRecords = new ArrayList<>();
            for(ContactsRelationalRecord record : relationalRecords.record){
                contactRecords.add(record.contact_by_contact_id);
            }

        }

        @Override
        protected void onCompletion(boolean success) {
            getContactsInGroupTask = null;
            if(success && contactRecords != null && contactRecords.size() > 0){
                if(contactListAdapter == null) {
                    // if there is no adapter, create a new one
                    contactListAdapter = new ContactListAdapter(ContactListActivity.this, contactRecords);
                    listView.setAdapter(contactListAdapter);
                    listView.setMultiChoiceModeListener(new DeletableContactListAdapter(contactListAdapter));
                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            ((ListView) view).setItemChecked(position, true);
                            contactListAdapter.set(position, true);
                            return true;
                        }
                    });

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            contactListAdapter.handleClick(position);
                        }
                    });
                }
                else{
                    // update the adapter data
                    contactListAdapter.mRecordsList = contactRecords;
                    contactListAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
