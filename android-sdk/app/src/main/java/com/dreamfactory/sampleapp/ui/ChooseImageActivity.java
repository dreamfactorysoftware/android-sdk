package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
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
                public void onResponse(Call<Resource<FileRecord>> call, Response<Resource<FileRecord>> response) {
                    if(response.isSuccessful()){
                        ProfileImageChooserAdapter profileImageChooserAdapter =
                                new ProfileImageChooserAdapter(ChooseImageActivity.this, response.body().getResource());

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
}
