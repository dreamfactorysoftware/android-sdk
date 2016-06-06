package com.dreamfactory.sampleapp.api;

import com.dreamfactory.sampleapp.DreamFactoryApp;
import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.AuthService;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.RegisterResponse;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;
import com.dreamfactory.sampleapp.models.requests.RegisterRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DreamFactoryAPITest {

    private DreamFactoryAPI api;

    @Before
    public void init() throws Exception {
        DreamFactoryApp.SESSION_TOKEN = "session_token";
        DreamFactoryApp.INSTANCE_URL = "https://ft-nirmel.vz2.dreamfactory.com/api/v2/";
        DreamFactoryApp.DB_SVC = "db/_table";
        DreamFactoryApp.API_KEY = "6498a8ad1beb9d84d63035c5d1120c007fad6de706734db9689f8996707e0f7d";

        DreamFactoryAPI.runningFromTest = true;

        api = DreamFactoryAPI.getInstance();

        testUserLogin();
    }

    @Test
    public void testGetContactGroups() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        Response<Resource<GroupRecord>> response = service.getGroupList().execute();

        Assert.assertTrue(response.isSuccessful());

        if(response.isSuccessful()) {
            Resource<GroupRecord> groupRecords = response.body();

            Assert.assertTrue(groupRecords.getResource().size() > 0);
        }
    }

    @Test
    public void testUserLogin() throws Exception {
        AuthService service = api.getService(AuthService.class);

        LoginRequest request = new LoginRequest();
        request.setEmail("nirmel+1@toptal.com");
        request.setPassword("testtest");

        Response<User> response = service.userLogin(request).execute();

        Assert.assertTrue(response.isSuccessful());

        if(response.isSuccessful()) {
            User user = response.body();

            Assert.assertEquals(user.getEmail(), request.getEmail());

            DreamFactoryAPI.testToken = user.getSessionToken();
        }
    }

    @Test
    public void testUserRegister() throws Exception {
        AuthService service = api.getService(AuthService.class);

        final RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("nirmel+1@toptal.com");
        registerRequest.setPassword("testtest");
        registerRequest.setLastName("Book");
        registerRequest.setFirstName("Address");
        registerRequest.setName("Address Book User");

        Response<RegisterResponse> response = service.userRegister(registerRequest, 1L).execute();

        if(response.isSuccessful()) {
            RegisterResponse resp = response.body();

            Assert.assertNotNull(resp.getSessionToken());

            DreamFactoryAPI.testToken = resp.getSessionToken();
        } else {
            ErrorMessage errorMessage = DreamFactoryAPI.getErrorMessage(response);

            Assert.assertEquals(errorMessage.getError().getCode().longValue(), 400L);
        }
    }

    @Test
    public void testUserLoginError() throws Exception {
        AuthService service = api.getService(AuthService.class);

        LoginRequest request = new LoginRequest();
        request.setEmail("nirmel.murtic+2@gmail.com");
        request.setPassword("badpassword");

        Response<User> response = service.userLogin(request).execute();

        Assert.assertFalse(response.isSuccessful());

        if(!response.isSuccessful()) {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(response);

            Assert.assertEquals(error.getError().getCode().longValue(), 401L);
        }
    }
}