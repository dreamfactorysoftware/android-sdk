package com.dreamfactory.sampleapp.ui;

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

        if(!record.info_type.isEmpty()){
            type.setText(record.info_type);
        }

        if(!record.email.isEmpty()){
            email.setText(record.email);
        }

        if(!record.phone.isEmpty()){
            phone.setText(record.phone);
        }

        if(!record.address.isEmpty() && !record.city.isEmpty()){
            address.setText(record.address);
            city.setText(record.city);
        }
    }

    public boolean mandatoryFieldsOk() {
        return !type.getText().toString().isEmpty();
    }

    public ContactInfoRecord buildToContactInfoRecord() {

        // build record and send it back up

        ContactInfoRecord record = new ContactInfoRecord();

        record.info_type = type.getText().toString();
        record.email = email.getText().toString();
        record.phone = phone.getText().toString();
        record.address = address.getText().toString();
        record.city = city.getText().toString();

        if (contactInfoRecord != null) {
            record.id = contactInfoRecord.id;
            record.contact_id = contactInfoRecord.contact_id;
        }

        return record;
    }
}
