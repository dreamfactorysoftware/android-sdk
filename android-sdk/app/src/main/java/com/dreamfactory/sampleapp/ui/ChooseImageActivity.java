package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.adapters.ProfileImageChooserAdapter;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;
import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dfapi.ApiException;
import dfapi.ApiInvoker;

public class ChooseImageActivity extends Activity {

    private int contactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        Intent intent = getIntent();
        contactId = intent.getIntExtra("contactId", 0);

        if(contactId == 0){
            Log.e("chooseImageActivity", "no contact id sent");
        }
        else{
            GetImageListTask getImageListTask = new GetImageListTask();
            getImageListTask.execute();
        }

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
                setResult(Activity.RESULT_CANCELED);
                tmp.finish();
            }
        });

        edit_button.setVisibility(View.INVISIBLE);

        save_button.setVisibility(View.INVISIBLE);
    }

    private class GetImageListTask extends BaseAsyncRequest{
        private List<String> file_list = new ArrayList<>();
        @Override
        protected void doSetup() throws ApiException, JSONException {

            verb = "GET";
            serviceName = "files";
            applicationApiKey = AppConstants.API_KEY;
            // the file path ends in a '/' because we are targeting a folder
            endPoint = "profile_images/" + contactId + "/";
            // don't get any folders back in response, only get files
            queryParams = new HashMap<>();
            queryParams.put("include_folders", "0");
            queryParams.put("include_files", "1");
            sessionToken = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_TOKEN);
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            /*
             * structure is:
             *  {
             *      ... folder info ...
             *      "file" : [
             *          { ... file info ... },
             *          { ... }
             *      ]
             *  ]
             *
             */
            JsonNode jsonArray = (JsonNode) ApiInvoker.deserialize(response, "", JsonNode.class);
            for(JsonNode node : jsonArray.get("file")){
                file_list.add(node.get("name").asText());
            }
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                ProfileImageChooserAdapter profileImageChooserAdapter =
                        new ProfileImageChooserAdapter(ChooseImageActivity.this, file_list);

                ListView listView = (ListView) findViewById(R.id.profile_image_list);
                listView.setAdapter(profileImageChooserAdapter);
            }
        }
    }
}
