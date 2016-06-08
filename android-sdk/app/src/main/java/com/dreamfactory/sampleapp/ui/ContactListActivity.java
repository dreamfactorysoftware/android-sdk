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

public class ContactListActivity extends BaseActivity {
    private Call<Resource<ContactsRelationalRecord>> getContactsInGroupCall;

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

        loadGroupContacts(contactGroupId);

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

    private void loadGroupContacts(int contactGroupId) {
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
        if(resultCode == Activity.RESULT_OK){
            loadGroupContacts(contactGroupId);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
    }
}
