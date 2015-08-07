package com.dreamfactory.sampleapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactInfoRecords;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ParcelableContactInfoRecords;
import com.dreamfactory.sampleapp.models.ParcelableContactRecord;
import dfapi.BaseAsyncRequest;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dfapi.ApiException;
import dfapi.ApiInvoker;

public class ContactViewActivity extends Activity {

    private ContactRecord contactRecord;
    private ContactInfoRecords contactInfoRecords;

    private LinearLayout linearLayout;

    private List<InfoViewGroup> infoViewGroupList;

    private GetContactInfoTask getContactInfoTask;
    private UpdateContactTask updateContactTask;
    private UpdateContactInfoTask updateContactInfoTask;
    private GetProfileImageFromServerTask getProfileImageFromServerTask;

    private boolean changedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        changedContact = false;

        final Intent intent = getIntent();

        ParcelableContactRecord parcelable = intent.getParcelableExtra("contactRecord");
        contactRecord = parcelable.buildContactRecord();

        // put the data in the view
        buildContactView();

        linearLayout = (LinearLayout) findViewById(R.id.contactViewTable);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        getContactInfoTask = new GetContactInfoTask();

        getContactInfoTask.execute();

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
                ParcelableContactRecord parcelableContactRecord =
                        new ParcelableContactRecord(contactRecord);

                ParcelableContactInfoRecords parcelableContactInfoRecords =
                        new ParcelableContactInfoRecords(contactInfoRecords);
                intent.putExtra("contactRecord", parcelableContactRecord);
                intent.putExtra("contactInfoRecords", parcelableContactInfoRecords);
                tmp.startActivityForResult(intent, 1);
            }
        });

        save_button.setVisibility(View.INVISIBLE);

        if(contactRecord.imageUrl != null && !contactRecord.imageUrl.isEmpty()){
            // only fetch the profile image if the user has one
            getProfileImageFromServerTask = new GetProfileImageFromServerTask();
            getProfileImageFromServerTask.execute();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            ParcelableContactRecord parcelableContactRecord =
                    data.getParcelableExtra("contactRecord");
            ContactRecord tmpRecord = parcelableContactRecord.buildContactRecord();

            ParcelableContactInfoRecords parcelableContactInfoRecords =
                    data.getParcelableExtra("contactInfoRecords");
            ContactInfoRecords tmpContactInfoRecords =
                    parcelableContactInfoRecords.buildContactInfoRecords();

            // refresh the view with the new data
            for(InfoViewGroup infoViewGroup : infoViewGroupList){
                infoViewGroup.removeFromParent();
            }

            if(!tmpRecord.equals(contactRecord)){
                // only update the contact view if it changed
                contactRecord = tmpRecord;
                updateContactTask = new UpdateContactTask();
                updateContactTask.execute();

                if(contactRecord.imageUrl != null && !contactRecord.imageUrl.isEmpty()) {
                    // re-get the contact profile image
                    getProfileImageFromServerTask = new GetProfileImageFromServerTask();
                    getProfileImageFromServerTask.execute();
                }
            }

            ContactInfoRecords toUpdate = new ContactInfoRecords();
            for(int i = 0; i < contactInfoRecords.record.size(); i++){
                // contactInfo only grows
                if(!tmpContactInfoRecords.record.get(i).equals(contactInfoRecords.record.get(i))) {
                    // if any element changed, add it
                    toUpdate.record.add(tmpContactInfoRecords.record.get(i));
                }
            }
            if(tmpContactInfoRecords.record.size() != contactInfoRecords.record.size()
                    || toUpdate.record.size() > 0){
                contactInfoRecords = tmpContactInfoRecords;
                infoViewGroupList.clear();
                buildContactView();
                buildContactInfoViews();
            }
            if(toUpdate.record.size() > 0){
                updateContactInfoTask = new UpdateContactInfoTask(toUpdate);
                updateContactInfoTask.execute();
            }
        }
    }

    private void buildContactView(){
        TextView nameLabel = (TextView) findViewById(R.id.contactName);
        nameLabel.setText(contactRecord.firstName + " " + contactRecord.lastName);

        TextView skypeLabel = (TextView) findViewById(R.id.skypeLabel);
        skypeLabel.setText(contactRecord.skype);

        TextView twitterLabel = (TextView) findViewById(R.id.twitterLabel);
        twitterLabel.setText(contactRecord.twitter);

        TextView notesLabel = (TextView) findViewById(R.id.notesLabel);
        notesLabel.setText(contactRecord.notes);
    }

    private void buildContactInfoViews(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(linearLayout.getLayoutParams());
        for(ContactInfoRecord record : contactInfoRecords.record){
            InfoViewGroup infoViewGroup = new InfoViewGroup(ContactViewActivity.this, record);
            linearLayout.addView(infoViewGroup, params);
            infoViewGroupList.add(infoViewGroup);
        }
    }

    public class GetContactInfoTask extends BaseAsyncRequest{

        private ContactInfoRecords records;

        public GetContactInfoTask() {
            callerName = "getContactInfoTask";

            serviceName = "db";
            endPoint = "contact_info";

            verb = "GET";

            queryParams = new HashMap<>();
            // filter to only the contact_info records related to the contact
            queryParams.put("filter", "contactId=" + contactRecord.contactId);

            // include application name and sessionId
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }

        @Override
        protected void processResponse(String response) throws ApiException {
            // results come back as an array of contact_info records
            // form is:
            // {
            //      "records":[
            //          { contactInfoRecord }
            //      ]
            // }
           records = (ContactInfoRecords) ApiInvoker.deserialize(response, "", ContactInfoRecords.class);
        }

        @Override
        protected void onCompletion(boolean success) {
            getContactInfoTask = null;
            if(success){
                for(ContactInfoRecord record : records.record) {
                    // make sure none of the fields are null
                    record.infoType = record.getNonNull(record.infoType);

                    record.city = record.getNonNull(record.city);
                    record.address = record.getNonNull(record.address);

                    record.phone = record.getNonNull(record.phone);
                    record.email = record.getNonNull(record.email);
                }

                contactInfoRecords = new ContactInfoRecords();
                contactInfoRecords.record = records.record;

                // build the views once you have the data
                buildContactInfoViews();
            }
        }
    }

    private class UpdateContactTask extends BaseAsyncRequest {
        @Override
        protected void doSetup() throws ApiException{
            callerName = "updateContactTask";

            serviceName = "db";
            endPoint = "contacts";

            verb = "PATCH";

            // send the contact record in the body
            requestString = ApiInvoker.serialize(contactRecord);

            // include sessionId
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                changedContact = true;
            }
            updateContactTask = null;
        }
    }

    private class UpdateContactInfoTask extends BaseAsyncRequest {
        private ContactInfoRecords records;
        public UpdateContactInfoTask(ContactInfoRecords toUpdate){ records = toUpdate; }

        @Override
        protected void doSetup() throws ApiException {
            callerName = "updateContactInfoTask";

            serviceName = "db";
            endPoint = "contact_info";

            verb = "PATCH";

            // body is array of records to patch
            requestString = ApiInvoker.serialize(records);

            // include application name and session_id
            applicationName = AppConstants.APP_NAME;
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                changedContact = true;
            }
            updateContactInfoTask = null;
        }
    }

    private class GetProfileImageFromServerTask extends BaseAsyncRequest {
        private Bitmap bitmap;
        @Override
        protected void doSetup() throws ApiException, JSONException {
            callerName = "getImageFromFile";
            serviceName = "files";
            applicationName = AppConstants.APP_NAME; // app name used in file path
            verb = "GET";
            // build rest path for request, form is <url to DSP>/rest/files/container/application/<folder path>/filename
            // here the folder path is profile_images/contactId/
            // the file path does not end in a '/' because we are targeting a file not a folder
            String containerName = "applications";
            String folderPath = "profile_images/" + contactRecord.contactId;
            String fileName = contactRecord.imageUrl;
            endPoint = containerName + "/" + applicationName + "/" + folderPath + "/" + fileName;

            queryParams = new HashMap<>();
            // don't include the file properties
            queryParams.put("include_properties", "1");
            // include the content
            queryParams.put("content", "1");
            // give us a download
            queryParams.put("download", "1");

            // need to include session_id
            sessionId = PrefUtil.getString(getApplicationContext(), AppConstants.SESSION_ID);
        }

        @Override
        protected void processResponse(String response) throws ApiException, JSONException {
            JSONObject jsonObject = new JSONObject(response);
            String imageData = jsonObject.getString("content");

            // files come back as Base64 strings
            byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }

        @Override
        protected void onCompletion(boolean success) {
            if(success){
                ImageView imageView = (ImageView) findViewById(R.id.contact_view_profile_image);
                imageView.setImageBitmap(bitmap);
            }
            getProfileImageFromServerTask = null;
        }
    }
}
