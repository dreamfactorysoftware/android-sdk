package com.dreamfactory.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactInfoRecord extends BaseRecord {

    @JsonProperty("id")
    public int id = 0;

    @JsonProperty("ordinal")
    public int ordinal = 0;

    @JsonProperty("contact_id")
    public int contact_id = 0;

    @JsonProperty("info_type")
    public String info_type = "";

    @JsonProperty("phone")
    public String phone = "";

    @JsonProperty("email")
    public String email = "";

    @JsonProperty("address")
    public String address = "";

    @JsonProperty("city")
    public String city = "";

    @JsonProperty("state")
    public String state = "";

    @JsonProperty("zip")
    public String zip = "";

    @JsonProperty("country")
    public String country = "";
}
