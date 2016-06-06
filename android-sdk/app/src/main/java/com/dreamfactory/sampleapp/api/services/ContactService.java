package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface ContactService {

    @PATCH("db/_table/contact")
    Call<Resource<ContactRecord>> updateContact(@Body Resource<ContactRecord> contactRecord);

    @GET("db/_table/contact/{id}")
    Call<ContactRecord> getContact(@Path(value = "id") Long contactId);
}
