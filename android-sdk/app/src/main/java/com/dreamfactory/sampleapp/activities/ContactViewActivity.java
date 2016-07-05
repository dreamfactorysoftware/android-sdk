package com.dreamfactory.sampleapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamfactory.sampleapp.DreamFactoryApp;
import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactInfoService;
import com.dreamfactory.sampleapp.api.services.ContactService;
import com.dreamfactory.sampleapp.api.services.ImageService;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.FileRecord;

import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.customviews.InfoViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity responsible for showing contact info
 *
 */
public class ContactViewActivity extends BaseActivity {

    private static Integer EDIT_CONTACT_REQUEST = 201;

    private ContactRecord.Parcelable contactRecord;

    private Resource.Parcelable<ContactInfoRecord.Parcelable> contactInfoRecords;

    private LinearLayout linearLayout;

    private List<InfoViewGroup> infoViewGroupList;

    private boolean changedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        changedContact = false;

        contactRecord = getIntent().getParcelableExtra("contactRecord");

        // put the data in the view
        buildContactView();

        linearLayout = (LinearLayout) findViewById(R.id.contactViewTable);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final ContactInfoService service = DreamFactoryAPI.getInstance().getService(ContactInfoService.class);

        service.getContactInfo("contact_id=" + contactRecord.getId()).enqueue(new Callback<Resource<ContactInfoRecord>>() {
            @Override
            public void onResponse(Call<Resource<ContactInfoRecord>> call, Response<Resource<ContactInfoRecord>> response) {
                if(response.isSuccessful()){
                    Resource<ContactInfoRecord> records = response.body();

                    contactInfoRecords = new Resource.Parcelable<>();

                    for(ContactInfoRecord record : records.getResource()) {
                        contactInfoRecords.addResource(new ContactInfoRecord.Parcelable(record));
                    }

                    // build the views once you have the data
                    buildContactInfoViews();
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<Resource<ContactInfoRecord>> call, Throwable t) {
                showError("Error while loading contact info.", t);
            }
        });

        infoViewGroupList = new ArrayList<>();

        final ImageButton backButton = (ImageButton) findViewById(R.id.persistent_back_button);
        final ImageButton editButton = (ImageButton) findViewById(R.id.persistent_edit_button);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.persistent_save_button);
        final ImageButton addButton = (ImageButton) findViewById(R.id.persistent_add_button);

        addButton.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changedContact) {
                    setResult(RESULT_OK);
                } else {
                    setResult(RESULT_CANCELED);
                }

                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditContactActivity.class);
                intent.putExtra("contactRecord", (Parcelable) contactRecord);
                intent.putExtra("contactInfoRecords", (Parcelable) contactInfoRecords);

                ContactViewActivity.this.startActivityForResult(intent, EDIT_CONTACT_REQUEST);
            }
        });

        saveButton.setVisibility(View.INVISIBLE);

        if(!contactRecord.getImageUrl().isEmpty()){
            // only fetch the profile image if the user has one
            getProfileImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_CONTACT_REQUEST && resultCode == RESULT_OK){
            ContactRecord.Parcelable tmpRecord = data.getParcelableExtra("contactRecord");

            Resource.Parcelable<ContactInfoRecord.Parcelable> tmpContactInfoRecords =
                    data.getParcelableExtra("contactInfoRecords");

            // refresh the view with the new data
            for(InfoViewGroup infoViewGroup : infoViewGroupList){
                infoViewGroup.removeFromParent();
            }

            if(!tmpRecord.equals(contactRecord)){
                // only update the contact view if it changed
                contactRecord = tmpRecord;

                final ContactService service = DreamFactoryAPI.getInstance().getService(ContactService.class);

                Resource<ContactRecord> resource = new Resource<>();
                resource.addResource(contactRecord);

                if(contactRecord.getImageUrl() != null) {
                    contactRecord.setImageUrl(DreamFactoryApp.INSTANCE_URL + "files/profile_images/"
                            + contactRecord.getId() + "/" + contactRecord.getImageUrl());
                }

                service.updateContacts(resource).enqueue(new Callback<Resource<ContactRecord>>() {
                    @Override
                    public void onResponse(Call<Resource<ContactRecord>> call, Response<Resource<ContactRecord>> response) {
                        if(response.isSuccessful()){
                            changedContact = true;

                            if(!contactRecord.getImageUrl().isEmpty()) {
                                // re-get the contact profile image
                                getProfileImage();
                            }
                        } else {
                            ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                            onFailure(call, e.toException());
                        }
                    }

                    @Override
                    public void onFailure(Call<Resource<ContactRecord>> call, Throwable t) {
                        showError("Error while updating contact.", t);
                    }
                });
            }

            final Resource<ContactInfoRecord> resource = new Resource<>();

            for(int i = 0; i < contactInfoRecords.getResource().size(); i++){
                // contactInfo only grows
                if(!tmpContactInfoRecords.getResource().get(i).equals(contactInfoRecords.getResource().get(i))) {
                    // if any element changed, add it
                    resource.addResource(tmpContactInfoRecords.getResource().get(i));
                }
            }

            if(tmpContactInfoRecords.getResource().size() != contactInfoRecords.getResource().size()
                    || resource.getResource().size() > 0){
                contactInfoRecords = tmpContactInfoRecords;
                infoViewGroupList.clear();
                buildContactView();
                buildContactInfoViews();
            }

            if(resource.getResource().size() > 0) {
                final ContactInfoService service = DreamFactoryAPI.getInstance()
                        .getService(ContactInfoService.class);

                service.updateContactInfos(resource).enqueue(new Callback<Resource<ContactInfoRecord>>() {
                    @Override
                    public void onResponse(Call<Resource<ContactInfoRecord>> call,
                                           Response<Resource<ContactInfoRecord>> response) {
                        if(response.isSuccessful()){
                            changedContact = true;
                        } else {
                            ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                            onFailure(call, e.toException());
                        }
                    }

                    @Override
                    public void onFailure(Call<Resource<ContactInfoRecord>> call, Throwable t) {
                        showError("Error while updating contact info.", t);
                    }
                });
            }
        }
    }

    private void getProfileImage() {
        final ImageService service = DreamFactoryAPI.getInstance().getService(ImageService.class);

        // Skip possible query params
        if(contactRecord.getImageUrl().contains("?")) {
            contactRecord.setImageUrl(contactRecord.getImageUrl().substring(0,
                    contactRecord.getImageUrl().indexOf("?")));
        }

        // Resolve image name from url
        String imageName = contactRecord.getImageUrl().substring(contactRecord.getImageUrl().lastIndexOf("/") + 1);

        service.getProfileImage(contactRecord.getId(), imageName).enqueue(new Callback<FileRecord>() {
            @Override
            public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                if(response.isSuccessful()){
                    FileRecord fileRecord = response.body();

                    if(fileRecord.getContent() != null) {
                        ConvertToBitmap convertToBitmapTask = new ConvertToBitmap();
                        convertToBitmapTask.execute(fileRecord.getContent());
                    }
                } else {
                    ErrorMessage e = DreamFactoryAPI.getErrorMessage(response);

                    onFailure(call, e.toException());
                }
            }

            @Override
            public void onFailure(Call<FileRecord> call, Throwable t) {
                showError("Error while loading profile image.", t);
            }
        });
    }

    private void buildContactView(){
        TextView nameLabel = (TextView) findViewById(R.id.contactName);
        nameLabel.setText(contactRecord.getFullName());

        TextView skypeLabel = (TextView) findViewById(R.id.skypeLabel);
        skypeLabel.setText(contactRecord.getSkype());

        TextView twitterLabel = (TextView) findViewById(R.id.twitterLabel);
        twitterLabel.setText(contactRecord.getTwitter());

        TextView notesLabel = (TextView) findViewById(R.id.notesLabel);
        notesLabel.setText(contactRecord.getNotes());
    }

    private void buildContactInfoViews(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(linearLayout.getLayoutParams());

        for(ContactInfoRecord record : contactInfoRecords.getResource()){
            InfoViewGroup infoViewGroup = new InfoViewGroup(ContactViewActivity.this, record);
            linearLayout.addView(infoViewGroup, params);
            infoViewGroupList.add(infoViewGroup);
        }
    }

    /**
     * Task responsible for converting base64 string to bitmap on separate thread
     *
     */
    private class ConvertToBitmap extends AsyncTask<String, Integer, Bitmap> {
        protected Bitmap doInBackground(String... contents) {
            byte[] decodedString = Base64.decode(contents[0], Base64.DEFAULT);

            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            final ImageView imageView = (ImageView) findViewById(R.id.contact_view_profile_image);

            if(imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
