package com.dreamfactory.sampleapp;

import com.dreamfactory.sampleapp.api.DreamFactoryAPI;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.UserService;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DreamFactoryTest {

    private DreamFactoryAPI api;

    @Before
    public void init() {
        api = DreamFactoryAPI.getInstance();
    }

    @Test
    public void testGetContactGroups() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        Call<Resource<GroupRecord>> call = service.getGroupList();

        Response<Resource<GroupRecord>> response = call.execute();

        Assert.assertTrue(response.isSuccessful());

        if(response.isSuccessful()) {
            Resource<GroupRecord> groupRecords = response.body();

            Assert.assertTrue(groupRecords.getResource().size() > 0);
        }
    }

    @Test
    public void testUserLogin() throws Exception {
        UserService service = api.getService(UserService.class);

        LoginRequest request = new LoginRequest();
        request.setEmail("nirmel.murtic+1@gmail.com");
        request.setPassword("testtest");

        Call<User> call = service.userLogin(request);

        Response<User> response = call.execute();

        Assert.assertTrue(response.isSuccessful());

        if(response.isSuccessful()) {
            User user = response.body();

            Assert.assertEquals(user.getEmail(), request.getEmail());
        }
    }
}