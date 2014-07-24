package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.RecordsRequest;
import com.dreamfactory.model.RecordResponse;
import com.dreamfactory.model.RecordRequest;
import com.dreamfactory.model.Resources;
import com.dreamfactory.model.RecordsResponse;
import com.dreamfactory.model.Tables;
import java.util.*;

public class DbApi {
  String basePath = "http://localhost:9080/rest";
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

  public Resources getResources () throws ApiException {
    // create path and map variables
    String path = "/db".replaceAll("\\{format\\}","json");

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
  public Tables getTables (String names) throws ApiException {
    // verify required params are set
    if(names == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(names)))
      queryParams.put("names", String.valueOf(names));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Tables) ApiInvoker.deserialize(response, "", Tables.class);
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
  public RecordsResponse getRecords (String table_name, String ids, String filter, Integer limit, Integer offset, String order, String fields, String related, Boolean include_count, Boolean include_schema, String id_field, String id_type, Boolean _continue) throws ApiException {
    // verify required params are set
    if(table_name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
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
  public RecordsResponse createRecords (String table_name, RecordsRequest body, String fields, String related, String id_field, String id_type, Boolean _continue, Boolean rollback, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(table_name == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
    if(!"null".equals(String.valueOf(_continue)))
      queryParams.put("continue", String.valueOf(_continue));
    if(!"null".equals(String.valueOf(rollback)))
      queryParams.put("rollback", String.valueOf(rollback));
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
  public RecordsResponse updateRecords (String table_name, RecordsRequest body, String ids, String filter, String fields, String related, String id_field, String id_type, Boolean _continue, Boolean rollback) throws ApiException {
    // verify required params are set
    if(table_name == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
    if(!"null".equals(String.valueOf(_continue)))
      queryParams.put("continue", String.valueOf(_continue));
    if(!"null".equals(String.valueOf(rollback)))
      queryParams.put("rollback", String.valueOf(rollback));
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
  public RecordsResponse deleteRecords (String table_name, String ids, String filter, Boolean force, String fields, String related, String id_field, String id_type, Boolean _continue, Boolean rollback) throws ApiException {
    // verify required params are set
    if(table_name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
    if(!"null".equals(String.valueOf(_continue)))
      queryParams.put("continue", String.valueOf(_continue));
    if(!"null".equals(String.valueOf(rollback)))
      queryParams.put("rollback", String.valueOf(rollback));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
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
  public RecordResponse getRecord (String table_name, String id, String fields, String related, String id_field, String id_type) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
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
  public RecordResponse createRecord (String table_name, String id, RecordRequest body, String fields, String related, String id_field, String id_type) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
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
  public RecordResponse updateRecord (String table_name, String id, RecordRequest body, String fields, String related, String id_field, String id_type) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
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
  public RecordResponse deleteRecord (String table_name, String id, String fields, String related, String id_field, String id_type) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/db/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(id_type)))
      queryParams.put("id_type", String.valueOf(id_type));
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
  }

