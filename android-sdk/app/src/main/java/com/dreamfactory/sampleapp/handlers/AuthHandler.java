package com.dreamfactory.sampleapp.handlers;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.AuthService;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.RegisterResponse;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;
import com.dreamfactory.sampleapp.models.requests.RegisterRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class AuthHandler extends BaseHandler {

    public AuthHandler() {
        super(AuthHandler.class.getSimpleName());
    }

    public void logIn(final Context context, final String email, final String password, final OnLogin callback) {
        final Handler mainHandler = new Handler(context.getMainLooper());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                AuthService service = DreamFactoryAPI.getInstance().getService(AuthService.class);

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setEmail(email);
                loginRequest.setPassword(password);

                try {
                    final Response<User> response = service.userLogin(loginRequest).execute();

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                        if (response.isSuccessful()) {
                            callback.onLogin(response.body());
                        } else {
                            callback.onError(DreamFactoryAPI.getErrorMessage(response).getError());
                        }
                        }
                    });
                } catch (IOException e) {
                    Log.e(AuthHandler.class.getSimpleName(), "Error while logging user", e);

                    callback.onError(new ErrorMessage("Unexpected error").getError());
                }
            }
        });
    }

    public void register(final Context context, final String email, final String password, final OnRegister callback) {
        final Handler mainHandler = new Handler(context.getMainLooper());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                AuthService service = DreamFactoryAPI.getInstance().getService(AuthService.class);

                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setEmail(email);
                registerRequest.setPassword(password);
                registerRequest.setLastName("Book");
                registerRequest.setFirstName("Address");
                registerRequest.setName("Address Book User");

                try {
                    // Second param means that user will be logged in automatically
                    final Response<RegisterResponse> response = service.userRegister(registerRequest, 1L).execute();

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                        if (response.isSuccessful()) {
                            callback.onRegister(response.body());
                        } else {
                            callback.onError(DreamFactoryAPI.getErrorMessage(response).getError());
                        }
                        }
                    });
                } catch (IOException e) {
                    Log.e(AuthHandler.class.getSimpleName(), "Error while logging user", e);

                    callback.onError(new ErrorMessage("Unexpected error").getError());
                }
            }
        });
    }

    public interface OnRegister extends BaseHandler.BaseCallback {
        void onRegister(RegisterResponse response);
    }

    public interface OnLogin extends BaseHandler.BaseCallback {
        void onLogin(User user);
    }
}