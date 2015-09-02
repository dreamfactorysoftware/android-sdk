package com.dreamfactory.sampleapp.models;


import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableContactRecord implements Parcelable {

    private int id;
    private String first_name;
    private String last_name;
    private String image_url;
    private String twitter;
    private String skype;
    private String notes;

    public ParcelableContactRecord(ContactRecord record) {

        id = record.id;
        first_name = record.first_name;
        last_name = record.last_name;
        image_url = record.image_url;
        twitter = record.twitter;
        skype = record.skype;
        notes = record.notes;
    }

    public ContactRecord buildContactRecord () {

        ContactRecord record = new ContactRecord();

        record.id = id;
        record.first_name = first_name;
        record.last_name = last_name;
        record.image_url = image_url;
        record.twitter = twitter;
        record.skype = skype;
        record.notes = notes;

        return record;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(image_url);
        dest.writeString(twitter);
        dest.writeString(skype);
        dest.writeString(notes);
    }

    public static final Parcelable.Creator<ParcelableContactRecord> CREATOR = new Parcelable.Creator<ParcelableContactRecord>() {
        public ParcelableContactRecord createFromParcel(Parcel in) {
            return new ParcelableContactRecord(in);
        }

        @Override
        public ParcelableContactRecord[] newArray(int size) {
            return new ParcelableContactRecord[size];
        }
    };

    private ParcelableContactRecord(Parcel in) {

        id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        image_url = in.readString();
        twitter = in.readString();
        skype = in.readString();
        notes = in.readString();
    }
}
