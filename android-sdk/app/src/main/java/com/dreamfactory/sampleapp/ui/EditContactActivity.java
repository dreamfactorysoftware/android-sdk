package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactInfoRecords;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ParcelableContactInfoRecords;
import com.dreamfactory.sampleapp.models.ParcelableContactRecord;

import java.util.ArrayList;

public class EditContactActivity extends CreateContactActivity {

    private ContactInfoRecords contactInfoRecords;

    private ContactRecord contactRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // CreateContactActivity calls handleIntent, buildViews, handleButtons
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handleIntent(Intent intent){
        ParcelableContactRecord parcelable = intent.getParcelableExtra("contactRecord");
        contactRecord = parcelable.buildContactRecord();

        ParcelableContactInfoRecords parcelableContactInfoRecords =
                intent.getParcelableExtra("contactInfoRecords");
        contactInfoRecords = parcelableContactInfoRecords.buildContactInfoRecords();
    }

    @Override
    protected void buildViews(){
        firstNameEditText.setText(contactRecord.firstName);
        lastNameEditText.setText(contactRecord.lastName);
        twitterEditText.setText(contactRecord.twitter);
        skypeEditText.setText(contactRecord.skype);
        notesEditText.setText(contactRecord.notes);


        editInfoViewGroupList = new ArrayList<>();
        for(ContactInfoRecord record : contactInfoRecords.record){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(linearLayout.getLayoutParams());
            EditInfoViewGroup editInfoViewGroup = new EditInfoViewGroup(this, record);
            linearLayout.addView(editInfoViewGroup, params);
            editInfoViewGroupList.add(editInfoViewGroup);
        }
    }

    @Override
    protected void handleButtons (){
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

        save_button.setTag(this);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity tmp = (Activity) v.getTag();
                if (mandatoryFieldsOk()) { // require all contacts to have a first and last name
                    setResult(Activity.RESULT_OK, buildIntent());
                    tmp.finish();
                } else {
                    Log.w("editContactActivity", "did not fill in mandatory fields");
                }
            }
        });

        chooseImageButton.setTag(this);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) v.getTag();
                Intent intent = new Intent(activity, ChooseImageActivity.class);
                intent.putExtra("contactId", contactRecord.contactId);
                activity.startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            contactRecord.imageUrl = data.getStringExtra("imageUrl");
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
        Intent intent = new Intent();

        contactRecord.firstName = firstNameEditText.getText().toString();
        contactRecord.lastName = lastNameEditText.getText().toString();
        contactRecord.twitter = twitterEditText.getText().toString();
        contactRecord.skype = skypeEditText.getText().toString();
        contactRecord.notes = notesEditText.getText().toString();

        ParcelableContactRecord parcelableContactRecord =
                new ParcelableContactRecord(contactRecord);

        // build the info view
        ContactInfoRecords tmpContactInfoRecord = new ContactInfoRecords();

        ContactInfoRecords contactInfoRecords = new ContactInfoRecords();
        for(EditInfoViewGroup editInfoViewGroup : editInfoViewGroupList){
            ContactInfoRecord tmp = editInfoViewGroup.buildToContactInfoRecord();
            if(tmp.contactId == 0){
                // new records don't have a contactId
                tmp.contactId = contactRecord.contactId;
                contactInfoRecords.record.add(tmp);
            }
            tmpContactInfoRecord.record.add(tmp);
        }

        if(contactInfoRecords.record.size() > 0) {
            // create any new contact info records
            AddContactInfoTask addContactInfoTask = new AddContactInfoTask(contactInfoRecords);
            addContactInfoTask.execute();
        }

        ParcelableContactInfoRecords parcelableContactInfoRecords =
                new ParcelableContactInfoRecords(tmpContactInfoRecord);

        intent.putExtra("contactInfoRecords", parcelableContactInfoRecords);
        intent.putExtra("contactRecord", parcelableContactRecord);
        return intent;
    }
}