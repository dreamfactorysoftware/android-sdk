package com.dreamfactory.sampleapp.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GroupRecords extends BaseRecord {
    @JsonProperty("resource")
    public ArrayList<GroupRecord> record = new ArrayList<>();
}
