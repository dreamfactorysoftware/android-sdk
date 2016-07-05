Address Book for Android
========================

This repo contains a sample address book application for Android that demonstrates how to use the DreamFactory REST API. It includes new user registration, user login, and CRUD for related tables.

#Getting DreamFactory on your local machine

To download and install DreamFactory, follow the instructions [here](http://wiki.dreamfactory.com/DreamFactory/Installation). Alternatively, you can create a [free hosted developer account](http://www.dreamfactory.com) at www.dreamfactory.com if you don't want to install DreamFactory locally.

#Configuring your DreamFactory instance to run the app

- Enable [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) for development purposes.
    - In the admin console, navigate to the Config tab and click on CORS in the left sidebar.
    - Click Add.
    - Set Origin, Paths, and Headers to *.
    - Set Max Age to 0.
    - Allow all HTTP verbs and check the Enabled box.
    - Click update when you are done.
    - More info on setting up CORS is available [here](http://wiki.dreamfactory.com/DreamFactory/Tutorials/Enabling_CORS_Access).

- Create a default role for new users and enable open registration
    - In the admin console, click the Roles tab then click Create in the left sidebar.
    - Enter a name for the role and check the Active box.
    - Go to the Access tab.
    - Add a new entry under Service Access (you can make it more restrictive later).
        - set Service = All
        - set Component = *
        - check all HTTP verbs under Access
        - set Requester = API
    - Click Create Role.
    - Click the Services tab, then edit the user service. Go to Config and enable Allow Open Registration.
    - Set the Open Reg Role Id to the name of the role you just created.
    - Make sure Open Reg Email Service Id is blank, so that new users can register without email confirmation.
    - Save changes.

- Import the package file for the app.
    - From the Apps tab in the admin console, click Import and click 'Address Book for Android' in the list of sample apps. The Address Book package contains the application description, schemas, and sample data.
    - Set storage service to files
    - Leave storage folder blank.
    - Click the Import button. If successful, your app will appear on the Apps tab. You may have to refresh the page to see your new app in the list.
    
- Make sure you have a SQL database service named 'db'. Depending on how you installed DreamFactory you may or may not have a 'db' service already available on your instance. You can add one by going to the Services tab in the admin console and creating a new SQL service. Make sure you set the name to 'db'.

#Running the Address Book app

Almost there! Clone this repo to your local machine then open and run the project with Android Studio. To open the project select the android-sdk/android-sdk directory.

Before running the project you need to edit apiKey in the file AndroidManifest.xml to match the key for your new app. This key can be found by selecting your app from the list on the Apps tab in the admin console.

If your DreamFactory instance is on localhost, make sure the instanceUrl in AndroidManifest.xml is set to the emulator localhost IP of 10.0.2.2. Make sure the port numbers match.

```<meta-data android:name="instanceUrl" android:value="http://10.0.2.2:8080/api/v2" />```

If your instance is not on localhost, set instanceUrl to point to the proper location.

```<meta-data android:name="instanceUrl" android:value="http://my-df-instance.example.com/api/v2" />```

When the app starts up you can register a new user, or log in as an existing user. Currently the app does not support registering and logging in admin users.

#Example API calls

The app uses the Retrofit2 library to send REST requests.

The general form of a DreamFactory REST API call is:

`<rest-verb> http[s]://<server-name>/api/v2/[<service-api-name>]/[<resource-path>][?<param-name>=<param-value>]`

An Android call looks like this:

```Java
	final ContactGroupService service = DreamFactoryAPI.getInstance().getService(ContactGroupService.class);

        service.getGroupList().enqueue(new Callback<Resource<GroupRecord>>() {
            @Override
            public void onResponse(Call<Resource<GroupRecord>> call, Response<Resource<GroupRecord>> response) {
                // Handle response on main thread
            }

            @Override
            public void onFailure(Call<Resource<GroupRecord>> call, Throwable t) {
            	// Handle error on main thread
            }
        });
```

More about sending REST requests using Retrofit2 library here: http://square.github.io/retrofit/

###Database
#####Selecting records (to get or delete)
   - Using filters
    ```Java
    
    // filter param should be like "contact_id=2"
    @GET("db/_table/contact_info")
    Call<Resource<ContactInfoRecord>> getContactInfo(@Query(value = "filter") String filter);
    
    ```        

   - Related records
    ```Java
    // filter to only get the contact_group_relationships we want, filter should be like "contact_group_id=1"
    // request without related would return just {id, contact_group_id, contact_id}
    // set the related field to go get the contact records referenced by
    // each contact_group_relationship record
    @GET("db/_table/contact_group_relationship?related=contact_by_contact_id")
    Call<Resource<ContactsRelationalRecord>> getGroupContacts(@Query(value = "filter") String filter);
    ```

   - Using id_fields
    ```Java
    // since we don't know the contact_group_relationship ids, change the id field to id
    @GET("db/_table/contact_group_relationship?id_field=id")
    Call<Resource<ContactsRelationalRecord>> getGroupContacts(@Body Resource<Long> ids);
    ```

   - Records by id
    ```Java
    // delete contact records by record ids
    // ids is comma separated list of contact ids
    @DELETE("db/_table/contact")
    Call<Resource<ContactRecord>> removeContacts(@Query(value = "ids") String ids);
    ```

#####Updating records
```Java
// send the record to patch, need to include record id inside each ContactRecord instance
@PATCH("db/_table/contact")
Call<Resource<ContactRecord>> updateContacts(@Body Resource<ContactRecord> contactRecords);
```

#####Creating records
```Java
// only need to send the group name inside GroupRecord instance
// we don't have a group ID yet, so we can't provide one here
@POST("db/_table/contact_group")
Call<Resource<GroupRecord>> createContactGroups(@Body Resource<GroupRecord> records);
```

#Additional Resources

More detailed information on the DreamFactory REST API is available [here](http://wiki.dreamfactory.com/DreamFactory/API).

The live API documentation included in the admin console is a great way to learn how the DreamFactory REST API works.
