##DreamFactory Android SDK

###Getting Started

To use the Android SDK, simply clone this repo.

####Importing the Project

If you are using Eclipse for Android development please follow the steps below to import the project into your existing workspace.

	File → Import → General → Existing Projects into Workspace → Next
    
	Select root directory: /path/to/project
    
	Projects → Select All
    
	Uncheck Copy projects into workspace and Add project to working sets
    
	Finish

	Make sure library (libs) path is correct and modify SDK platform if required.

	Add SDK source code as external source. Go to Project Properties > Java Build Path > Source tab > add external SDK folder

####Basic Usage

####This SDK provides APIs to connect default DSP services as well as custom services created by developers.

```java

Sample code to connect to the 'user' service, for more details please check LoginActivity in the to-do sample app.
		UserApi userApi = new UserApi();
		userApi.addHeader("X-DreamFactory-Application-Name", "your app name");
		userApi.setBasePath("your DSP url");
		Login login = new Login();
		login.setEmail("your email");
		login.setPassword("your password");
		Session session = userApi.login(login);

Sample to connect to 'db' service, for more details please check ToDoDemoActivity in the to-do sample app.
		RecordRequest record = new RecordRequest();
		record.setName("some text"); 
		DbApi dbApi = new DbApi();  
		dbApi.addHeader("X-DreamFactory-Application-Name", "your app name");
		dbApi.setBasePath("your DSP url");
		dbApi.addHeader("X-DreamFactory-Session-Token", "your session id"); 
		RecordResponse resultRecord = dbApi.createRecord("table name", "123", record, null, null, null, null);
		resultRecord.setName(todoItem);
		log(resultRecord.toString());

 
Now, suppose we want to create a new REST service on DSP and connect to it using the Android SDK. Here are some examples:

a) Let's say we've create a new service called 'city', path as 'list', and want to 'GET' a list of cities.

		// create an object of APIInvoker
		ApiInvoker invoker  = new ApiInvoker();
		// set default headers, here the header is app name
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", "your app name");
		 
		String serviceName = "city";
		String endPoint = "list";
		// you can create the path the way you like
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params, if any
		Map<String, String> queryParams = new HashMap<String, String>();

		// additional header if you want to pass with
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		String response = invoker.invokeAPI(dsp_url, path, "GET", queryParams, null, headerParams, contentType);
		// here this API returns 'String' that you can either parse using a JSON object 
		// or map to your model class using the SDK's deserialize function
		(check function loginServiceDemoApproach_3 in LoginActivity.java )

b) Another example to use 'POST' method for service 'city' with path 'add'.

		// create an object of APIInvoker
		ApiInvoker invoker  = new ApiInvoker();
		// set default headers, here the header is app name
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", "your app name");
		 
		String serviceName = "city";
		String endPoint = "add";
		// you can create path the way you like
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params, if any
		Map<String, String> queryParams = new HashMap<String, String>();

		// additional header if you want to pass with
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		CityModelClass city = new CityModelClass();
		city.setName("your city name");
		city.setTag("some text");

		// Please check different version of ApiInvoker.invokeApi
		// We can directly pass objects to API invoker, model classes should be in proper format with JsonProperty. 
		// For a sample model class please check DemoLoginModel.java or you can pass JSON string.
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, city, headerParams, contentType);
		or 
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, "json string", headerParams, contentType);

		// here this API returns 'String' that you can either parse using a JSON object 
		// or map to your model class using the SDK's deserialize function
		(check function loginServiceDemoApproach_3 in LoginActivity.java )


		Here are three different ways you can call the 'user' API.

	/**
	 * This is a user API example. It explains how to call the User API by using SDK model classes.
	 * 
	 */
	private String loginServiceDemoApproach_1() throws ApiException{
		UserApi userApi = new UserApi();
		userApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		userApi.setBasePath(dspUrl + IAppConstants.DSP_URL_SUFIX);
		Login login = new Login();
		login.setEmail(editTextUserId.getText().toString());
		login.setPassword(editTextUserPassword.getText().toString());
		Session session = userApi.login(login);
		return session.getSession_id();
	}

	/**
	 * This is another way to use the SDK to connect to the backend server by passing a JSON string.
	 * This is the generic API invoker where you can pass all required headers, query params, and body as a JSON string.
	 * 
	 * This approach is more useful when we you are creating additional services apart from DreamFactory's default services.
	 * apiInvoiker returns a string response and from here you are free to play with data.
	 *
	 * For custom services you can also create your own model class (e.g. Login) using JacksonAnnotation 
	 * and directly pass to API invoker as described in the loginServiceDemoApproach_3 function. 
	 */
	private String loginServiceDemoApproach_2() throws Exception{
		ApiInvoker invoker = new ApiInvoker();
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		// create path and map variables
		String serviceName = "user";
		String endPoint = "session";
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		JSONObject obj = new JSONObject();
		obj.put("email", userID);
		obj.put("password", userPass);
		String requestStringFromJsonObject = obj.toString();
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, requestStringFromJsonObject, headerParams, contentType);
		JSONObject object = new JSONObject(response);
		return object.getString("session_id");
	}

	/**
	 * This is sample function to demonstrate how to call a custom service by passing model object. 
	 */ 

	private String loginServiceDemoApproach_3() throws Exception{
		ApiInvoker invoker  = new ApiInvoker();
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		// create path and map variables
		String serviceName = "user";
		String endPoint = "session";
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		DemoLoginModel login = new DemoLoginModel();
		login.setEmail(editTextUserId.getText().toString());
		login.setPassword(editTextUserPassword.getText().toString());
		
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, login, headerParams, contentType);
		
		// Here you can also convert this response to model class.
		
		Session session = (Session) ApiInvoker.deserialize(response, "", Session.class);

		JSONObject object = new JSONObject(response);
		return object.getString("session_id");
	}
```