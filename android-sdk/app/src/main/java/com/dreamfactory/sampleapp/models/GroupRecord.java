package com.dreamfactory.sampleapp.models;




// Take advantage of jackson being super nice to use
import com.fasterxml.jackson.annotation.JsonProperty;
public class GroupRecord extends BaseRecord {

    @JsonProperty("id")
    public int id = 0;

    @JsonProperty("name")
    public String name = "";
}

