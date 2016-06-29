package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface ContactGroupService {

    @GET("db/_table/contact_group/{id}")
    Call<GroupRecord> getContactGroup(@Path(value = "id") Long contactGroupId);

    @GET("db/_table/contact_group")
    Call<Resource<GroupRecord>> getGroupList();

    @GET("db/_table/contact_group_relationship?related=contact_by_contact_id")
    Call<Resource<ContactsRelationalRecord>> getGroupContacts(@Query(value = "filter") String filter);

    @HTTP(method = "DELETE", path = "db/_table/contact_group_relationship?id_field=contact_group_id,contact_id", hasBody = true)
    Call<Resource<ContactsRelationalRecord>> deleteGroupContacts(@Body Resource<ContactsRelationalRecord> records);

    @HTTP(method = "DELETE", path = "db/_table/contact_group_relationship?id_field=contact_id", hasBody = true)
    Call<Resource<ContactsRelationalRecord>> deleteContactsFromGroups(@Body Resource<Long> records);

    @DELETE("db/_table/contact_group_relationship")
    Call<Resource<ContactsRelationalRecord>> deleteContactsFromGroups(@Query(value = "filter") String filter);

    @DELETE("db/_table/contact_group")
    Call<Resource<GroupRecord>> removeGroups(@Query(value = "ids") String ids);

    @POST("db/_table/contact_group_relationship")
    Call<Resource<ContactsRelationalRecord>> addGroupContacts(@Body Resource<ContactsRelationalRecord> records);

    @POST("db/_table/contact_group")
    Call<Resource<GroupRecord>> createContactGroups(@Body Resource<GroupRecord> records);

    @PATCH("db/_table/contact_group")
    Call<Resource<GroupRecord>> updateContactGroups(@Body Resource<GroupRecord> records);
}
