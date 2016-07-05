package com.dreamfactory.sampleapp.models;

import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactRecord extends BaseRecord {

    protected Long id;

    @JsonProperty("first_name")
    protected String firstName;

    @JsonProperty("last_name")
    protected String lastName;

    @JsonProperty("image_url")
    protected String imageUrl;

    protected String twitter;

    protected String skype;

    protected String notes;

    public ContactRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName != null ? firstName : "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName != null ? lastName : "";
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl != null ? imageUrl : "";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTwitter() {
        return twitter != null ? twitter : "";
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getSkype() {
        return skype != null ? skype : "";
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getNotes() {
        return notes != null ? notes : "";
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public static class Parcelable extends ContactRecord implements android.os.Parcelable {

        public Parcelable() {
        }

        public Parcelable(ContactRecord record) {
            this.id = record.id;
            this.firstName = record.firstName;
            this.lastName = record.lastName;
            this.imageUrl = record.imageUrl;
            this.twitter = record.twitter;
            this.skype = record.skype;
            this.notes = record.notes;
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

        public static final Parcelable.Creator<Parcelable> CREATOR = new Parcelable.Creator<Parcelable>() {
            public Parcelable createFromParcel(Parcel in) {
                return new Parcelable(in);
            }

            @Override
            public Parcelable[] newArray(int size) {
                return new Parcelable[size];
            }
        };

        private Parcelable(Parcel in) {
            id = in.readLong();
            firstName = in.readString();
            lastName = in.readString();
            imageUrl = in.readString();
            twitter = in.readString();
            skype = in.readString();
            notes = in.readString();
        }
    }
}
