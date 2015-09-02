package com.dreamfactory.sampleapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableContactInfoRecord implements Parcelable {

    private int id;
    private int ordinal;
    private int contact_id;
    private String info_type;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    public ParcelableContactInfoRecord(ContactInfoRecord record) {

        id = record.id;
        ordinal = record.ordinal;
        contact_id = record.contact_id;
        info_type = record.info_type;
        phone = record.phone;
        email = record.email;
        address = record.address;
        city = record.city;
        state = record.state;
        zip = record.zip;
        country = record.country;
    }

    public ContactInfoRecord buildContactInfoRecord() {

        ContactInfoRecord record = new ContactInfoRecord();

        record.id = id;
        record.ordinal = ordinal;
        record.contact_id = contact_id;
        record.info_type = info_type;
        record.phone = phone;
        record.email = email;
        record.address = address;
        record.city = city;
        record.state = state;
        record.zip = zip;
        record.country = country;

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
        dest.writeInt(contact_id);
        dest.writeString(info_type);
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
        contact_id = in.readInt();
        info_type = in.readString();
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
