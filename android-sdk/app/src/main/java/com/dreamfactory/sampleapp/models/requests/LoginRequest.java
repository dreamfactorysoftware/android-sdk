package com.dreamfactory.sampleapp.models.requests;

import java.io.Serializable;

/**
 * Created by Nirmel on 6/3/2016.
 */
public class LoginRequest implements Serializable {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
