package com.dreamfactory.sampleapp.api;

import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.AuthService;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;

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
    public void init() {
        api = DreamFactoryAPI.getInstance();
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
        request.setEmail("nirmel.murtic+1@gmail.com");
        request.setPassword("testtest");

        Response<User> response = service.userLogin(request).execute();

        Assert.assertTrue(response.isSuccessful());

        if(response.isSuccessful()) {
            User user = response.body();

            Assert.assertEquals(user.getEmail(), request.getEmail());
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