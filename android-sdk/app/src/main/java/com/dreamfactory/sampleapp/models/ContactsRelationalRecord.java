package com.dreamfactory.sampleapp.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactsRelationalRecord {
    @JsonProperty("contacts_by_contactId")
    public ContactRecord contacts_by_contact_id;
}
