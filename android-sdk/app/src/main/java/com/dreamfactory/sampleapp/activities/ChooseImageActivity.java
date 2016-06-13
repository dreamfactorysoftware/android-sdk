package com.dreamfactory.sampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.adapters.ProfileImageChooserAdapter;

import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ImageService;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.FileRecord;
import com.dreamfactory.sampleapp.models.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity used for displaying profile images
 * related to user and choosing one as profile image
 *
 */
public class ChooseImageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        Intent intent = getIntent();

        Long contactId = intent.getLongExtra("contactId", 0);

        if(contactId == 0){
            logError("No contact id sent");
        } else{
            final ImageService service = DreamFactoryAPI.getInstance().getService(ImageService.class);

            service.getProfileImages(contactId).enqueue(new Callback<Resource<FileRecord>>() {
                @Override
                public void onResponse(Call<Resource<FileRecord>> call,
                                       Response<Resource<FileRecord>> response) {
                    if(response.isSuccessful()){
                        ProfileImageChooserAdapter profileImageChooserAdapter =
                                new ProfileImageChooserAdapter(ChooseImageActivity.this,
                                        response.body().getResource());

                        ListView listView = (ListView) findViewById(R.id.profile_image_list);
                        listView.setAdapter(profileImageChooserAdapter);
                    } else {
                        ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                        onFailure(call, e.toException());
                    }
                }

                @Override
                public void onFailure(Call<Resource<FileRecord>> call, Throwable t) {
                    showError("Error while loading profile images.", t);
                }
            });
        }

        final ImageButton backButton = (ImageButton) findViewById(R.id.persistent_back_button);
        final ImageButton editButton = (ImageButton) findViewById(R.id.persistent_edit_button);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.persistent_save_button);
        final ImageButton addButton = (ImageButton) findViewById(R.id.persistent_add_button);

        addButton.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        editButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
    }
}
