package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.Register;
import com.dreamfactory.model.CustomSettings;
import com.dreamfactory.model.Login;
import com.dreamfactory.model.PasswordResponse;
import com.dreamfactory.model.ProfileResponse;
import com.dreamfactory.model.Session;
import com.dreamfactory.model.CustomSetting;
import com.dreamfactory.model.DeviceRequest;
import com.dreamfactory.model.PasswordRequest;
import com.dreamfactory.model.Resources;
import com.dreamfactory.model.Success;
import com.dreamfactory.model.ProfileRequest;
import com.dreamfactory.model.DevicesResponse;
import java.util.*;

public class UserApi {
	String basePath = "http://localhost/rest";
	String serviceName = "/user";
	ApiInvoker apiInvoker = ApiInvoker.getInstance();

	public void addHeader(String key, String value) {
		getInvoker().addDefaultHeader(key, value);
	}

	public ApiInvoker getInvoker() {
		return apiInvoker;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}
	
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}

	public String getServiceName(){
		return this.serviceName;
	}
	
	public Resources getResources () throws ApiException {
		// create path and map variables
		String path = serviceName.replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (Resources) ApiInvoker.deserialize(response, "", Resources.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public CustomSettings getCustomSettings () throws ApiException {
		// create path and map variables
		String path = serviceName + " + /custom".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (CustomSettings) ApiInvoker.deserialize(response, "", CustomSettings.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Success setCustomSettings (CustomSettings body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/custom".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (Success) ApiInvoker.deserialize(response, "", Success.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public CustomSetting getCustomSetting (String setting) throws ApiException {
		// verify required params are set
		if(setting == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/custom/{setting}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "setting" + "\\}", apiInvoker.escapeString(setting.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (CustomSetting) ApiInvoker.deserialize(response, "", CustomSetting.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Success deleteCustomSetting (String setting) throws ApiException {
		// verify required params are set
		if(setting == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/custom/{setting}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "setting" + "\\}", apiInvoker.escapeString(setting.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
			if(response != null){
				return (Success) ApiInvoker.deserialize(response, "", Success.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public DevicesResponse getDevices () throws ApiException {
		// create path and map variables
		String path = serviceName + "/device".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (DevicesResponse) ApiInvoker.deserialize(response, "", DevicesResponse.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Success setDevice (DeviceRequest body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/device".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (Success) ApiInvoker.deserialize(response, "", Success.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public PasswordResponse changePassword (Boolean reset, Boolean login, PasswordRequest body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/password".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(reset)))
			queryParams.put("reset", String.valueOf(reset));
		if(!"null".equals(String.valueOf(login)))
			queryParams.put("login", String.valueOf(login));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (PasswordResponse) ApiInvoker.deserialize(response, "", PasswordResponse.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public ProfileResponse getProfile () throws ApiException {
		// create path and map variables
		String path = serviceName + "/profile".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (ProfileResponse) ApiInvoker.deserialize(response, "", ProfileResponse.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Success updateProfile (ProfileRequest body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/profile".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (Success) ApiInvoker.deserialize(response, "", Success.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Success register (Boolean login, Register body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/register".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(login)))
			queryParams.put("login", String.valueOf(login));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (Success) ApiInvoker.deserialize(response, "", Success.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Session getSession () throws ApiException {
		// create path and map variables
		String path = serviceName + "/session".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (Session) ApiInvoker.deserialize(response, "", Session.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Session login (Login body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/session".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (Session) ApiInvoker.deserialize(response, "", Session.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
	public Success logout () throws ApiException {
		// create path and map variables
		String path = serviceName + "/session".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
			if(response != null){
				return (Success) ApiInvoker.deserialize(response, "", Success.class);
			}
			else {
				return null;
			}
		} catch (ApiException ex) {
			if(ex.getCode() == 404) {
				return null;
			}
			else {
				throw ex;
			}
		}
	}
}

