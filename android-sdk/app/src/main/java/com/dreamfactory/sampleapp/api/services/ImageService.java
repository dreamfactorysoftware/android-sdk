package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.FileRecord;
import com.dreamfactory.sampleapp.models.Resource;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface ImageService {

    @GET("files/profile_images/{id}/?include_folders=0&include_files=1")
    Call<Resource<FileRecord>> getProfileImages(@Path(value = "id") Long contactId);

    @GET("files/profile_images/{id}/{name}?include_properties=1&content=1&download=1")
    Call<FileRecord> getProfileImage(@Path(value = "id") Long contactId, @Path(value = "name") String name);

    @POST("files/profile_images/{id}/{name}")
    Call<FileRecord> addProfileImage(@Path(value = "id") Long contactId, @Path(value = "name") String name, @Body RequestBody file);

    @POST("files/profile_images/{id}/")
    Call<FileRecord> addFolder(@Path(value = "id") Long contactId);

    @DELETE("files/profile_images/{id}/?force=1")
    Call<FileRecord> removeFolder(@Path(value = "id") Long contactId);
}
