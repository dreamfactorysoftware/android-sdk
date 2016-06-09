package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactViewActivity extends BaseActivity {

    private ContactRecord contactRecord;

    private Resource.Parcelable<ContactInfoRecord.Parcelable> contactInfoRecords;

    private LinearLayout linearLayout;

    private List<InfoViewGroup> infoViewGroupList;

    private boolean changedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        changedContact = false;

        final Intent intent = getIntent();

        final ContactRecord.Parcelable contactRecord = intent.getParcelableExtra("contactRecord");

        // put the data in the view
        buildContactView();

        linearLayout = (LinearLayout) findViewById(R.id.contactViewTable);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final ContactInfoService service = DreamFactoryAPI.getInstance().getService(ContactInfoService.class);

        service.getContactInfo("contact_id=" + contactRecord.getId()).enqueue(new Callback<Resource.Parcelable<ContactInfoRecord.Parcelable>>() {
            @Override
            public void onResponse(Call<Resource.Parcelable<ContactInfoRecord.Parcelable>> call, Response<Resource.Parcelable<ContactInfoRecord.Parcelable>> response) {
                if(response.isSuccessful()){
                    contactInfoRecords = response.body();
                    // build the views once you have the data
                    buildContactInfoViews();
                }
            }

            @Override
            public void onFailure(Call<Resource.Parcelable<ContactInfoRecord.Parcelable>> call, Throwable t) {

            }
        });

        infoViewGroupList = new ArrayList<>();

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
                if (changedContact) {
                    setResult(Activity.RESULT_OK);
                } else {
                    setResult(Activity.RESULT_CANCELED);
                }
                tmp.finish();
            }
        });

        edit_button.setTag(this);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tmp = (Activity) v.getTag();
                Intent intent = new Intent(tmp, EditContactActivity.class);
                intent.putExtra("contactRecord", (Parcelable) contactRecord);
                intent.putExtra("contactInfoRecords", (Parcelable) contactInfoRecords);
                tmp.startActivityForResult(intent, 1);
            }
        });

        save_button.setVisibility(View.INVISIBLE);

        if(!contactRecord.getImageUrl().isEmpty()){
            // only fetch the profile image if the user has one
            getProfileImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            ContactRecord.Parcelable tmpRecord =
                    data.getParcelableExtra("contactRecord");

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

                service.updateContacts(resource).enqueue(new Callback<Resource<ContactRecord>>() {
                    @Override
                    public void onResponse(Call<Resource<ContactRecord>> call, Response<Resource<ContactRecord>> response) {
                        if(response.isSuccessful()){
                            changedContact = true;
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

                if(!contactRecord.getImageUrl().isEmpty()) {
                    // re-get the contact profile image
                    getProfileImage();
                }
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
                final ContactInfoService service = DreamFactoryAPI.getInstance().getService(ContactInfoService.class);

                service.updateContactInfos(resource).enqueue(new Callback<Resource<ContactInfoRecord>>() {
                    @Override
                    public void onResponse(Call<Resource<ContactInfoRecord>> call, Response<Resource<ContactInfoRecord>> response) {
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

        service.getProfileImage(contactRecord.getId(), contactRecord.getImageUrl()).enqueue(new Callback<FileRecord>() {
            @Override
            public void onResponse(Call<FileRecord> call, Response<FileRecord> response) {
                if(response.isSuccessful()){
                    FileRecord fileRecord = response.body();

                    //TODO: Move to task
                    byte[] decodedString = Base64.decode(fileRecord.getContent(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    ImageView imageView = (ImageView) findViewById(R.id.contact_view_profile_image);
                    imageView.setImageBitmap(bitmap);
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
        nameLabel.setText(contactRecord.getFirstName() + " " + contactRecord.getLastName());

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
}
