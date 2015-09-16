Address Book for Android
========================

This repo contains a sample address book application for Android that demonstrates how to use the DreamFactory REST API. It includes new user registration, user login, and CRUD for related tables.

#Getting DreamFactory on your local machine

To download and install DreamFactory, follow the instructions [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/Usage-Options). Alternatively, you can create a [free hosted developer account](http://www.dreamfactory.com) at www.dreamfactory.com if you don't want to install DreamFactory locally.

#Configuring your DreamFactory instance to run the app

- Enable [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) for development purposes.
    - In the admin console, navigate to the Config tab and click on CORS in the left sidebar.
    - Click Add.
    - Set Origin, Paths, and Headers to *.
    - Set Max Age to 0.
    - Allow all HTTP verbs and check the Enabled box.
    - Click update when you are done.
    - More info on setting up CORS is available [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/CORs-Configuration).

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
    - Leave storage service and folder blank. This is a native Android app so it requires no file storage on the server.
    - Click the Import button. If successful, your app will appear on the Apps tab. You may have to refresh the page to see your new app in the list.
    
- Make sure you have a SQL database service named 'db'. Depending on how you installed DreamFactory you may or may not have a 'db' service already available on your instance. You can add one by going to the Services tab in the admin console and creating a new SQL service. Make sure you set the name to 'db'.

#Running the Address Book app

Almost there! Clone this repo to your local machine then open and run the project with Android Studio. To open the project select the android-sdk/android-sdk directory.

Before running the project you need to edit API_KEY in the file AppConstants.java to match the key for your new app. This key can be found by selecting your app from the list on the Apps tab in the admin console.

The default admin console URL is localhost:8080. If your admin console is not at that path, you can change the default path in AppConstants.java.

When the app starts up you can register a new user, or log in as an existing user. Currently the app does not support registering and logging in admin users.

#Example API calls

The app uses the Apache client HTTP library to send REST requests. The request formatting is exposed in case you want to roll your own code or use a third party library to send requests.

The general form of a DreamFactory REST API call is:

`<rest-verb> http[s]://<server-name>/api/v2/[<service-api-name>]/[<resource-path>][?<param-name>=<param-value>]`

An Android call looks like this:

```Java
private String invokeAPIInternal(String host,
								String path,
								String method,
							   	Map<String, String> queryParams,
							   	String body,
							   	Map<String, String> headerParams,
							   	String contentType) throws ApiException
```

Breaking down each parameter:
   - **path** Holds the value of `http[s]://<server-name>/api/v2/[<service-api-name>]/[<resource-path>]` from the generic call. You can include the query parameters here. However, it is easier and cleaner to pass in the query parameters as a map than it is to format them into the url.
   - **method** The HTTP verb.
   - **queryParams** Holds the query parameters.
   - **body** Serialized request body, formatted as either a JSON or XML
   - **headerParams** Users using 1.* need to include the session id and the application name.
   - **contentType** Either json or xml.

There is a lot of boilerplate code for Java because requests need to run asynchronously and you need to check for exceptions. To help keep things clean, we provide a base request class that extends AsyncTask. The light weight class handles boilerplate from error handling and request formatting with almost no extra overhead.

###Database
#####Selecting records (to get or delete)
   - Using filters
    ```Java
    queryParams = new HashMap<>();
    // filter to only the contact_info records related to the contact
    queryParams.put("filter", "id=" + contactRecord.id);
    ```        

   - Related records
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
    ```Java
    // since we don't know the contact_group_relationship ids, change the id field to id
    queryParams = new HashMap<>();
    queryParams.put("id_field", "id");

    // form of request is { "resource": [... ids ...] }
    requestBody = new JSONObject();
    requestBody.put("resource", idArray);
    ```

   - Records by id
    ```Java
    // delete contact records by record id
    // need to remove '[' and ']' from JSON array because ids doesn't take JSON
    queryParams = new HashMap<>();
    queryParams.put("ids", idArray.toString().replace("[", "").replace("]", ""));
    ```

#####Updating records
```Java
// send the record to patch, need to include record id
//requestString = "{\"name\":\"" + record.name + "\",\"id\":" +
        //record.id + "}";
// form is { "id": id, "name":name }
requestString = ApiInvoker.serialize(record);
```

#####Creating records
```Java
// only need to send the groupName in body
// we don't have a groupID yet, so we can't provide one here
requestString = "{\"name\":\"" + name + "\"}";
```

###Working with files
#####creating files: 
```Java
serviceName = "files";
applicationApiKey = AppConstants.API_KEY;

// build rest path for request
endPoint = "profile_images/" + contactId + "/testFile.png";

verb = "POST";

fileRequest = new FileRequest();
fileRequest.setPath(profileImagePath);
```

#####creating folders: 
```Java
serviceName = "files";
applicationApiKey = AppConstants.API_KEY;

// build rest path for request
endPoint = "profile_images/" + contactId + "/";

verb = "POST";
```

#####getting files
```Java
{
  ... part of doSetup ...
  applicationApiKey = AppConstants.API_KEY; // API key used in file path
  verb = "GET";
  // build rest path for request
  endPoint = "profile_images/" + contactRecord.id + "/" + contactRecord.image_url;
  
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
```Java
applicationApiKey = AppConstants.API_KEY;

// build rest path for request
endPoint = "profile_images/" + contactId + "/";

// don't get any folders back in response, only get files
queryParams = new HashMap<>();
queryParams.put("include_folders", "0");
queryParams.put("include_files", "1");
```

#####deleting files/folders
```Java
endPoint = "profile_images/" + contactId + "/";

verb = "DELETE";
// want to delete all the files and folders in the target folder
queryParams = new HashMap<>();
queryParams.put("force", "1");
```

###User login
```Java
{ // part of doSetup
  serviceName = "user";
  endPoint = "session";
  
  verb = "POST";
  
  // post email and password to get back session token
  // need session token to make every call other than login and challenge
  requestBody = new JSONObject();
  requestBody.put("email", mEmail);
  requestBody.put("password", mPassword);
  
  // include application API key
  applicationApiKey = AppConstants.API_KEY;
}

@Override
protected void processResponse(String response) throws ApiException, JSONException {
// store the session_token to be used later on
JSONObject jsonObject = new JSONObject(response);
String session_token = jsonObject.getString("session_token");
if(session_token.length() == 0){
    throw new ApiException(0, "did not get a valid session token in the response");
}
PrefUtil.putString(getApplicationContext(), AppConstants.SESSION_TOKEN, session_token);
}
```

#Additional Resources

More detailed information on the DreamFactory REST API is available [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/REST-API).

The live API documentation included in the admin console is a great way to learn how the DreamFactory REST API works.
Check out how to use the live API docs [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/API-Docs).