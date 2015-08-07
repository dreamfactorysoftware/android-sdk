##DreamFactory Android API

The DreamFactory Android API uses the Apache client HTTP library to send REST requests. The request formatting is exposed in case you want to roll your own code or use a third party library to send requests. 

The general form of a DreamFactory REST API call is: 

`<rest-verb> http[s]://<server-name>/rest/[<service-api-name>]/[<resource-path>][?<param-name>=<param-value>]`

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
   - **path** Holds the value of `http[s]://<server-name>/rest/[<service-api-name>]/[<resource-path>]` from the generic call. You can include the query parameters here. However, it is easier and cleaner to pass in the query parameters as a map than it is to format them into the url.
   - **method** The HTTP verb.
   - **queryParams** Holds the query parameters.
   - **body** Serialized request body, formatted as either a JSON or XML
   - **headerParams** Users using 1.* need to include the session id and the application name.
   - **contentType** Either json or xml.

There is a lot of boilerplate code for Java because requests need to run asynchronously and you need to check for exceptions. To help keep things clean, we provide a base request class that extends AsyncTask. The light weight class handles boilerplate from error handling and request formatting with almost no extra overhead.

####Additional Resources
Detailed instructions to setup and run the "Address Book" sample application as well as a list of API calls used by the "Address Book" sample application are located in the README [here](../com/dreamfactory/sampleapp/README.md#example-api-calls-).

More detailed information on the DreamFactory REST API is available [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/REST-API). 

The live API documentation included in the DreamFactory Admin Console is a great way to learn how the DreamFactory REST API works.
Check out how to use the live API docs [here](https://github.com/dreamfactorysoftware/dsp-core/wiki/API-Docs). You can view and test an example of the live API docs [here](https://dsp-sandman1.cloud.dreamfactory.com/swagger/).
