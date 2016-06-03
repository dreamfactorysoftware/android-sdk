package com.dreamfactory.sampleapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class Resource<T extends BaseRecord> implements Serializable {

    private List<T> resource = new ArrayList<>();

    public List<T> getResource() {
        return resource;
    }

    public void setResource(List<T> resource) {
        this.resource = resource;
    }
}
