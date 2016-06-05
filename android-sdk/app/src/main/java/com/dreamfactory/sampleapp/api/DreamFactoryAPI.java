package com.dreamfactory.sampleapp.api;

import android.util.Log;

import com.dreamfactory.sampleapp.DreamFactoryApp;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.utils.AppConstants;
import com.dreamfactory.sampleapp.utils.PrefUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class DreamFactoryAPI {

    private static DreamFactoryAPI INSTANCE;

    private Retrofit retrofit;

    private OkHttpClient httpClient;

    private static Converter<ResponseBody, ErrorMessage> errorConverter;

    private DreamFactoryAPI() {
        httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder ongoing = chain.request().newBuilder();

                if(DreamFactoryApp.API_KEY == null) {
                    Log.w(DreamFactoryAPI.class.getSimpleName(), "API key not provided");
                } else {
                    ongoing.addHeader("X-DreamFactory-Api-Key", DreamFactoryApp.API_KEY);
                }

                String token = PrefUtil.getString(DreamFactoryApp.getAppContext(), AppConstants.SESSION_TOKEN);

                if(token != null && !token.isEmpty()) {
                    ongoing.addHeader("X-DreamFactory-Session-Token", token);
                }

                return chain.proceed(ongoing.build());
            }
        }).build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        retrofit = new Retrofit.Builder()
                .baseUrl(DreamFactoryApp.INSTANCE_URL)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        errorConverter = retrofit.responseBodyConverter(ErrorMessage.class, new Annotation[0]);
    }

    public static ErrorMessage getErrorMessage(Response response) {
        ErrorMessage error = null;

        try {
            error = errorConverter.convert(response.errorBody());
        } catch (IOException e) {
            error = new ErrorMessage("Unexpected error");

            Log.e("ERROR", "Unexpected error while serialising error message", e);
        }

        return error;
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
