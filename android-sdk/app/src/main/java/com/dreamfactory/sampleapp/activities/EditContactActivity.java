package com.dreamfactory.sampleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactInfoService;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.customviews.EditInfoViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity responsible for editing contact
 */
public class EditContactActivity extends CreateContactActivity {

    private Resource.Parcelable<ContactInfoRecord.Parcelable> contactInfoRecords;

    private ContactRecord.Parcelable contactRecord;

    private boolean contactInfosCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // CreateContactActivity calls handleIntent, buildViews, handleButtons
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handleIntent(Intent intent){
        contactRecord = intent.getParcelableExtra("contactRecord");
        contactInfoRecords = intent.getParcelableExtra("contactInfoRecords");
    }

    @Override
    protected void buildViews(){
        firstNameEditText.setText(contactRecord.getFirstName());
        lastNameEditText.setText(contactRecord.getLastName());
        twitterEditText.setText(contactRecord.getTwitter());
        skypeEditText.setText(contactRecord.getSkype());
        notesEditText.setText(contactRecord.getNotes());


        editInfoViewGroupList = new ArrayList<>();
        for(ContactInfoRecord record : contactInfoRecords.getResource()){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(linearLayout.getLayoutParams());
            EditInfoViewGroup editInfoViewGroup = new EditInfoViewGroup(this, record);
            linearLayout.addView(editInfoViewGroup, params);
            editInfoViewGroupList.add(editInfoViewGroup);
        }
    }

    @Override
    protected void handleButtons (){
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
                if (mandatoryFieldsOk()) { // require all contacts to have a first and last name
                    Intent intent = buildIntent();

                    if(contactInfosCreated) {
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                } else {
                    Log.w("editContactActivity", "did not fill in mandatory fields");
                }
            }
        });

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ChooseImageActivity.class);
                intent.putExtra("contactId", contactRecord.getId());
                EditContactActivity.this.startActivityForResult(intent, CHOOSE_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            contactRecord.setImageUrl(data.getStringExtra("imageUrl"));
        }
    }

    private boolean mandatoryFieldsOk(){
        // returns true if contact has a first and last name, plus every contact_info record has a tittle
        if(firstNameEditText.getText().toString().isEmpty() || lastNameEditText.getText().toString().isEmpty()){
            return false;
        }

        for(EditInfoViewGroup editInfoViewGroup : editInfoViewGroupList){
            if(!editInfoViewGroup.mandatoryFieldsOk()){
                return false;
            }
        }
        return true;
    }


    private Intent buildIntent(){
        // just to keep onCreate a little cleaner
        final Intent intent = new Intent();

        contactRecord.setFirstName(firstNameEditText.getText().toString());
        contactRecord.setLastName(lastNameEditText.getText().toString());
        contactRecord.setTwitter(twitterEditText.getText().toString());
        contactRecord.setSkype(skypeEditText.getText().toString());
        contactRecord.setNotes(notesEditText.getText().toString());

        // build the info view
        Resource.Parcelable<ContactInfoRecord.Parcelable> tmpContactInfoRecord = new Resource.Parcelable<>();

        Resource<ContactInfoRecord> contactInfoRecords = new Resource<>();
        for(EditInfoViewGroup editInfoViewGroup : editInfoViewGroupList){
            ContactInfoRecord.Parcelable tmp = editInfoViewGroup.buildToContactInfoRecord();

            if(tmp.getId() == null){
                // new records don't have an id
                tmp.setContactId(contactRecord.getId());
                contactInfoRecords.addResource(tmp);
            }

            tmpContactInfoRecord.addResource(tmp);
        }

        if(contactInfoRecords.getResource().size() > 0) {
            // create any new contact info records
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
                                contactInfosCreated = true;

                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Resource<ContactInfoRecord>> call, Throwable t) {
                            showError("Error while creating contact info.", t);
                        }
                    });
        } else {
            contactInfosCreated = true;
        }

        intent.putExtra("contactInfoRecords", (Parcelable) tmpContactInfoRecord);
        intent.putExtra("contactRecord", (Parcelable) contactRecord);

        return intent;
    }
}