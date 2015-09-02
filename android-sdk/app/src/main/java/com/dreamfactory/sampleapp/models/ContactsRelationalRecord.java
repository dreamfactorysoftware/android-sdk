package com.dreamfactory.sampleapp.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactsRelationalRecord {
    @JsonProperty("contact_by_contact_id")
    public ContactRecord contact_by_contact_id;
}
