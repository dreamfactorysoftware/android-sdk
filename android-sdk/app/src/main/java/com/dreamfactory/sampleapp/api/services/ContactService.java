package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.Resource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface ContactService {

    @GET("db/_table/contact/{id}")
    Call<ContactRecord> getContact(@Path(value = "id") Long contactId);

    @GET("db/_table/contact")
    Call<Resource<ContactRecord>> getAllContacts();

    @POST("db/_table/contact")
    Call<Resource<ContactRecord>> createContacts(@Body Resource<ContactRecord> records);

    @DELETE("db/_table/contact")
    Call<Resource<ContactRecord>> removeContacts(@Query(value = "ids") String ids);

    @PATCH("db/_table/contact")
    Call<Resource<ContactRecord>> updateContacts(@Body Resource<ContactRecord> contactRecord);
}
