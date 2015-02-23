package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.TableSchemas;
import com.dreamfactory.model.StoredProcRequest;
import com.dreamfactory.model.RecordsRequest;
import com.dreamfactory.model.RecordResponse;
import com.dreamfactory.model.FieldSchema;
import com.dreamfactory.model.IdsRequest;
import com.dreamfactory.model.RecordRequest;
import com.dreamfactory.model.FilterRequest;
import com.dreamfactory.model.GetRecordsRequest;
import com.dreamfactory.model.IdsRecordRequest;
import com.dreamfactory.model.TableSchema;
import com.dreamfactory.model.Resources;
import com.dreamfactory.model.FilterRecordRequest;
import com.dreamfactory.model.Success;
import com.dreamfactory.model.RecordsResponse;
import com.dreamfactory.model.StoredProcResponse;
import com.dreamfactory.model.ComponentList;
import java.util.*;

public class SqlazureApi {
	String basePath = "http://192.168.1.12/rest";
	String serviceName = "/sqlazure";
	
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
	public ComponentList getTables (Boolean names_only, Boolean include_schemas) throws ApiException {
		// verify required params are set
		if(names_only == null || include_schemas == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName.replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(names_only)))
			queryParams.put("names_only", String.valueOf(names_only));
		if(!"null".equals(String.valueOf(include_schemas)))
			queryParams.put("include_schemas", String.valueOf(include_schemas));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (ComponentList) ApiInvoker.deserialize(response, "", ComponentList.class);
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
	public ComponentList getAccessComponents (Boolean as_access_components) throws ApiException {
		// verify required params are set
		if(as_access_components == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName.replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(as_access_components)))
			queryParams.put("as_access_components", String.valueOf(as_access_components));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (ComponentList) ApiInvoker.deserialize(response, "", ComponentList.class);
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
	public RecordsResponse getRecordsByFilter (String table_name, String filter, Integer limit, Integer offset, String order, String fields, Boolean include_count, Boolean include_schema, String related) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(filter)))
			queryParams.put("filter", String.valueOf(filter));
		if(!"null".equals(String.valueOf(limit)))
			queryParams.put("limit", String.valueOf(limit));
		if(!"null".equals(String.valueOf(offset)))
			queryParams.put("offset", String.valueOf(offset));
		if(!"null".equals(String.valueOf(order)))
			queryParams.put("order", String.valueOf(order));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(include_count)))
			queryParams.put("include_count", String.valueOf(include_count));
		if(!"null".equals(String.valueOf(include_schema)))
			queryParams.put("include_schema", String.valueOf(include_schema));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse getRecordsByIds (String table_name, String ids, String fields, String id_field, String id_type, Boolean _continue, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || ids == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(ids)))
			queryParams.put("ids", String.valueOf(ids));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse getRecordsByPost (String table_name, GetRecordsRequest body, String fields, String id_field, String id_type, Boolean _continue, String XHTTPMETHOD) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null || XHTTPMETHOD == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse getRecords (String table_name, String ids, String filter, Integer limit, Integer offset, String order, String fields, Boolean include_count, Boolean include_schema, String id_field, String id_type, Boolean _continue, String related) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(ids)))
			queryParams.put("ids", String.valueOf(ids));
		if(!"null".equals(String.valueOf(filter)))
			queryParams.put("filter", String.valueOf(filter));
		if(!"null".equals(String.valueOf(limit)))
			queryParams.put("limit", String.valueOf(limit));
		if(!"null".equals(String.valueOf(offset)))
			queryParams.put("offset", String.valueOf(offset));
		if(!"null".equals(String.valueOf(order)))
			queryParams.put("order", String.valueOf(order));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(include_count)))
			queryParams.put("include_count", String.valueOf(include_count));
		if(!"null".equals(String.valueOf(include_schema)))
			queryParams.put("include_schema", String.valueOf(include_schema));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse createRecords (String table_name, RecordsRequest body, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String XHTTPMETHOD, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse replaceRecordsByIds (String table_name, IdsRecordRequest body, String ids, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(ids)))
			queryParams.put("ids", String.valueOf(ids));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse replaceRecordsByFilter (String table_name, FilterRecordRequest body, String filter, String fields, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(filter)))
			queryParams.put("filter", String.valueOf(filter));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse replaceRecords (String table_name, RecordsRequest body, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse updateRecordsByIds (String table_name, IdsRecordRequest body, String ids, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(ids)))
			queryParams.put("ids", String.valueOf(ids));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse updateRecordsByFilter (String table_name, FilterRecordRequest body, String filter, String fields, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(filter)))
			queryParams.put("filter", String.valueOf(filter));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse updateRecords (String table_name, RecordsRequest body, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse deleteRecordsByIds (String table_name, String ids, IdsRequest body, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String related) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(ids)))
			queryParams.put("ids", String.valueOf(ids));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse deleteRecordsByFilter (String table_name, String filter, FilterRequest body, Boolean force, String fields, String related) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(filter)))
			queryParams.put("filter", String.valueOf(filter));
		if(!"null".equals(String.valueOf(force)))
			queryParams.put("force", String.valueOf(force));
		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordsResponse deleteRecords (String table_name, RecordsRequest body, String fields, String id_field, String id_type, Boolean _continue, Boolean rollback, String filter, String ids, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(_continue)))
			queryParams.put("continue", String.valueOf(_continue));
		if(!"null".equals(String.valueOf(rollback)))
			queryParams.put("rollback", String.valueOf(rollback));
		if(!"null".equals(String.valueOf(filter)))
			queryParams.put("filter", String.valueOf(filter));
		if(!"null".equals(String.valueOf(ids)))
			queryParams.put("ids", String.valueOf(ids));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordsResponse) ApiInvoker.deserialize(response, "", RecordsResponse.class);
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
	public RecordResponse getRecord (String table_name, String id, String fields, String id_field, String id_type, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || id == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (RecordResponse) ApiInvoker.deserialize(response, "", RecordResponse.class);
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
	public RecordResponse createRecord (String table_name, String id, RecordRequest body, String fields, String id_field, String id_type, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || id == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordResponse) ApiInvoker.deserialize(response, "", RecordResponse.class);
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
	public RecordResponse replaceRecord (String table_name, String id, RecordRequest body, String fields, String id_field, String id_type, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || id == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordResponse) ApiInvoker.deserialize(response, "", RecordResponse.class);
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
	public RecordResponse updateRecord (String table_name, String id, RecordRequest body, String fields, String id_field, String id_type, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || id == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
			if(response != null){
				return (RecordResponse) ApiInvoker.deserialize(response, "", RecordResponse.class);
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
	public RecordResponse deleteRecord (String table_name, String id, String fields, String id_field, String id_type, String related) throws ApiException {
		// verify required params are set
		if(table_name == null || id == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(fields)))
			queryParams.put("fields", String.valueOf(fields));
		if(!"null".equals(String.valueOf(id_field)))
			queryParams.put("id_field", String.valueOf(id_field));
		if(!"null".equals(String.valueOf(id_type)))
			queryParams.put("id_type", String.valueOf(id_type));
		if(!"null".equals(String.valueOf(related)))
			queryParams.put("related", String.valueOf(related));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
			if(response != null){
				return (RecordResponse) ApiInvoker.deserialize(response, "", RecordResponse.class);
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
	public Resources getSchemas () throws ApiException {
		// create path and map variables
		String path = serviceName + "/_schema".replaceAll("\\{format\\}","json");

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
	public Resources createTables (TableSchemas body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema".replaceAll("\\{format\\}","json");

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
	public Resources replaceTables (TableSchemas body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema".replaceAll("\\{format\\}","json");

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
	public Resources updateTables (TableSchemas body) throws ApiException {
		// verify required params are set
		if(body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema".replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
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
	public TableSchema describeTable (String table_name, Boolean refresh) throws ApiException {
		// verify required params are set
		if(table_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(refresh)))
			queryParams.put("refresh", String.valueOf(refresh));
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
	public Success createTable (String table_name, TableSchema body) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
	public Success replaceTable (String table_name, TableSchema body) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
	public Success updateTable (String table_name, TableSchema body) throws ApiException {
		// verify required params are set
		if(table_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
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
		String path = serviceName + "/_schema/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
	public FieldSchema describeField (String table_name, String field_name, Boolean refresh) throws ApiException {
		// verify required params are set
		if(table_name == null || field_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(refresh)))
			queryParams.put("refresh", String.valueOf(refresh));
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
	public Success replaceField (String table_name, String field_name, FieldSchema body) throws ApiException {
		// verify required params are set
		if(table_name == null || field_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

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
	public Success updateField (String table_name, String field_name, FieldSchema body) throws ApiException {
		// verify required params are set
		if(table_name == null || field_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_schema/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
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
		String path = serviceName + "/_schema/{table_name}/{field_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "field_name" + "\\}", apiInvoker.escapeString(field_name.toString()));

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
	public Resources getStoredProcs () throws ApiException {
		// create path and map variables
		String path = serviceName + "/_proc".replaceAll("\\{format\\}","json");

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
	public StoredProcResponse callStoredProc(String procedure_name, String wrapper) throws ApiException {
		// verify required params are set
		if(procedure_name == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_proc/{procedure_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "procedure_name" + "\\}", apiInvoker.escapeString(procedure_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(wrapper)))
			queryParams.put("wrapper", String.valueOf(wrapper));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
			if(response != null){
				return (StoredProcResponse) ApiInvoker.deserialize(response, "", StoredProcResponse.class);
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
	public StoredProcResponse callStoredProcWithParams(String procedure_name, StoredProcRequest body, String wrapper) throws ApiException {
		// verify required params are set
		if(procedure_name == null || body == null ) {
			throw new ApiException(400, "missing required params");
		}
		// create path and map variables
		String path = serviceName + "/_proc/{procedure_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "procedure_name" + "\\}", apiInvoker.escapeString(procedure_name.toString()));

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(wrapper)))
			queryParams.put("wrapper", String.valueOf(wrapper));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (StoredProcResponse) ApiInvoker.deserialize(response, "", StoredProcResponse.class);
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

