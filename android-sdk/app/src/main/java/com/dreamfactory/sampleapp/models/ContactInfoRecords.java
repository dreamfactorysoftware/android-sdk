package com.dreamfactory.sampleapp.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoRecords extends BaseRecord {
    @JsonProperty("resource")
    public List<ContactInfoRecord> record = new ArrayList<>();
}
