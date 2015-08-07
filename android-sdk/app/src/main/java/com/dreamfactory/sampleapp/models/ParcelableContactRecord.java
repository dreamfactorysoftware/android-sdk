package com.dreamfactory.sampleapp.models;


import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableContactRecord implements Parcelable{
    private String firstName;
    private String lastName;
    private int contactId;
    private String notes;
    private String skype;
    private String twitter;
    private String imageUrl;

    public ParcelableContactRecord(ContactRecord record){
        firstName = record.firstName;
        lastName = record.lastName;
        contactId = record.contactId;
        notes = record.notes;
        skype = record.skype;
        twitter = record.twitter;
        imageUrl = record.imageUrl;
    }

    public ContactRecord buildContactRecord (){
        ContactRecord record = new ContactRecord();
        record.firstName = firstName;
        record.lastName = lastName;
        record.contactId = contactId;
        record.notes = notes;
        record.skype = skype;
        record.twitter = twitter;
        record.imageUrl = imageUrl;

        return record;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(contactId);
        dest.writeString(notes);
        dest.writeString(skype);
        dest.writeString(twitter);
        dest.writeString(imageUrl);
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

    private ParcelableContactRecord(Parcel in){
        firstName = in.readString();
        lastName = in.readString();
        contactId = in.readInt();
        notes = in.readString();
        skype = in.readString();
        twitter = in.readString();
        imageUrl = in.readString();
    }
}
