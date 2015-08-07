package com.dreamfactory.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableContactInfoRecord implements Parcelable{
    private String address;
    private String city;
    private int contactId;
    private String email;
    private int infoId;
    private String infoType;
    private String phone;

    public ParcelableContactInfoRecord(ContactInfoRecord record){
        address = record.address;
        city = record.city;
        contactId = record.contactId;
        email = record.email;
        infoId = record.infoId;
        infoType = record.infoType;
        phone = record.phone;
    }

    public ContactInfoRecord buildContactInfoRecord() {
        ContactInfoRecord record = new ContactInfoRecord();
        record.address = address;
        record.city = city;
        record.contactId = contactId;
        record.email = email;
        record.infoId = infoId;
        record.infoType = infoType;
        record.phone = phone;

        return record;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(city);
        dest.writeInt(contactId);
        dest.writeString(email);
        dest.writeInt(infoId);
        dest.writeString(infoType);
        dest.writeString(phone);
    }

    private ParcelableContactInfoRecord(Parcel in){
        address = in.readString();
        city = in.readString();
        contactId = in.readInt();
        email = in.readString();
        infoId = in.readInt();
        infoType = in.readString();
        phone = in.readString();
    }

    public static final Parcelable.Creator<ParcelableContactInfoRecord> CREATOR = new Parcelable.Creator<ParcelableContactInfoRecord>() {
        public ParcelableContactInfoRecord createFromParcel(Parcel in) {
            return new ParcelableContactInfoRecord(in);
        }

        @Override
        public ParcelableContactInfoRecord[] newArray(int size) {
            return new ParcelableContactInfoRecord[size];
        }
    };
}
