package com.dreamfactory.sampleapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.dreamfactory.sampleapp.DreamFactoryApp;
import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.ContactInfoService;
import com.dreamfactory.sampleapp.api.services.ContactService;
import com.dreamfactory.sampleapp.api.services.ImageService;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactRecord;

import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.FileRecord;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.customviews.EditInfoViewGroup;
import com.dreamfactory.sampleapp.utils.ImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity responsible for creating new contact
 */
public class CreateContactActivity extends BaseActivity {

    protected static final int CHOOSE_IMAGE_REQUEST = 202;

    protected EditText firstNameEditText;
    protected EditText lastNameEditText;
    protected EditText twitterEditText;
    protected EditText skypeEditText;
    protected EditText notesEditText;
    protected Button chooseImageButton;

    protected Button addContactInfoButton;

    protected LinearLayout linearLayout;

    protected List<EditInfoViewGroup> editInfoViewGroupList;

    private Long groupId;

    private String profileImagePath;

    private boolean contactInfosCreationFinished = false;
    private boolean contactGroupCreationFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // since this gets extended, only do fairly generic stuff in the constructor
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        handleIntent(intent);

        firstNameEditText = (EditText) findViewById(R.id.edit_contact_first_name);
        lastNameEditText = (EditText) findViewById(R.id.edit_contact_last_name);
        twitterEditText = (EditText) findViewById(R.id.edit_contact_twitter);
        skypeEditText = (EditText) findViewById(R.id.edit_contact_skype);
        notesEditText = (EditText) findViewById(R.id.edit_contact_notes);
        chooseImageButton = (Button) findViewById(R.id.edit_contact_info_change_photo);

        addContactInfoButton = (Button) findViewById(R.id.edit_contact_add_info);
        addContactInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditInfoViewGroup editInfoViewGroup = new EditInfoViewGroup(CreateContactActivity.this, null);
                editInfoViewGroupList.add(editInfoViewGroup);
                linearLayout.addView(editInfoViewGroup);

                final ScrollView scrollView = (ScrollView) findViewById(R.id.edit_contact_scroll_view);
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        editInfoViewGroupList = new ArrayList<>();
        linearLayout = (LinearLayout) findViewById(R.id.edit_contact_linear_layout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        buildViews();
        handleButtons();
    }

    protected void handleIntent(Intent intent) {
        // contacts are created from contactListActivity as part of a group
        groupId = intent.getLongExtra("contactGroupId", 0);
    }

    protected void handleButtons() {
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ContactRecord contactRecord = new ContactRecord();
                contactRecord.setFirstName(firstNameEditText.getText().toString());
                contactRecord.setLastName(lastNameEditText.getText().toString());
                contactRecord.setSkype(skypeEditText.getText().toString());
                contactRecord.setTwitter(twitterEditText.getText().toString());
                contactRecord.setNotes(notesEditText.getText().toString());

                final String imageName;

                if(profileImagePath != null && !profileImagePath.isEmpty()){
                    imageName = profileImagePath.substring(profileImagePath.lastIndexOf("/") + 1);

                    contactRecord.setImageUrl(DreamFactoryApp.INSTANCE_URL + "files/profile_images/" + contactRecord.getId() + "/" + imageName);
                } else {
                    imageName = null;
                }

                final Resource<ContactRecord> resource = new Resource<>();
                resource.addResource(contactRecord);

                final ContactService contactService = DreamFactoryAPI.getInstance().getService(ContactService.class);
                final ContactGroupService contactGroupService = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);
                final ImageService imageService = DreamFactoryAPI.getInstance().getService(ImageService.class);

                contactService.createContacts(resource).enqueue(new Callback<Resource<ContactRecord>>() {
                    @Override
                    public void onResponse(Call<Resource<ContactRecord>> call, Response<Resource<ContactRecord>> response) {
                        if(response.isSuccessful()){
                            final ContactRecord contactRecord = response.body().getResource().get(0);

                            // build a list of contact_info records to create
                            Resource<ContactInfoRecord> contactInfoRecords = new Resource<>();

                            for (EditInfoViewGroup viewGroup : editInfoViewGroupList) {
                                ContactInfoRecord contactInfoRecord = viewGroup.buildToContactInfoRecord();
                                // need to include the contactId
                                contactInfoRecord.setContactId(contactRecord.getId());

                                contactInfoRecords.addResource(contactInfoRecord);
                            }

                            createContactInfos(contactInfoRecords);

                            final Resource<ContactsRelationalRecord> resource = new Resource<>();

                            ContactsRelationalRecord relation = new ContactsRelationalRecord();
                            relation.setContactGroupId(groupId);
                            relation.setContactId(contactRecord.getId());

                            resource.addResource(relation);

                            contactGroupService.addGroupContacts(resource).enqueue(new Callback<Resource<ContactsRelationalRecord>>() {
                                @Override
                                public void onResponse(Call<Resource<ContactsRelationalRecord>> call, Response<Resource<ContactsRelationalRecord>> response) {
                                    if(!response.isSuccessful()){
                                        ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                                        onFailure(call, e.toException());
                                    } else {
                                        if(profileImagePath != null && !profileImagePath.isEmpty()){
                                            imageService.addFolder(contactRecord.getId()).enqueue(new Callback<FileRecord>() {
                                                @Override
                                                public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                                                    if(response.isSuccessful()){
                                                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), new File(profileImagePath));

                                                        imageService.addProfileImage(contactRecord.getId(), imageName, requestBody).enqueue(new Callback<FileRecord>() {
                                                            @Override
                                                            public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                                                                if(!response.isSuccessful()) {
                                                                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                                                                    onFailure(call, e.toException());
                                                                } else if(contactInfosCreationFinished){
                                                                    contactGroupCreationFinished = true;

                                                                    setResult(RESULT_OK);

                                                                    finish();
                                                                } else {
                                                                    contactGroupCreationFinished = true;
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<FileRecord> call, Throwable t) {
                                                                showError("Error while uploading image.", t);
                                                            }
                                                        });
                                                    } else {
                                                        ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                                                        onFailure(call, e.toException());
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<FileRecord> call, Throwable t) {
                                                    showError("Error while creating contact folder.", t);
                                                }
                                            });
                                        } else if(contactInfosCreationFinished){
                                            contactGroupCreationFinished = true;

                                            setResult(RESULT_OK);

                                            finish();
                                        } else {
                                            contactGroupCreationFinished = true;
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Resource<ContactsRelationalRecord>> call, Throwable t) {
                                    showError("Error while assigning contact to contact group.", t);
                                }
                            });
                        } else {
                            ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                            onFailure(call, e.toException());
                        }
                    }

                    @Override
                    public void onFailure(Call<Resource<ContactRecord>> call, Throwable t) {
                        showError("Error while creating contact.", t);

                        setResult(RESULT_CANCELED);
                    }
                });
            }
        });

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateContactActivity.this.startActivityForResult(Intent.createChooser(
                        new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                        "Choose an image"), CHOOSE_IMAGE_REQUEST);
            }
        });
    }

    protected void createContactInfos(Resource<ContactInfoRecord> contactInfoRecords) {
        if(contactInfoRecords.getResource().size() > 0) {
            final ContactInfoService contactInfoService = DreamFactoryAPI.getInstance()
                    .getService(ContactInfoService.class);

            contactInfoService.createContactInfos(contactInfoRecords)
                    .enqueue(new Callback<Resource<ContactInfoRecord>>() {
                @Override
                public void onResponse(Call<Resource<ContactInfoRecord>> call,
                                       Response<Resource<ContactInfoRecord>> response) {
                    if(!response.isSuccessful()){
                        ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                        onFailure(call, e.toException());
                    } else {
                        contactInfosCreationFinished = true;
                    }

                    if(contactInfosCreationFinished && contactGroupCreationFinished) {
                        setResult(RESULT_OK);

                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Resource<ContactInfoRecord>> call, Throwable t) {
                    showError("Error while creating contact info.", t);
                }
            });
        } else {
            contactInfosCreationFinished = true;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case CHOOSE_IMAGE_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri uri = imageReturnedIntent.getData();

                    profileImagePath = ImageUtil.getImagePath(uri, getContentResolver());
                }
        }
    }

    protected void buildViews() {
        firstNameEditText = (EditText) findViewById(R.id.edit_contact_first_name);
        lastNameEditText = (EditText) findViewById(R.id.edit_contact_last_name);
        twitterEditText = (EditText) findViewById(R.id.edit_contact_twitter);
        skypeEditText = (EditText) findViewById(R.id.edit_contact_skype);
        notesEditText = (EditText) findViewById(R.id.edit_contact_notes);
    }
}
