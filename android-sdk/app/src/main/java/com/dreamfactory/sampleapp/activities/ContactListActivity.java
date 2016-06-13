package com.dreamfactory.sampleapp.activities;

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
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;

import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.Resource;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity responsible for showing contact list
 *
 */
public class ContactListActivity extends BaseActivity {

    private Call<Resource<ContactsRelationalRecord>> getContactsInGroupCall;

    private ListView listView;
    private ContactListAdapter contactListAdapter;

    private Long contactGroupId;
    private String groupName;

    private final int EDIT_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    private final int CREATE_CONTACT_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        // get the groupRecordId from the intent
        Intent intent = getIntent();
        contactGroupId = intent.getLongExtra("groupRecordId", 0);
        groupName = intent.getStringExtra("groupName");

        loadGroupContacts(contactGroupId);

        listView = (ListView) findViewById(R.id.contactList);
        registerForContextMenu(listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        final ImageButton backButton = (ImageButton) findViewById(R.id.persistent_back_button);
        final ImageButton editButton = (ImageButton) findViewById(R.id.persistent_edit_button);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.persistent_save_button);
        final ImageButton addButton = (ImageButton) findViewById(R.id.persistent_add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateContactActivity.class);
                intent.putExtra("contactGroupId", contactGroupId);

                ContactListActivity.this.startActivityForResult(intent, CREATE_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GroupActivity.class);
                intent.putExtra("contactGroupId", contactGroupId);
                intent.putExtra("groupName", groupName);

                ContactListActivity.this.startActivityForResult(intent, EDIT_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

        saveButton.setVisibility(View.INVISIBLE);
    }

    private void loadGroupContacts(Long contactGroupId) {
        if(getContactsInGroupCall != null) {
            getContactsInGroupCall.cancel();
        }

        final ContactGroupService service = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        getContactsInGroupCall = service.getGroupContacts("contact_group_id=" + contactGroupId);

        getContactsInGroupCall.enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                if(response.isSuccessful()){
                    List<ContactsRelationalRecord> contactRecordRelationships = response.body().getResource();

                    List<ContactRecord> contactRecords = new ArrayList<>();

                    for(ContactsRelationalRecord rel : contactRecordRelationships) {
                        contactRecords.add(rel.getContact());
                    }

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
                    } else{
                        // update the adapter data
                        contactListAdapter.mRecordsList = contactRecords;
                        contactListAdapter.notifyDataSetChanged();
                    }
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactsRelationalRecord>> call, Throwable t) {
                if(!call.isCanceled()) {
                    showError("Error while loading group contacts.", t);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            loadGroupContacts(contactGroupId);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
    }
}
