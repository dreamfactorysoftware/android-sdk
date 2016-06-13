package com.dreamfactory.sampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.adapters.DeletableGroupListAdapter;
import com.dreamfactory.sampleapp.adapters.GroupListAdapter;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity responsible for showing group list
 */
public class GroupListActivity extends BaseActivity {

    private GroupListAdapter groupListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_list);

        listView = (ListView) findViewById(R.id.groupList);
        registerForContextMenu(listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        final ImageButton backButton = (ImageButton) findViewById(R.id.persistent_back_button);
        final ImageButton editButton = (ImageButton) findViewById(R.id.persistent_edit_button);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.persistent_save_button);
        final ImageButton addButton = (ImageButton) findViewById(R.id.persistent_add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GroupActivity.class);
                GroupListActivity.this.startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editButton.setVisibility(View.INVISIBLE);

        saveButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final ContactGroupService service = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        service.getGroupList().enqueue(new Callback<Resource<GroupRecord>>() {
            @Override
            public void onResponse(Call<Resource<GroupRecord>> call, Response<Resource<GroupRecord>> response) {
                if(response.isSuccessful()) {
                    if (groupListAdapter == null) {
                        // if there is no adapter set, create a new one
                        groupListAdapter = new GroupListAdapter(GroupListActivity.this, response.body().getResource());
                        listView.setAdapter(groupListAdapter);

                        // configure press + hold to select delete
                        listView.setMultiChoiceModeListener(new DeletableGroupListAdapter(groupListAdapter));
                        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                ((ListView) view).setItemChecked(position, true);
                                groupListAdapter.set(position, true);
                                return true;
                            }
                        });

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                groupListAdapter.handleClick(position);
                            }
                        });

                    } else {
                        // if an adapter is set, reload its data
                        groupListAdapter.setRecords(response.body().getResource());
                    }
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<GroupRecord>> call, Throwable t) {
                showError("Error while loading contact groups.", t);
            }
        });
    }
}