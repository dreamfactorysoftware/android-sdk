##DreamFactory Android SDK

###Getting Started

As always, clone this repo

####Importing the Project

If you are using eclipse for android development please follow below steps to import project into your existing workspace

	File → Import → General → Existing Projects into Workspace → Next
    
	Select root directory: /path/to/project
    
	Projects → Select All
    
	Uncheck Copy projects into workspace and Add project to working sets
    
	Finish

	Make sure library (libs)path is correct and modify SDK platform if required.

####Basic Usage

#####User  Login

```java

//User Api
// Creating a user api object
	UserApi userApi = new UserApi();
	userApi.addHeader("X-DreamFactory-Application-Name", "your app name");
	userApi.setBasePath("your dsp server url");

	// here is a sample of login user api call,
	Login login = new Login();
	login.setEmail(“your login email");
	login.setPassword("password”;
	Session session =	userApi.login(login);

//For complete example please check LoginActivity.java

class LoginTask extends AsyncTask<Void, Void, String>{

		@Override
		protected String doInBackground(Void... params) {

			UserApi userApi = new UserApi();
			userApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			userApi.setBasePath(dspUrl + IAppConstants.DSP_URL_SUFIX);
			Login login = new Login();
			login.setEmail(editTextUserId.getText().toString());
			login.setPassword(editTextUserPassword.getText().toString());
			try {
				Session session =	userApi.login(login);
				String session_id = session.getSession_id();
			} catch (Exception e) {
				return e.getMessage();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result !=null){ // error message
				String errorMsg = "";
				try {
					JSONObject jObj = new JSONObject(result);
					JSONArray jArray = jObj.getJSONArray("error");
					JSONObject obj = jArray.getJSONObject(0);
					errorMsg = obj.getString("message");
				} catch (JSONException e) {
					errorMsg = result;
				}
			}
			else {
				 // success
			}
		}
		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}
	} 
	


	
```
### Working with the DB Service
```java


// create a DB api object similar to above
	DbApi dbApi = new DbApi();
	dbApi.addHeader("X-DreamFactory-Application-Name", "your app name");
	dbApi.addHeader("X-DreamFactory-Session-Token", "your session token received from login call");
	dbApi.setBasePath(dsp_url); // your dsp url
	// And here is how to get to do records, Please check function getRecords to see how to utilize different parameters
	RecordsResponse records = dbApi.getRecords(IAppConstants.TABLE_NAME,null,null,-1,-1,null,null,false,false,null,null,true,null);

//For complete example please check ToDoDemoActivity.java

class GetRecordsTask extends AsyncTask<Void, RecordsResponse, RecordsResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected RecordsResponse doInBackground(Void... params) {
			DbApi dbApi = new DbApi();
			dbApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			dbApi.addHeader("X-DreamFactory-Session-Token", session_id);
			dbApi.setBasePath(dsp_url);
			try {
				RecordsResponse records = dbApi.getRecords(IAppConstants.TABLE_NAME,null,null,-1,-1,null,null,false,false,null,null,true,null);
				log(records.toString());
				return records;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(RecordsResponse records) {
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.cancel();
			}
			if(records != null){ // success
				adapter = new ToDoAdapter(ToDoDemoActivity.this, records.getRecord());
				list_view.setAdapter(adapter);
			}else{ // some error show dialog
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
				logout();
			}
		}
	}

```
####Working with Files
```java

//Here is how to use File API 
       // create a FilesApi api object similar to above
       FilesApi fileApi = new FilesApi();
	fileApi.addHeader("X-DreamFactory-Application-Name", "your app name");
	fileApi.addHeader("X-DreamFactory-Session-Token", "session id");
	fileApi.setBasePath(“your dap url”);

	// container and folder name
	String containerName = "container name on server"
	String filePathOnServer = "folder name where you want to store file"

	// Create a file request
	FileRequest request = new FileRequest();
	request.setName("file name");  // this will be stored file name on server
	request.setPath("your local file path to upload");
	FileResponse resp = fileApi.createFile(containerName, filePathOnServer, false, request);

//For complete example please check UploadFileDemo.java

class UploadFileTask extends AsyncTask<Object, FileResponse, FileResponse>{
		private String errorMsg;

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected FileResponse doInBackground(Object... params) {
			FilesApi fileApi = new FilesApi();
			fileApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
			fileApi.addHeader("X-DreamFactory-Session-Token", session_id);
			fileApi.setBasePath(dsp_url);
			FileResponse fileToUpload = (FileResponse)params[0];
			// where to upload on server
			String containerName = IAppConstants.CONTAINER_NAME;
			String filePathOnServer = IAppConstants.FOLDER_NAME + "/";

			FileRequest request = new FileRequest();
			request.setName(fileToUpload.getName());  // this will be stored file name on server
			request.setPath(fileToUpload.getPath());
			try {
				FileResponse resp = fileApi.createFile(containerName, filePathOnServer, false, request);
				log(resp.toString());
				return resp;
			} catch (ApiException e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			return null;
		}
		@Override
		protected void onPostExecute(FileResponse resp) {
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.cancel();
			}
			if(resp != null){ // success
				Toast.makeText(getApplicationContext(), "Uploaded successfully.", Toast.LENGTH_LONG).show(); 
			}else{ // some error show dialog
				Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
			}
		}
	}

```
