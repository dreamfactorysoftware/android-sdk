package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface UserService {

    @POST("user/session")
    Call<User> userLogin(@Body LoginRequest request);
}
