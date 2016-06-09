package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;

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
public interface ContactInfoService {

    @GET("db/_table/contact_info/{id}")
    Call<ContactInfoRecord> getContactInfo(@Path(value = "id") Long contactInfoId);

    @GET("db/_table/contact_info")
    Call<Resource.Parcelable<ContactInfoRecord.Parcelable>> getContactInfo(@Query(value = "filter") String filter);

    @POST("db/_table/contact_info")
    Call<Resource<ContactInfoRecord>> createContactInfos(@Body Resource<ContactInfoRecord> records);

    @PATCH("db/_table/contact_info")
    Call<Resource<ContactInfoRecord>> updateContactInfos(@Body Resource<ContactInfoRecord> contactRecord);
}
