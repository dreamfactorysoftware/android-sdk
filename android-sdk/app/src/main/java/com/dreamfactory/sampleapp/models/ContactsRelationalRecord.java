package com.dreamfactory.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactsRelationalRecord extends BaseRecord {

    @JsonProperty("contact_by_contact_id")
    private ContactRecord contact;

    @JsonProperty("contact_id")
    private Long contactId;

    @JsonProperty("contact_group_id")
    private Long contactGroupId;

    public ContactRecord getContact() {
        return contact;
    }

    public void setContact(ContactRecord contact) {
        this.contact = contact;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactGroupId() {
        return contactGroupId;
    }

    public void setContactGroupId(Long contactGroupId) {
        this.contactGroupId = contactGroupId;
    }
}
