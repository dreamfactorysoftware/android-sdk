package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactInfoRecords;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.Resource;

import java.util.ArrayList;

public class EditContactActivity extends CreateContactActivity {

    private Resource.Parcelable<ContactInfoRecord.Parcelable> contactInfoRecords;

    private ContactRecord.Parcelable contactRecord;

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

//        chooseImageButton.setTag(this);
//        chooseImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Activity activity = (Activity) v.getTag();
//                Intent intent = new Intent(activity, ChooseImageActivity.class);
//                intent.putExtra("contactId", contactRecord.id);
//                activity.startActivityForResult(intent, 2);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
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
        Intent intent = new Intent();

        contactRecord.setFirstName(firstNameEditText.getText().toString());
        contactRecord.setLastName(lastNameEditText.getText().toString());
        contactRecord.setTwitter(twitterEditText.getText().toString());
        contactRecord.setSkype(skypeEditText.getText().toString());
        contactRecord.setNotes(notesEditText.getText().toString());


        // build the info view
        Resource.Parcelable<ContactInfoRecord.Parcelable> tmpContactInfoRecord = new Resource.Parcelable<>();

        ContactInfoRecords contactInfoRecords = new ContactInfoRecords();
        for(EditInfoViewGroup editInfoViewGroup : editInfoViewGroupList){
            ContactInfoRecord.Parcelable tmp = editInfoViewGroup.buildToContactInfoRecord();

            if(tmp.getId() == 0){
                // new records don't have an id
                tmp.setId(contactRecord.getId());
                contactInfoRecords.record.add(tmp);
            }
            tmpContactInfoRecord.addResource(tmp);
        }

        if(contactInfoRecords.record.size() > 0) {
            // create any new contact info records
            AddContactInfoTask addContactInfoTask = new AddContactInfoTask(contactInfoRecords);
            addContactInfoTask.execute();
        }

        intent.putExtra("contactInfoRecords", (Parcelable) tmpContactInfoRecord);
        intent.putExtra("contactRecord", (Parcelable) contactRecord);
        return intent;
    }
}