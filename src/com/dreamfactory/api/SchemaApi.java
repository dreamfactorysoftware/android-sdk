package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.FieldSchema;
import com.dreamfactory.model.Fields;
import com.dreamfactory.model.Resources;
import com.dreamfactory.model.TableSchema;
import com.dreamfactory.model.Success;
import com.dreamfactory.model.Tables;
import java.util.*;

public class SchemaApi {
	String basePath = "http://localhost:9080/rest";
	String serviceName = "/schema";
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
	public Resources createTables (Tables body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName.replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
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
	public Resources updateTables (Tables body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName.replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
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
	public TableSchema describeTable (String table_name) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (TableSchema) ApiInvoker.deserialize(response, "", TableSchema.class);
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
	public Success createFields (String table_name, Fields body) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
	public Success updateFields (String table_name, Fields body) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
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
	public Success deleteTable (String table_name) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
	public FieldSchema describeField (String table_name, String field_name) throws ApiException {
		// verify required params are set
		if(table_name == null || field_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (FieldSchema) ApiInvoker.deserialize(response, "", FieldSchema.class);
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
	public Success updateField (String table_name, String field_name, FieldSchema body) throws ApiException {
		// verify required params are set
		if(table_name == null || field_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
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
	public Success deleteField (String table_name, String field_name) throws ApiException {
		// verify required params are set
		if(table_name == null || field_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

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

