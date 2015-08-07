package com.dreamfactory.sampleapp.models;




// Take advantage of jackson being super nice to use
import com.fasterxml.jackson.annotation.JsonProperty;
public class GroupRecord extends BaseRecord {
    @JsonProperty("contactGroupId")
    public int contactGroupId = 0;

    @JsonProperty("groupName")
    public String groupName = "";
}

