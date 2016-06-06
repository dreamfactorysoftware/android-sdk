package com.dreamfactory.sampleapp.api;

import com.dreamfactory.sampleapp.DreamFactoryApp;
import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.AuthService;
import com.dreamfactory.sampleapp.api.services.ContactInfoService;
import com.dreamfactory.sampleapp.api.services.ContactService;
import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactInfoRecords;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.RegisterResponse;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;
import com.dreamfactory.sampleapp.models.requests.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

        testUserRegister();
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
        request.setEmail("nirmel+1@dreamfactory.com");
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
        registerRequest.setEmail("nirmel+1@dreamfactory.com");
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
        request.setEmail("nirmel+1@dreamfactory.com");
        request.setPassword("badpassword");

        Response<User> response = service.userLogin(request).execute();

        Assert.assertFalse(response.isSuccessful());

        if(!response.isSuccessful()) {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(response);

            Assert.assertEquals(error.getError().getCode().longValue(), 401L);
        }
    }

    @Test
    public void testGetGroupContacts() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        Response<Resource<ContactsRelationalRecord>> response = service.getGroupContacts("contact_group_id=2").execute();

        Assert.assertTrue(response.isSuccessful());

        List<ContactsRelationalRecord> list = response.body().getResource();

        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testGetContactInfo() throws Exception {
        ContactInfoService service = api.getService(ContactInfoService.class);

        Response<Resource<ContactInfoRecord>> response = service.getContactInfo("contact_id=1").execute();

        Assert.assertTrue(response.isSuccessful());

        List<ContactInfoRecord> list = response.body().getResource();

        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testUpdateContact() throws Exception {
        ContactService service = api.getService(ContactService.class);

        ContactRecord record = service.getContact(1L).execute().body();

        String originalFirstName = record.getFirstName();

        record.setFirstName("New Name");

        Resource<ContactRecord> updateRecords = new Resource<>();
        updateRecords.addResource(record);

        Response<Resource<ContactRecord>> resp = service.updateContact(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);

        record = service.getContact(1L).execute().body();

        record.setFirstName(originalFirstName);

        updateRecords = new Resource<>();
        updateRecords.addResource(record);

        resp = service.updateContact(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);
    }

    @Test
    public void testUpdateContactInfo() throws Exception {
        ContactInfoService service = api.getService(ContactInfoService.class);

        ContactInfoRecord contactInfo = service.getContactInfo(1L).execute().body();

        String oldCity = contactInfo.getCity();

        contactInfo.setCity("NEW CITY");

        Resource<ContactInfoRecord> updateRecords = new Resource<>();
        updateRecords.addResource(contactInfo);

        Response<Resource<ContactInfoRecord>> resp = service.updateContactInfo(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);

        contactInfo = service.getContactInfo(1L).execute().body();

        contactInfo.setCity(oldCity);

        updateRecords = new Resource<>();
        updateRecords.addResource(contactInfo);

        resp = service.updateContactInfo(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);
    }
}