package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface ContactGroupService {

    //TODO: Remove temporary email & password
    @GET("db/_table/contact_group?email=nirmel.murtic+1@gmail.com&password=testtest")
    Call<Resource<GroupRecord>> getGroupList();
}
