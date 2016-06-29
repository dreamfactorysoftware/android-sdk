package com.dreamfactory.sampleapp.api.services;

import com.dreamfactory.sampleapp.models.RegisterResponse;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;
import com.dreamfactory.sampleapp.models.requests.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Nirmel on 6/3/2016.
 */
public interface AuthService {

    @POST("user/session")
    Call<User> userLogin(@Body LoginRequest request);

    @POST("user/register")
    Call<RegisterResponse> userRegister(@Body RegisterRequest request, @Query("login") Long login);
}
