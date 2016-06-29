package com.dreamfactory.sampleapp.api;

import com.dreamfactory.sampleapp.DreamFactoryApp;

import com.dreamfactory.sampleapp.api.services.ContactGroupService;
import com.dreamfactory.sampleapp.api.services.AuthService;
import com.dreamfactory.sampleapp.api.services.ContactInfoService;
import com.dreamfactory.sampleapp.api.services.ContactService;

import com.dreamfactory.sampleapp.api.services.ImageService;

import com.dreamfactory.sampleapp.models.ContactInfoRecord;
import com.dreamfactory.sampleapp.models.ContactRecord;
import com.dreamfactory.sampleapp.models.ContactsRelationalRecord;
import com.dreamfactory.sampleapp.models.ErrorMessage;
import com.dreamfactory.sampleapp.models.FileRecord;
import com.dreamfactory.sampleapp.models.GroupRecord;
import com.dreamfactory.sampleapp.models.RegisterResponse;
import com.dreamfactory.sampleapp.models.Resource;
import com.dreamfactory.sampleapp.models.User;
import com.dreamfactory.sampleapp.models.requests.LoginRequest;
import com.dreamfactory.sampleapp.models.requests.RegisterRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DreamFactoryAPITest {

    private DreamFactoryAPI api;

    @Before
    public void init() throws Exception {
        DreamFactoryApp.SESSION_TOKEN = "session_token";
        DreamFactoryApp.INSTANCE_URL = "http://10.0.2.2:8080/api/v2/";
        DreamFactoryApp.DB_SVC = "db/_table";
        DreamFactoryApp.API_KEY = "55d7ad6d21915e8539b7939f8604b4c096eff005bc2d431abd9b427a45d55e22";

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
    public void testGetAllContacts() throws Exception {
        ContactService service = api.getService(ContactService.class);

        Response<Resource<ContactRecord>> response = service.getAllContacts().execute();

        Assert.assertTrue(response.isSuccessful());

        List<ContactRecord> list = response.body().getResource();

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

        Response<Resource<ContactRecord>> resp = service.updateContacts(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);

        record = service.getContact(1L).execute().body();

        record.setFirstName(originalFirstName);

        updateRecords = new Resource<>();
        updateRecords.addResource(record);

        resp = service.updateContacts(updateRecords).execute();

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

        Response<Resource<ContactInfoRecord>> resp = service.updateContactInfos(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);

        contactInfo = service.getContactInfo(1L).execute().body();

        contactInfo.setCity(oldCity);

        updateRecords = new Resource<>();
        updateRecords.addResource(contactInfo);

        resp = service.updateContactInfos(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);
    }

    @Test
    public void testUpdateContactGroup() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        GroupRecord contactGroup = service.getContactGroup(1L).execute().body();

        String oldName = contactGroup.getName();

        contactGroup.setName("NEW NAME");

        Resource<GroupRecord> updateRecords = new Resource<>();
        updateRecords.addResource(contactGroup);

        Response<Resource<GroupRecord>> resp = service.updateContactGroups(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);

        contactGroup = service.getContactGroup(1L).execute().body();

        contactGroup.setName(oldName);

        updateRecords = new Resource<>();
        updateRecords.addResource(contactGroup);

        resp = service.updateContactGroups(updateRecords).execute();

        Assert.assertTrue(resp.isSuccessful());

        Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() == 1L);
    }

    @Test
    public void testDeleteAndAddGroupContacts() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        ContactsRelationalRecord recordToDelete = new ContactsRelationalRecord();
        recordToDelete.setContactGroupId(2L);
        recordToDelete.setContactId(7L);

        Resource<ContactsRelationalRecord> resource = new Resource<>();
        resource.addResource(recordToDelete);

        Response<Resource<ContactsRelationalRecord>> response = service.deleteGroupContacts(resource).execute();

        if(response.isSuccessful()) {
            response = service.addGroupContacts(response.body()).execute();

            Assert.assertTrue(response.isSuccessful());
        } else {
            ErrorMessage errorMessage = DreamFactoryAPI.getErrorMessage(response);

            // Already deleted, add it
            Assert.assertEquals(errorMessage.getError().getCode().longValue(), 404L);

            response = service.addGroupContacts(resource).execute();

            Assert.assertTrue(response.isSuccessful());

            response = service.deleteGroupContacts(response.body()).execute();

            Assert.assertTrue(response.isSuccessful());
        }
    }

    @Test
    public void testCreateContactGroup() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        GroupRecord contactGroup = new GroupRecord();
        contactGroup.setName("NEW NAME 3");

        Resource<GroupRecord> createRecords = new Resource<>();
        createRecords.addResource(contactGroup);

        Response<Resource<GroupRecord>> resp = service.createContactGroups(createRecords).execute();

        if(resp.isSuccessful()) {
            Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() != 0L);
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(resp);

            // Group already exist
            Assert.assertEquals(error.getError().getCode().longValue(), 500L);
        }
    }

    @Test
    public void testCreateContact() throws Exception {
        ContactService service = api.getService(ContactService.class);

        ContactRecord contact = new ContactRecord();
        contact.setFirstName("John");
        contact.setLastName("Doe");

        Resource<ContactRecord> createRecords = new Resource<>();
        createRecords.addResource(contact);

        Response<Resource<ContactRecord>> resp = service.createContacts(createRecords).execute();

        if(resp.isSuccessful()) {
            Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() != 0L);
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(resp);

            // Group already exist
            Assert.assertEquals(error.getError().getCode().longValue(), 500L);
        }
    }

    @Test
    public void testCreateContactInfo() throws Exception {
        ContactInfoService service = api.getService(ContactInfoService.class);

        ContactInfoRecord contactInfo = new ContactInfoRecord();
        contactInfo.setCity("Sarajevo");
        contactInfo.setContactId(7L);
        contactInfo.setInfoType("home");
        contactInfo.setAddress("Zmaja od Bosne");
        contactInfo.setCountry("Bosnia and Herzegovina");
        contactInfo.setEmail("nirmel@dreamfactory.com");
        contactInfo.setPhone("38761123456");
        contactInfo.setZip("71000");

        Resource<ContactInfoRecord> createRecords = new Resource<>();
        createRecords.addResource(contactInfo);

        Response<Resource<ContactInfoRecord>> resp = service.createContactInfos(createRecords).execute();

        if(resp.isSuccessful()) {
            Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() != 0L);
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(resp);

            // Contact info already exist
            Assert.assertEquals(error.getError().getCode().longValue(), 500L);
        }
    }

    @Test
    public void testCreateFolder() throws Exception {
        ImageService service = api.getService(ImageService.class);

        Response<FileRecord> response = service.addFolder(7L).execute();

        if(response.isSuccessful()) {
            Assert.assertEquals("profile_images/7/", response.body().getPath());
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(response);

            // Folder already exist
            Assert.assertEquals(error.getError().getCode().longValue(), 400L);
        }
    }

    @Test
    public void testAddProfileImage() throws Exception {
        ImageService service = api.getService(ImageService.class);

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),  "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7");

        Response<FileRecord> response = service.addProfileImage(7L, "test.jpg", requestBody).execute();

        Assert.assertTrue(response.isSuccessful());

        Assert.assertEquals(response.body().getName(), "test.jpg");
    }

    @Test
    public void testGetProfileImage() throws Exception {
        ImageService service = api.getService(ImageService.class);

        Response<FileRecord> response = service.getProfileImage(7L, "test.jpg").execute();

        if(response.isSuccessful()) {
            Assert.assertEquals(response.body().getName(), "test.jpg");
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(response);

            // Folder already exist
            Assert.assertEquals(error.getError().getCode().longValue(), 404L);
        }
    }

    @Test
    public void testGetProfileImages() throws Exception {
        ImageService service = api.getService(ImageService.class);

        Response<Resource<FileRecord>> response = service.getProfileImages(7L).execute();

        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void testRemoveFolder() throws Exception {
        ImageService service = api.getService(ImageService.class);

        Response<FileRecord> response = service.removeFolder(8L).execute();

        if(response.isSuccessful()) {
            Assert.assertEquals("profile_images/8/", response.body().getPath());
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(response);

            // Folder already exist
            Assert.assertEquals(error.getError().getCode().longValue(), 400L);
        }
    }

    @Test
    public void testRemoveContacts() throws Exception {
        ContactService service = api.getService(ContactService.class);

        Response<Resource<ContactRecord>> resp = service.removeContacts("108").execute();

        if(resp.isSuccessful()) {
            Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() != 0L);
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(resp);

            // Contact already removed
            Assert.assertEquals(error.getError().getCode().longValue(), 404L);
        }
    }

    @Test
    public void testRemoveContactInfos() throws Exception {
        ContactInfoService service = api.getService(ContactInfoService.class);

        Resource<Long> resource = new Resource<>();

        resource.addResource(8L);

        Response<Resource<ContactInfoRecord>> resp = service.removeContactInfos(resource).execute();

        if(resp.isSuccessful()) {
            Assert.assertTrue(resp.body().getResource().size() > 0);
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(resp);

            // Contact already removed
            Assert.assertEquals(error.getError().getCode().longValue(), 404L);
        }
    }

    @Test
    public void testRemoveContactGroups() throws Exception {
        ContactGroupService service = api.getService(ContactGroupService.class);

        Response<Resource<GroupRecord>> resp = service.removeGroups("9").execute();

        if(resp.isSuccessful()) {
            Assert.assertTrue(resp.body().getResource().size() > 0 && resp.body().getResource().get(0).getId() != 0L);
        } else {
            ErrorMessage error = DreamFactoryAPI.getErrorMessage(resp);

            // Contact group already removed
            Assert.assertEquals(error.getError().getCode().longValue(), 404L);
        }
    }
}