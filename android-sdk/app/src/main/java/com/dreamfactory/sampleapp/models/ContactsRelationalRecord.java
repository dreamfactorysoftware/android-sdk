package com.dreamfactory.sampleapp.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactsRelationalRecord extends BaseRecord {

    @JsonProperty("contact_by_contact_id")
    private ContactRecord contact;

    public ContactRecord getContact() {
        return contact;
    }

    public void setContact(ContactRecord contact) {
        this.contact = contact;
    }
}
