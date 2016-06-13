package com.dreamfactory.sampleapp.models;

import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactInfoRecord extends BaseRecord {

    protected Long id;

    protected Long ordinal = 0L;

    @JsonProperty("contact_id")
    protected Long contactId;

    @JsonProperty("info_type")
    protected String infoType = "";

    protected String phone = "";

    protected String email = "";

    protected String address = "";

    protected String city = "";

    protected String state = "";

    protected String zip = "";

    protected String country = "";

    public ContactInfoRecord(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Long ordinal) {
        this.ordinal = ordinal;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static class Parcelable extends ContactInfoRecord implements android.os.Parcelable {

        public Parcelable() {
        }

        public Parcelable(ContactInfoRecord record) {
            this.id = record.id;
            this.ordinal = record.ordinal;
            this.contactId = record.contactId;
            this.infoType = record.infoType;
            this.phone = record.phone;
            this.email = record.email;
            this.address = record.address;
            this.city = record.city;
            this.state = record.state;
            this.zip = record.zip;
            this.country = record.country;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(ordinal);
            dest.writeValue(contactId);
            dest.writeString(infoType);
            dest.writeString(phone);
            dest.writeString(email);
            dest.writeString(address);
            dest.writeString(city);
            dest.writeString(state);
            dest.writeString(zip);
            dest.writeString(country);
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
            id = (Long) in.readValue(Long.class.getClassLoader());
            ordinal = (Long) in.readValue(Long.class.getClassLoader());
            contactId = (Long) in.readValue(Long.class.getClassLoader());
            infoType = in.readString();
            phone = in.readString();
            email = in.readString();
            address = in.readString();
            city = in.readString();
            state = in.readString();
            zip = in.readString();
            country = in.readString();
        }
    }
}
