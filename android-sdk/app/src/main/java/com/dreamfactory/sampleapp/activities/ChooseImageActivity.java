package com.dreamfactory.sampleapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.adapters.ProfileImageChooserAdapter;

import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ImageService;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.FileRecord;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.utils.ImageUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity used for displaying profile images
 * related to user and choosing one as profile image
 *
 */
public class ChooseImageActivity extends BaseActivity {

    protected static final int CHOOSE_IMAGE_REQUEST = 202;

    protected static final int REQUEST_READ_EXTERNAL_STORAGE_PERMISSION = 121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        refresh();

        final ImageButton backButton = (ImageButton) findViewById(R.id.persistent_back_button);
        final ImageButton editButton = (ImageButton) findViewById(R.id.persistent_edit_button);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.persistent_save_button);
        final ImageButton addButton = (ImageButton) findViewById(R.id.persistent_add_button);
        final Button uploadPhotoButton = (Button) findViewById(R.id.upload_photo);

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

        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    private void takePicture() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_READ_EXTERNAL_STORAGE_PERMISSION);
                return;
            }
        }

        ChooseImageActivity.this.startActivityForResult(Intent.createChooser(
                new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                "Choose an image"), CHOOSE_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                }
        }
    }

    private void refresh() {
        Intent intent = getIntent();

        final Long contactId = intent.getLongExtra("contactId", 0);

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

                        // Skip folder not found error
                        if(e.getError().getCode() != 404L) {
                            onFailure(call, e.toException());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Resource<FileRecord>> call, Throwable t) {
                    showError("Error while loading profile images.", t);
                }
            });
        }
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case CHOOSE_IMAGE_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri uri = imageReturnedIntent.getData();

                    final String profileImagePath = ImageUtil.getImagePath(uri, getContentResolver());

                    final ImageService imageService = DreamFactoryAPI.getInstance().getService(ImageService.class);

                    final Long contactId = getIntent().getLongExtra("contactId", 0);

                    imageService.addFolder(contactId).enqueue(new Callback<FileRecord>() {
                        @Override
                        public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                            if(response.isSuccessful()){
                                addProfileImage(contactId, profileImagePath);
                            } else {
                                ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                                if(e.getError().getCode() == 400L) {
                                    addProfileImage(contactId, profileImagePath);
                                } else {
                                    onFailure(call, e.toException());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<FileRecord> call, Throwable t) {
                            showError("Error while creating contact folder.", t);
                        }
                    });
                }
        }
    }

    private void addProfileImage(Long contactId, String profileImagePath) {
        final String imageName = profileImagePath.substring(profileImagePath.lastIndexOf("/") + 1);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), new File(profileImagePath));

        final ImageService imageService = DreamFactoryAPI.getInstance().getService(ImageService.class);

        imageService.addProfileImage(contactId, imageName, requestBody).enqueue(new Callback<FileRecord>() {
            @Override
            public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                if(!response.isSuccessful()) {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                } else {
                    refresh();
                }
            }

            @Override
            public void onFailure(Call<FileRecord> call, Throwable t) {
                showError("Error while uploading image.", t);
            }
        });
    }
}
