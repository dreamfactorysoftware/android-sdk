package com.dreamfactory.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableContactInfoRecord implements Parcelable {

    private int id;
    private int ordinal;
    private int contactId;
    private String infoIype;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    public ParcelableContactInfoRecord(ContactInfoRecord record) {

        id = record.getId();
        ordinal = record.getOrdinal();
        contactId = record.getContactId();
        infoIype = record.getInfoType();
        phone = record.getPhone();
        email = record.getEmail();
        address = record.getAddress();
        city = record.getCity();
        state = record.getState();
        zip = record.getZip();
        country = record.getCountry();
    }

    public ContactInfoRecord buildContactInfoRecord() {

        ContactInfoRecord record = new ContactInfoRecord();

        record.setId(id);
        record.setOrdinal(ordinal);
        record.setContactId(contactId);
        record.setInfoType(infoIype);
        record.setPhone(phone);
        record.setEmail(email);
        record.setAddress(address);
        record.setCity(city);
        record.setState(state);
        record.setZip(zip);
        record.setCountry(country);

        return record;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeInt(ordinal);
        dest.writeInt(contactId);
        dest.writeString(infoIype);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeString(country);
    }

    private ParcelableContactInfoRecord(Parcel in) {

        id = in.readInt();
        ordinal = in.readInt();
        contactId = in.readInt();
        infoIype = in.readString();
        phone = in.readString();
        email = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        country = in.readString();
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
