package com.dreamfactory.sampleapp.models;


// no real functionality, just something to extend for polymorphism
public class BaseRecord {
    public String getNonNull(String toCheck){
        // just so we don't go display a null string
        if(toCheck == null){
            return "";
        }
        return toCheck;
    }

    public void setAllNonNull() {}
}


