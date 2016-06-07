package com.dreamfactory.sampleapp.models;


import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableContactRecord implements Parcelable {

    private Long id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String twitter;
    private String skype;
    private String notes;

    public ParcelableContactRecord(ContactRecord record) {

        id = record.getId();
        firstName = record.getFirstName();
        lastName = record.getLastName();
        imageUrl = record.getImageUrl();
        twitter = record.getTwitter();
        skype = record.getSkype();
        notes = record.getNotes();
    }

    public ContactRecord buildContactRecord () {

        ContactRecord record = new ContactRecord();

        record.setId(id);
        record.setFirstName(firstName);
        record.setLastName(lastName);
        record.setImageUrl(imageUrl);
        record.setTwitter(twitter);
        record.setSkype(skype);
        record.setNotes(notes);

        return record;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(imageUrl);
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

        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        imageUrl = in.readString();
        twitter = in.readString();
        skype = in.readString();
        notes = in.readString();
    }
}
