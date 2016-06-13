package com.dreamfactory.sampleapp.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dreamfactory.sampleapp.R;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;

public class EditInfoViewGroup extends LinearLayout {

    private EditText type;
    private EditText email;
    private EditText phone;
    private EditText address;
    private EditText city;

    private ContactInfoRecord contactInfoRecord;

    public EditInfoViewGroup(Context context, ContactInfoRecord record) {
        super(context);

        contactInfoRecord = record;

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.edit_contact_info_layout, this, true);

        type = (EditText) findViewById(R.id.edit_contact_info_type);
        email = (EditText) findViewById(R.id.edit_contact_info_email);
        phone = (EditText) findViewById(R.id.edit_contact_info_phone);
        address = (EditText) findViewById(R.id.edit_contact_info_address);
        city = (EditText) findViewById(R.id.edit_contact_info_city);

        if(record == null){
            return;
        }

        if(!record.getInfoType().isEmpty()){
            type.setText(record.getInfoType());
        }

        if(!record.getEmail().isEmpty()){
            email.setText(record.getEmail());
        }

        if(!record.getPhone().isEmpty()){
            phone.setText(record.getPhone());
        }

        if(!record.getAddress().isEmpty() && !record.getCity().isEmpty()){
            address.setText(record.getAddress());
            city.setText(record.getCity());
        }
    }

    public boolean mandatoryFieldsOk() {
        boolean valid = !type.getText().toString().isEmpty();

        if(!valid) {
            type.setError(getResources().getString(R.string.error_field_required));
        }

        return valid;
    }

    public ContactInfoRecord.Parcelable buildToContactInfoRecord() {

        // build record and send it back up

        ContactInfoRecord.Parcelable record = new ContactInfoRecord.Parcelable();

        record.setInfoType(type.getText().toString());
        record.setEmail(email.getText().toString());
        record.setPhone(phone.getText().toString());
        record.setAddress(address.getText().toString());
        record.setCity(city.getText().toString());

        if (contactInfoRecord != null) {
            record.setId(contactInfoRecord.getId());
            record.setContactId(contactInfoRecord.getContactId());
        }

        return record;
    }
}
