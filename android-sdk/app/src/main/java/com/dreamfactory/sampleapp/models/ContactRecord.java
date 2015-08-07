package com.dreamfactory.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// make it parcelable for passing from contactListActivity to contactViewActivity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactRecord extends BaseRecord{
    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("contactId")
    public int contactId;

    @JsonProperty("notes")
    public String notes;

    @JsonProperty("skype")
    public String skype;

    @JsonProperty("twitter")
    public String twitter;

    @JsonProperty("imageUrl")
    public String imageUrl;

    @Override
    public void setAllNonNull() {
        firstName = getNonNull(firstName);
        lastName = getNonNull(lastName);
        notes = getNonNull(notes);
        skype = getNonNull(skype);
        twitter = getNonNull(twitter);
        imageUrl = getNonNull(imageUrl);
    }
}
