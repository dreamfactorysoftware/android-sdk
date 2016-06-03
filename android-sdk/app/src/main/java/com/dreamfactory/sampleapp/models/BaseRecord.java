package com.dreamfactory.sampleapp.models;

import java.io.Serializable;

public class BaseRecord implements Serializable {

    public String getNonNull(String toCheck){
        // just so we don't go display a null string
        if(toCheck == null){
            return "";
        }
        return toCheck;
    }

    public void setAllNonNull() {}
}


