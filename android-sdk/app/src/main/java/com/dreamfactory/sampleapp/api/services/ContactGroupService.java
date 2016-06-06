package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface ContactGroupService {

    @GET("db/_table/contact_group")
    Call<Resource<GroupRecord>> getGroupList();

    @GET("db/_table/contact_group_relationship?related=contact_by_contact_id")
    Call<Resource<ContactsRelationalRecord>> getGroupContacts(@Query(value = "filter") String filter);
}
