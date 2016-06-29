package com.dreamfactory.sampleapp.activities;

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
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.ContactService;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity responsible for managing group
 */
public class GroupActivity extends BaseActivity {

    protected EditText groupName;

    protected CreateGroupAdapter createGroupAdapter;

    protected ListView listView;

    protected boolean editingGroup;
    protected GroupRecord groupRecord;

    protected boolean removeContactsFinished = false;
    protected boolean assignContactsFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupName = (EditText) findViewById(R.id.group_edit_group_name);
        listView = (ListView) findViewById(R.id.group_edit_list);

        Intent intent = getIntent();

        if(intent.hasExtra("contactGroupId")){
            editingGroup = true;
            groupRecord = new GroupRecord();
            groupRecord.setId(intent.getLongExtra("contactGroupId", 0L));
            groupRecord.setName(intent.getStringExtra("groupName"));
            groupName.setText(groupRecord.getName());
        }
        else{
            editingGroup = false;
        }

        handleButtons();

        final ContactService service = DreamFactoryAPI.getInstance().getService(ContactService.class);

        service.getAllContacts().enqueue(new Callback<Resource<ContactRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactRecord>> call, Response<Resource<ContactRecord>> response) {
                if(response.isSuccessful()){
                    Resource<ContactRecord> data = response.body();

                    if(editingGroup){
                        createGroupAdapter = new EditGroupAdapter(GroupActivity.this, data.getResource(), groupRecord);
                        listView.setAdapter(createGroupAdapter);
                    }
                    else {
                        createGroupAdapter = new CreateGroupAdapter(GroupActivity.this, data.getResource());
                        listView.setAdapter(createGroupAdapter);
                    }

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            createGroupAdapter.handleClick(view);
                        }
                    });
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactRecord>> call, Throwable t) {
                showError("Error while loading contacts.", t);
            }
        });
    }

    protected void handleButtons(){
        final ImageButton backButton = (ImageButton) findViewById(R.id.persistent_back_button);
        final ImageButton editButton = (ImageButton) findViewById(R.id.persistent_edit_button);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.persistent_save_button);
        final ImageButton addButton = (ImageButton) findViewById(R.id.persistent_add_button);

        addButton.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setVisibility(View.INVISIBLE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCompletion();
            }
        });
    }

    protected void handleCompletion (){
        final ContactGroupService service = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        if(editingGroup){
            if(!groupName.getText().toString().equals(groupRecord.getName())){
                groupRecord.setName(groupName.getText().toString());

                Resource<GroupRecord> resource = new Resource<>();
                resource.addResource(groupRecord);

                service.updateContactGroups(resource).enqueue(new Callback<Resource<GroupRecord>>() {
                    @Override
                    public void onResponse(Call<Resource<GroupRecord>> call, Response<Resource<GroupRecord>> response) {
                        if(response.isSuccessful()) {
                            setResult(Activity.RESULT_OK);

                            // Close if group members are not changed
                            if(!((EditGroupAdapter) createGroupAdapter).didGroupChange()){
                                finish();
                            }
                        } else {
                            ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                            onFailure(call, e.toException());
                        }
                    }

                    @Override
                    public void onFailure(Call<Resource<GroupRecord>> call, Throwable t) {
                        showError("Error while updating contact group name.", t);
                    }
                });
            } else if(!((EditGroupAdapter) createGroupAdapter).didGroupChange()){
                // don't update calling activity
                setResult(Activity.RESULT_CANCELED);

                // Close in case group name or members are not changed
                finish();
            }

            if(((EditGroupAdapter) createGroupAdapter).didGroupChange()){
                // only update group members if the group changed
                assignContactsToGroup(createGroupAdapter.getSelectedContacts());

                List<Long> contactsToRemove = ((EditGroupAdapter)createGroupAdapter).getContactsToRemove();

                if(contactsToRemove.size() > 0) {
                    Resource<ContactsRelationalRecord> resourcesToRemove = new Resource<>();

                    for (Long contactId : contactsToRemove) {
                        ContactsRelationalRecord record = new ContactsRelationalRecord();
                        record.setContactId(contactId);
                        record.setContactGroupId(groupRecord.getId());

                        resourcesToRemove.addResource(record);
                    }

                    service.deleteGroupContacts(resourcesToRemove).enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
                        @Override
                        public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                            if (response.isSuccessful()) {
                                removeContactsFinished = true;

                                synchronized (this) {
                                    if (assignContactsFinished && removeContactsFinished) {
                                        setResult(Activity.RESULT_OK);

                                        finish();
                                    }
                                }
                            } else {
                                ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                                onFailure(call, e.toException());
                            }
                        }

                        @Override
                        public void onFailure(Call<Resource<ContactsRelationalRecord>> call, Throwable t) {
                            showError("Error while removing contacts from group.", t);
                        }
                    });
                } else {
                    removeContactsFinished = true;
                }
            }
        } else {
            final Resource<GroupRecord> resource = new Resource<>();

            groupRecord = new GroupRecord();
            groupRecord.setName(groupName.getText().toString());

            resource.addResource(groupRecord);

            service.createContactGroups(resource).enqueue(new Callback<Resource<GroupRecord>>() {
                @Override
                public void onResponse(Call<Resource<GroupRecord>> call, Response<Resource<GroupRecord>> response) {
                    if(response.isSuccessful()){
                        GroupRecord resultGroup = response.body().getResource().get(0);

                        groupRecord.setId(resultGroup.getId());

                        List<Long> contactsToAssign = createGroupAdapter.getSelectedContacts();

                        assignContactsToGroup(contactsToAssign);

                        if(assignContactsFinished) {
                            setResult(Activity.RESULT_OK);

                            finish();
                        }
                    } else{
                        setResult(Activity.RESULT_CANCELED);

                        ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                        onFailure(call, e.toException());

                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Resource<GroupRecord>> call, Throwable t) {
                    showError("Error while creating contact group.", t);
                }
            });
        }
    }

    private void assignContactsToGroup(List<Long> contactsToAssign) {
        if(contactsToAssign.size() == 0) {
            assignContactsFinished = true;
            return;
        }

        final ContactGroupService service = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        Resource<ContactsRelationalRecord> resourcesToCreate = new Resource<>();

        for(Long contactId : contactsToAssign) {
            ContactsRelationalRecord record = new ContactsRelationalRecord();
            record.setContactId(contactId);
            record.setContactGroupId(groupRecord.getId());

            resourcesToCreate.addResource(record);
        }

        service.addGroupContacts(resourcesToCreate).enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                if(response.isSuccessful()) {
                    assignContactsFinished = true;

                    synchronized (this) {
                        if (assignContactsFinished && (removeContactsFinished || !editingGroup)) {
                            setResult(Activity.RESULT_OK);

                            finish();
                        }
                    }
                } else {
                    setResult(Activity.RESULT_CANCELED);

                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactsRelationalRecord>> call, Throwable t) {
                showError("Error while assigning contacts to contact group.", t);
            }
        });
    }
}