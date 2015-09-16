package com.dreamfactory.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ContactRecords extends BaseRecord {
    @JsonProperty("resource")
    public List<ContactRecord> record = new ArrayList<>();
}
