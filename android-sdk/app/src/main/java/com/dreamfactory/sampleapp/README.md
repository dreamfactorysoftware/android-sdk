SampleApp
==========
This repo contains a sample Android "Address Book" application that demonstrates the DreamFactory Android API. 

#####Getting DreamFactory on your local machine
To download and install DreamFactory, follow the instructions [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/Usage-Options). Alternatively, you can create a [free hosted developer account](http://www.dreamfactory.com) at www.dreamfactory.com if you don't want to install DreamFactory locally.

In the DreamFactory Admin Console, navigate to the Config tab and click on CORS in the left sidebar. To enable [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) for development purposes, click add, set the host to *, allow all HTTP verbs and check the enabled box. Click update when you are done. More info on setting up CORS is [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/CORs-Configuration).

#####Running the example Android Address Book app
From the Apps menu in the DreamFactory Admin Console, click import and import the package from "https://raw.github.com/dreamfactorysoftware/app-address-book-ios/master/add_ios.dfpkg". The Address Book package contains application descriptions, schemas and data used by the iOS application. Set the storage service to local file storage and the storage container to applications. Click the Update button when done.

Almost there! Download this repo onto your local machine then open and run the project with Android Studio. You can log in to the app with the username and password you used for the DreamFactory Admin Console.

The default DreamFactory Admin Console URL is localhost:8080. If your DreamFactory Admin Console is not at that path, you can change the default path in IAppConstants. 

#####Set up new user registration
To allow new users to register in the Address Book app you need to create a new role:

1. From the DreamFactory Admin Console, click roles then click Create
2. Enter a name and description for the role
3. Go to the Access tab
4. Create a new service under Server Access
  - set Service = All
  - set Component = *
  - check all HTTP verbs under Access
  - set Requester = API
5. Under the Apps tab make the address book the default
6. Click Create Role
7. Click the Config tab, then go to Open Registration
8. Check Allow Open Registration
9. Set the default role to the name of the role you created

Example API calls 
==============
More info on the DreamFactory Android API is available [here](../../../dfapi). 

Each example has a link to where the call is made in code as well as a short excerpt showing the most important part of the call.

###Database
#####Selecting records (to get or delete)
   - Using filters
     - [EditGroupAdapter](adapters/EditGroupAdapter.java#L72-L139)
     - [ContactListActivity](ui/ContactListActivity.java#L123-L202)
     - [ContactViewActivity](ui/ContactViewActivity.java#L197-L252)
     - [GroupListAdapter](adapters/GroupListAdapter.java#L119-L152)
    ```Java
    queryParams = new HashMap<>();
    // filter to only the contact_info records related to the contact
    queryParams.put("filter", "id=" + contactRecord.id);
    ```        

   - Related records
     - [EditGroupAdapter](adapters/EditGroupAdapter.java#L72-L128)
     - [ContactListActivity](ui/ContactListActivity.java#L123-L202)
    ```Java
    // filter to only get the group we want
    queryParams = new HashMap<>();
    queryParams.put("filter", "id=" + groupId);

    // request without related would return just {id, contact_group_id, contact_id}
    // set the related field to go get the contact records referenced by
    // each contact_group_relationship record
    queryParams.put("related", "contact_by_contact_id");
    ```

   - Using id_fields
     - [ContactListAdapter](adapters/ContactListAdapter.java#L225-L261)
     - [ContactListAdapter](adapters/ContactListAdapter.java#L263-L295)
    ```Java
    // since we don't know the contact_group_relationship ids, change the id field to id
    queryParams = new HashMap<>();
    queryParams.put("id_field", "id");

    // form of request is { "record": [... ids ...] }
    requestBody = new JSONObject();
    requestBody.put("record", idArray);
    ```

   - Records by id
     - [ContactListAdapter](adapters/ContactListAdapter.java#L297-L345)
     - [GroupListAdapter](adapters/GroupListAdapter.java#L154-L187)
    ```Java
    // delete contact records by record id
    // need to remove '[' and ']' from JSON array because ids doesn't take JSON
    queryParams = new HashMap<>();
    queryParams.put("ids", idArray.toString().replace("[", "").replace("]", ""));
    ```

#####Updating records
- [ContactViewActivity](ui/ContactViewActivity.java#L281-L309)
- [ContactViewActivity](ui/ContactViewActivity.java#L254-L279)
- [GroupActivity](ui/GroupActivity.java#L319-L340)
```Java
// send the record to patch, need to include record id
//requestString = "{\"name\":\"" + record.name + "\",\"id\":" +
        //record.id + "}";
// form is { "id": id, "name":name }
requestString = ApiInvoker.serialize(record);
```

#####Creating records
 - [CreateContactActivity](ui/CreateContactActivity.java#L186-L260)
 - [CreateContactActivity](ui/CreateContactActivity.java#L262-L290)
 - [CreateContactActivity](ui/CreateContactActivity.java#L292-L318)
 - [GroupActivity](ui/GroupActivity.java#L184-L228)
 - [GroupActivity](ui/GroupActivity.java#L230-L270)
```Java
// only need to send the groupName in body
// we don't have a groupID yet, so we can't provide one here
requestString = "{\"name\":\"" + name + "\"}";
```

###Working with files
#####creating files: 
- [CreateContactActivity](ui/CreateContactActivity.java#L351-L375)
```Java
serviceName = "files";
applicationName = AppConstants.APP_NAME;

// build rest path for request
// form is <url to DSP>/rest/files/container/application/<folder path>/<file name>
// here the folder path is profile_images/contactId
String containerName = "applications";
String folderPath = "profile_images/" + contactId + "/";
String fileName = "testFile.png";
endPoint = containerName + "/" + applicationName + "/" + folderPath + "/" + fileName;

verb = "POST";

fileRequest = new FileRequest();
fileRequest.setPath(profileImagePath);
```

#####creating folders: 
- [CreateContactActivity](ui/CreateContactActivity.java#L320-L349)
```Java
serviceName = "files";
applicationName = AppConstants.APP_NAME;

// build rest path for request
// form is <url to DSP>/rest/files/container/application/<folder path>
// here the folder path is profile_images/contactId/
// the file path ends in a '/' because we are targeting a folder
String containerName = "applications";
String folderPath = "profile_images/" + contactId + "/";
endPoint = containerName + "/" + applicationName + "/" + folderPath;

verb = "POST";
```

#####getting files
- [ContactViewActivity](ui/ContactViewActivity.java#L311-L357)
```Java
{
  ... part of doSetup ...
  applicationName = AppConstants.APP_NAME; // app name used in file path
  verb = "GET";
  // build rest path for request
  // form is <url to DSP>/rest/files/container/application/<folder path>/filename
  // here the folder path is profile_images/contactId/
  // the file path does not end in a '/' because we are targeting a file not a folder
  String containerName = "applications";
  String folderPath = "profile_images/" + contactRecord.id;
  String fileName = contactRecord.image_url;
  endPoint = containerName + "/" + applicationName + "/" + folderPath + "/" + fileName;
  
  queryParams = new HashMap<>();
  // don't include the file properties
  queryParams.put("include_properties", "1");
  // include the content
  queryParams.put("content", "1");
  // give us a download
  queryParams.put("download", "1");
}
@Override
protected void processResponse(String response) throws ApiException, JSONException {
    JSONObject jsonObject = new JSONObject(response);
    String imageData = jsonObject.getString("content");

    // files come back as Base64 strings
    byte[] decodedString = Base64.decode(imageData, Base64.DEFAULT);
    bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
}
```

#####getting folder contents
- [ChooseImageActivity](ui/ChooseImageActivity.java#L67-L119)
```Java
applicationName = AppConstants.APP_NAME;

// build rest path for request
// form is <url to DSP>/rest/files/container/application/<folder path>
// here the folder path is profile_images/contactId/
// the file path ends in a '/' because we are targeting a folder
String containerName = "applications";
String folderPath = "profile_images/" + contactId + "/";
endPoint = containerName + "/" + applicationName + "/" + folderPath;

// don't get any folders back in response, only get files
queryParams = new HashMap<>();
queryParams.put("include_folders", "0");
queryParams.put("include_files", "1");
```

#####deleting files/folders
- [ContactListAdapter](adapters/ContactListAdapter.java#L347-L370)
```Java
// here the folder path is profile_images/contactId/
// the file path ends in a '/' because we are targeting a folder
String containerName = "applications";
String folderPath = "profile_images/" + contactId + "/";
endPoint = containerName + "/" + applicationName + "/" + folderPath;

verb = "DELETE";
// want to delete all the files and folders in the target folder
queryParams = new HashMap<>();
queryParams.put("force", "1");
```

###User login
- [Login](ui/LoginActivity.java#L279-L331)
- [Register new user](ui/LoginActivity.java#L333-L362)
```Java
{ // part of doSetup
  serviceName = "user";
  endPoint = "session";
  
  verb = "POST";
  
  // post email and password to get back session id
  // need session id to make every call other than login and challenge
  requestBody = new JSONObject();
  requestBody.put("email", mEmail);
  requestBody.put("password", mPassword);
  
  // include application name
  applicationName = AppConstants.APP_NAME;
}

@Override
protected void processResponse(String response) throws ApiException, JSONException {
// store the session_id to be used later on
JSONObject jsonObject = new JSONObject(response);
String session_id = jsonObject.getString("session_id");
if(session_id.length() == 0){
    throw new ApiException(0, "did not get a valid session id in the response");
}
PrefUtil.putString(getApplicationContext(), AppConstants.SESSION_ID, session_id);
}
```
