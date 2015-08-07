package com.dreamfactory.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactInfoRecord extends BaseRecord {
    @JsonProperty("address")
    public String address = "";

    @JsonProperty("city")
    public String city = "";

    @JsonProperty("contactId")
    public int contactId = 0;

    @JsonProperty("email")
    public String email = "";

    @JsonProperty("infoId")
    public int infoId = 0;

    @JsonProperty("infoType")
    public String infoType = "";

    @JsonProperty("phone")
    public String phone = "";
}
