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

	Add SDK source code as external source. go to project properties > Java Build Path > Source tab > add external sdk folder

####Basic Usage

####This sdk provides apis to connect default dsp services as well as custom services created by developers

```java
########Sample code to connect to 'user' service (//for detailed example please check LoginActivity in to-do sample app)
		UserApi userApi = new UserApi();
		userApi.addHeader("X-DreamFactory-Application-Name", "your app name");
		userApi.setBasePath("your dsp url");
		Login login = new Login();
		login.setEmail("your email");
		login.setPassword("your password");
		Session session =	userApi.login(login);

Sample to connect to 'db' service, for more detail please check ToDoDemoActivity
		RecordRequest record = new RecordRequest();
		record.setName("some text"); 
		DbApi dbApi = new DbApi();  
		dbApi.addHeader("X-DreamFactory-Application-Name", "your app name");
		dbApi.setBasePath("your dsp url");
		dbApi.addHeader("X-DreamFactory-Session-Token", "your session id"); 
		RecordResponse resultRecord = dbApi.createRecord("table name", "123", record, null, null, null,null);
		resultRecord.setName(todoItem);
		log(resultRecord.toString());

Now, suppose we want to create a new service on dsp and connect using df sdk, here are different approaches

ex. 
a)  Lets say we've create a new service called 'city' and a path as 'list' and set up as a 'GET' request
	

		// create an object of APIInvoker
		ApiInvoker invoker  = new ApiInvoker();
		// set default headers, here is app name
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", "your app name");
		 
		String serviceName = "city";
		String endPoint = "list";
		// you can create path the way you like
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params if any
		Map<String, String> queryParams = new HashMap<String, String>();

		// additional header if you want to pass with
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		String response = invoker.invokeAPI(dsp_url, path, "GET", queryParams, null, headerParams, contentType);
		// here this api returns 'String' that you can either parse using json object or map to your model class using sdk deserialise function
		(check function loginServiceDemoApproach_3 in LoginActivity.java )

b) Another example to use 'POST' method for service 'city' with path 'add'
// create an object of APIInvoker
		ApiInvoker invoker  = new ApiInvoker();
		// set default headers, here is app name
		invoker.addDefaultHeader("X-DreamFactory-Application-Name", "your app name");
		 
		String serviceName = "city";
		String endPoint = "add";
		// you can create path the way you like
		String path = new StringBuilder("/").append(serviceName).append("/").append(endPoint).append("/").toString();
		// query params if any
		Map<String, String> queryParams = new HashMap<String, String>();

		// additional header if you want to pass with
		Map<String, String> headerParams = new HashMap<String, String>();
		String contentType = "application/json";

		CityModelClass city = new CityModelClass();
		city.setName("your city name");
		city.setTag("some text");

		// Please check different version of ApiInvoker.invokeApi
		//we can directly pass objects to api invoiker, model classes should be in proper format with JsonProperty. For a sample model class please check DemoLoginModel.java or you can pass json string
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, city, headerParams, contentType);
		or 
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, "json string", headerParams, contentType);

		// here this api returns 'String' that you can either parse using json object or map to your model class using sdk deserialise function
		(check function loginServiceDemoApproach_3 in LoginActivity.java )


		Here are 3 different ways that you can use to call a 'user' api

	/**
	 * This is a df user Api example. It explains how to use user api by using sdk model classes
	 * 
	 */
	private String loginServiceDemoApproach_1() throws ApiException{
		UserApi userApi = new UserApi();
		userApi.addHeader("X-DreamFactory-Application-Name", IAppConstants.APP_NAME);
		userApi.setBasePath(dspUrl + IAppConstants.DSP_URL_SUFIX);
		Login login = new Login();
		login.setEmail(editTextUserId.getText().toString());
		login.setPassword(editTextUserPassword.getText().toString());
		Session session =	userApi.login(login);
		return session.getSession_id();
	}

	/**
	 * This is another way to user use df sdk to connect to backend server by passing json string
	 * This is generic api inoker where you can pass all required header, query parms and body as json string
	 * 
	 * This approach is more useful when we you are creating additional services apart from Dreamfactry default services.
	 * apiInvoiker returns string response and from here you are free to play with data
	 *
	 * For custom service you can also create your owd model class (ex Login) using JacksonAnnotation and directly pass to APIinvoker as described in function loginServiceDemoApproach_3
	 */
	private String loginServiceDemoApproach_2() throws Exception{
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

		JSONObject obj = new JSONObject();
		obj.put("email", userID);
		obj.put("password", userPass);
		String requestStringFromJsonObject = obj.toString();
		String response = invoker.invokeAPI(dsp_url, path, "POST", queryParams, requestStringFromJsonObject, headerParams, contentType);
		JSONObject object = new JSONObject(response);
		return object.getString("session_id");
	}

	/**
	 * This is sample function to demonstrate how to call custom service by passing model object 
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
		
		// here you can also convert this response to model class
		
		Session session = (Session) ApiInvoker.deserialize(response, "", Session.class);

		JSONObject object = new JSONObject(response);
		return object.getString("session_id");
	}
```