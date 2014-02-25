package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.Records;
import com.dreamfactory.model.Record;
import com.dreamfactory.model.Tables;
import java.util.*;

public class SimpleDBApi {
  String basePath = "https://next.cloud.dreamfactory.com/rest";
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

  public Tables getTables (Boolean include_properties, String names) throws ApiException {
    // create path and map variables
    String path = "/SimpleDB".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(include_properties)))
      queryParams.put("include_properties", String.valueOf(include_properties));
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
  public Tables createTables (Tables body, Boolean check_exist, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(check_exist)))
      queryParams.put("check_exist", String.valueOf(check_exist));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
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
  public Tables updateTableProperties (Tables body) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
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
  public Tables deleteTables (String names, Boolean force) throws ApiException {
    // create path and map variables
    String path = "/SimpleDB".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(names)))
      queryParams.put("names", String.valueOf(names));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
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
  public Records getRecords (String table_name, String ids, String filter, Integer limit, Integer offset, String order, String fields, Boolean include_count, String id_field) throws ApiException {
    // verify required params are set
    if(table_name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

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
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Records) ApiInvoker.deserialize(response, "", Records.class);
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
  public Records createRecords (String table_name, Records body, String id_field, String fields, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(table_name == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Records) ApiInvoker.deserialize(response, "", Records.class);
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
  public Records replaceRecords (String table_name, Records body, String ids, String filter, String id_field, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Records) ApiInvoker.deserialize(response, "", Records.class);
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
  public Records updateRecords (String table_name, Records body, String ids, String filter, String id_field, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Records) ApiInvoker.deserialize(response, "", Records.class);
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
  public Records deleteRecords (String table_name, String ids, String filter, Boolean force, String id_field, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Records) ApiInvoker.deserialize(response, "", Records.class);
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
  public Record getRecord (String table_name, String id, Boolean properties_only, String id_field, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(properties_only)))
      queryParams.put("properties_only", String.valueOf(properties_only));
    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Record) ApiInvoker.deserialize(response, "", Record.class);
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
  public Record createRecord (String table_name, String id, String id_field, Record body, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Record) ApiInvoker.deserialize(response, "", Record.class);
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
  public Record replaceRecord (String table_name, String id, String id_field, Record body, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Record) ApiInvoker.deserialize(response, "", Record.class);
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
  public Record updateRecord (String table_name, String id, String id_field, Record body, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Record) ApiInvoker.deserialize(response, "", Record.class);
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
  public Record deleteRecord (String table_name, String id, String id_field, String fields) throws ApiException {
    // verify required params are set
    if(table_name == null || id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/SimpleDB/{table_name}/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "table_name" + "\\}", apiInvoker.escapeString(table_name.toString())).replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(id_field)))
      queryParams.put("id_field", String.valueOf(id_field));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Record) ApiInvoker.deserialize(response, "", Record.class);
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

