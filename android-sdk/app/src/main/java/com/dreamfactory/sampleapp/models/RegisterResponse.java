package com.dreamfactory.sampleapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class RegisterResponse implements Serializable {

    private Boolean success;

    @JsonProperty("session_token")
    private String sessionToken;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
