package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
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
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.GroupRecords;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONException;

import java.util.ArrayList;

import dfapi.ApiException;
import dfapi.ApiInvoker;


public class GroupListActivity extends Activity {
    private GetGroupsTask getGroupsTask;
    private ArrayList<GroupRecord> groupRecords;
    private GroupListAdapter groupListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_list);

        listView = (ListView) findViewById(R.id.groupList);
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
                Intent intent = new Intent(tmp, GroupActivity.class);
                tmp.startActivity(intent);
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

        edit_button.setVisibility(View.INVISIBLE);

        save_button.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // reload the data when this view reappears
        getGroupsTask = new GetGroupsTask();
        getGroupsTask.execute();
    }

    public class GetGroupsTask extends BaseAsyncRequest {
        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "getGroupsTask";

            // Get all the records from the table
            serviceName = AppConstants.DB_SVC;
            endPoint = "contact_group";

            verb = "GET";

            // need API key and session token
            applicationApiKey = AppConstants.API_KEY;
            sessionToken = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_TOKEN);
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            // response is an array of group records
            // form is:
            // {
            //      "resource": [
            //          {"id":id, "name":name},
            //          {...}
            //      ]
            // }
            GroupRecords records = (GroupRecords) ApiInvoker.deserialize(response, "", GroupRecords.class);
            groupRecords = records.record;
        }

        @Override
        protected void onCompletion(boolean success) {
            getGroupsTask = null;

            if (success) {
                if (groupListAdapter == null) {
                    // if there is no adapter set, create a new one
                    groupListAdapter = new GroupListAdapter(GroupListActivity.this, groupRecords);
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
                    groupListAdapter.records = groupRecords;
                    groupListAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
