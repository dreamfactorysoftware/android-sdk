package com.dreamfactory.sampleapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class DreamFactoryAPI {

    private static DreamFactoryAPI INSTANCE;

    private Retrofit retrofit;

    private String baseUrl = "https://df-ft-nirmel-murtic452.enterprise.dreamfactory.com/api/v2/";

    private String apiKey = "405b6f4da730ca78933654842be162024911a4250dc8e67a615e342dd2903eaf";

    private OkHttpClient httpClient;

    private DreamFactoryAPI() {
        httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder ongoing = chain.request().newBuilder();
                ongoing.addHeader("X-DreamFactory-Api-Key", apiKey);

                return chain.proceed(ongoing.build());
            }
        }).build();

        ObjectMapper objectMapper = new ObjectMapper();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    public synchronized static DreamFactoryAPI getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DreamFactoryAPI();
        }

        return INSTANCE;
    }

    public <T> T getService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
