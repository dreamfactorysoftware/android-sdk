package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.Constant;
import com.dreamfactory.model.RolesResponse;
import com.dreamfactory.model.AppGroupsResponse;
import com.dreamfactory.model.CustomSettings;
import com.dreamfactory.model.UsersRequest;
import com.dreamfactory.model.EmailTemplateRequest;
import com.dreamfactory.model.DeviceResponse;
import com.dreamfactory.model.UserRequest;
import com.dreamfactory.model.ConfigResponse;
import com.dreamfactory.model.CustomSetting;
import com.dreamfactory.model.EmailTemplatesRequest;
import com.dreamfactory.model.ServiceResponse;
import com.dreamfactory.model.ServiceRequest;
import com.dreamfactory.model.RoleRequest;
import com.dreamfactory.model.ServicesResponse;
import com.dreamfactory.model.AppsRequest;
import com.dreamfactory.model.AppGroupResponse;
import com.dreamfactory.model.Constants;
import com.dreamfactory.model.Resources;
import com.dreamfactory.model.EmailTemplatesResponse;
import com.dreamfactory.model.AppsResponse;
import com.dreamfactory.model.EmailTemplateResponse;
import com.dreamfactory.model.Success;
import com.dreamfactory.model.ServicesRequest;
import com.dreamfactory.model.AppRequest;
import com.dreamfactory.model.AppGroupsRequest;
import com.dreamfactory.model.AppGroupRequest;
import com.dreamfactory.model.RolesRequest;
import com.dreamfactory.model.UserResponse;
import com.dreamfactory.model.DevicesResponse;
import com.dreamfactory.model.AppResponse;
import com.dreamfactory.model.UsersResponse;
import com.dreamfactory.model.RoleResponse;
import com.dreamfactory.model.ConfigRequest;
import java.util.*;

public class SystemApi {
  String basePath = "http://192.168.1.23/rest";
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
    String path = "/system".replaceAll("\\{format\\}","json");

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
  public AppsResponse getApps (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema) throws ApiException {
    // create path and map variables
    String path = "/system/app".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppsResponse) ApiInvoker.deserialize(response, "", AppsResponse.class);
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
  public AppsResponse createApps (AppsRequest body, String fields, String related, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (AppsResponse) ApiInvoker.deserialize(response, "", AppsResponse.class);
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
  public AppsResponse updateApps (AppsRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (AppsResponse) ApiInvoker.deserialize(response, "", AppsResponse.class);
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
  public AppsResponse deleteApps (String ids, Boolean force, String fields, String related, Boolean delete_storage) throws ApiException {
    // create path and map variables
    String path = "/system/app".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(delete_storage)))
      queryParams.put("delete_storage", String.valueOf(delete_storage));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppsResponse) ApiInvoker.deserialize(response, "", AppsResponse.class);
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
  public AppResponse getApp (String id, String fields, String related, Boolean pkg, Boolean include_files, Boolean include_services, Boolean include_schema, Boolean sdk) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(pkg)))
      queryParams.put("pkg", String.valueOf(pkg));
    if(!"null".equals(String.valueOf(include_files)))
      queryParams.put("include_files", String.valueOf(include_files));
    if(!"null".equals(String.valueOf(include_services)))
      queryParams.put("include_services", String.valueOf(include_services));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    if(!"null".equals(String.valueOf(sdk)))
      queryParams.put("sdk", String.valueOf(sdk));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppResponse) ApiInvoker.deserialize(response, "", AppResponse.class);
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
  public AppResponse updateApp (String id, AppRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (AppResponse) ApiInvoker.deserialize(response, "", AppResponse.class);
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
  public AppResponse deleteApp (String id, String fields, String related, Boolean delete_storage) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(delete_storage)))
      queryParams.put("delete_storage", String.valueOf(delete_storage));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppResponse) ApiInvoker.deserialize(response, "", AppResponse.class);
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
  public AppGroupsResponse getAppGroups (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema) throws ApiException {
    // create path and map variables
    String path = "/system/app_group".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppGroupsResponse) ApiInvoker.deserialize(response, "", AppGroupsResponse.class);
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
  public AppGroupsResponse createAppGroups (AppGroupsRequest body, String fields, String related, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app_group".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (AppGroupsResponse) ApiInvoker.deserialize(response, "", AppGroupsResponse.class);
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
  public AppGroupsResponse updateAppGroups (AppGroupsRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app_group".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (AppGroupsResponse) ApiInvoker.deserialize(response, "", AppGroupsResponse.class);
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
  public AppGroupsResponse deleteAppGroups (String ids, Boolean force, String fields, String related) throws ApiException {
    // create path and map variables
    String path = "/system/app_group".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppGroupsResponse) ApiInvoker.deserialize(response, "", AppGroupsResponse.class);
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
  public AppGroupResponse getAppGroup (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app_group/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppGroupResponse) ApiInvoker.deserialize(response, "", AppGroupResponse.class);
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
  public AppGroupResponse updateAppGroup (String id, AppGroupRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app_group/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (AppGroupResponse) ApiInvoker.deserialize(response, "", AppGroupResponse.class);
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
  public AppGroupResponse deleteAppGroup (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/app_group/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (AppGroupResponse) ApiInvoker.deserialize(response, "", AppGroupResponse.class);
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
  public ConfigResponse getConfig () throws ApiException {
    // create path and map variables
    String path = "/system/config".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ConfigResponse) ApiInvoker.deserialize(response, "", ConfigResponse.class);
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
  public ConfigResponse setConfig (ConfigRequest body) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/config".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (ConfigResponse) ApiInvoker.deserialize(response, "", ConfigResponse.class);
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
  public Constants getConstants () throws ApiException {
    // create path and map variables
    String path = "/system/constant".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Constants) ApiInvoker.deserialize(response, "", Constants.class);
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
  public Constant getConstant (String type) throws ApiException {
    // verify required params are set
    if(type == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/constant/{type}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "type" + "\\}", apiInvoker.escapeString(type.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Constant) ApiInvoker.deserialize(response, "", Constant.class);
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
    String path = "/system/custom".replaceAll("\\{format\\}","json");

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
    String path = "/system/custom".replaceAll("\\{format\\}","json");

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
    String path = "/system/custom/{setting}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "setting" + "\\}", apiInvoker.escapeString(setting.toString()));

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
    String path = "/system/custom/{setting}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "setting" + "\\}", apiInvoker.escapeString(setting.toString()));

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
  public DevicesResponse getDevices (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema) throws ApiException {
    // create path and map variables
    String path = "/system/device".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
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
  public DevicesResponse deleteDevices (String ids, Boolean force, String fields, String related) throws ApiException {
    // create path and map variables
    String path = "/system/device".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
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
  public DeviceResponse getDevice (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/device/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (DeviceResponse) ApiInvoker.deserialize(response, "", DeviceResponse.class);
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
  public DeviceResponse deleteDevice (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/device/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (DeviceResponse) ApiInvoker.deserialize(response, "", DeviceResponse.class);
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
  public EmailTemplatesResponse getEmailTemplates (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema) throws ApiException {
    // create path and map variables
    String path = "/system/email_template".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (EmailTemplatesResponse) ApiInvoker.deserialize(response, "", EmailTemplatesResponse.class);
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
  public EmailTemplatesResponse createEmailTemplates (EmailTemplatesRequest body, String fields, String related, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/email_template".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (EmailTemplatesResponse) ApiInvoker.deserialize(response, "", EmailTemplatesResponse.class);
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
  public EmailTemplatesResponse updateEmailTemplates (EmailTemplatesRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/email_template".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (EmailTemplatesResponse) ApiInvoker.deserialize(response, "", EmailTemplatesResponse.class);
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
  public EmailTemplatesResponse deleteEmailTemplates (String ids, Boolean force, String fields, String related) throws ApiException {
    // create path and map variables
    String path = "/system/email_template".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (EmailTemplatesResponse) ApiInvoker.deserialize(response, "", EmailTemplatesResponse.class);
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
  public EmailTemplateResponse getEmailTemplate (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/email_template/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (EmailTemplateResponse) ApiInvoker.deserialize(response, "", EmailTemplateResponse.class);
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
  public EmailTemplateResponse updateEmailTemplate (String id, EmailTemplateRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/email_template/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (EmailTemplateResponse) ApiInvoker.deserialize(response, "", EmailTemplateResponse.class);
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
  public EmailTemplateResponse deleteEmailTemplate (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/email_template/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (EmailTemplateResponse) ApiInvoker.deserialize(response, "", EmailTemplateResponse.class);
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
  public RolesResponse getRoles (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema) throws ApiException {
    // create path and map variables
    String path = "/system/role".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (RolesResponse) ApiInvoker.deserialize(response, "", RolesResponse.class);
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
  public RolesResponse createRoles (RolesRequest body, String fields, String related, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/role".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (RolesResponse) ApiInvoker.deserialize(response, "", RolesResponse.class);
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
  public RolesResponse updateRoles (RolesRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/role".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (RolesResponse) ApiInvoker.deserialize(response, "", RolesResponse.class);
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
  public RolesResponse deleteRoles (String ids, Boolean force, String fields, String related) throws ApiException {
    // create path and map variables
    String path = "/system/role".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (RolesResponse) ApiInvoker.deserialize(response, "", RolesResponse.class);
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
  public RoleResponse getRole (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/role/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (RoleResponse) ApiInvoker.deserialize(response, "", RoleResponse.class);
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
  public RoleResponse updateRole (String id, RoleRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/role/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (RoleResponse) ApiInvoker.deserialize(response, "", RoleResponse.class);
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
  public RoleResponse deleteRole (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/role/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (RoleResponse) ApiInvoker.deserialize(response, "", RoleResponse.class);
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
  public ServicesResponse getServices (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema) throws ApiException {
    // create path and map variables
    String path = "/system/service".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ServicesResponse) ApiInvoker.deserialize(response, "", ServicesResponse.class);
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
  public ServicesResponse createServices (ServicesRequest body, String fields, String related, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/service".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (ServicesResponse) ApiInvoker.deserialize(response, "", ServicesResponse.class);
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
  public ServicesResponse updateServices (ServicesRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/service".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (ServicesResponse) ApiInvoker.deserialize(response, "", ServicesResponse.class);
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
  public ServicesResponse deleteServices (String ids, Boolean force, String fields, String related) throws ApiException {
    // create path and map variables
    String path = "/system/service".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ServicesResponse) ApiInvoker.deserialize(response, "", ServicesResponse.class);
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
  public ServiceResponse getService (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/service/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ServiceResponse) ApiInvoker.deserialize(response, "", ServiceResponse.class);
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
  public ServiceResponse updateService (String id, ServiceRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/service/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (ServiceResponse) ApiInvoker.deserialize(response, "", ServiceResponse.class);
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
  public ServiceResponse deleteService (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/service/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ServiceResponse) ApiInvoker.deserialize(response, "", ServiceResponse.class);
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
  public UsersResponse getUsers (String ids, String filter, Integer limit, String order, Integer offset, String fields, String related, Boolean include_count, Boolean include_schema, String file) throws ApiException {
    // create path and map variables
    String path = "/system/user".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(limit)))
      queryParams.put("limit", String.valueOf(limit));
    if(!"null".equals(String.valueOf(order)))
      queryParams.put("order", String.valueOf(order));
    if(!"null".equals(String.valueOf(offset)))
      queryParams.put("offset", String.valueOf(offset));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    if(!"null".equals(String.valueOf(include_count)))
      queryParams.put("include_count", String.valueOf(include_count));
    if(!"null".equals(String.valueOf(include_schema)))
      queryParams.put("include_schema", String.valueOf(include_schema));
    if(!"null".equals(String.valueOf(file)))
      queryParams.put("file", String.valueOf(file));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (UsersResponse) ApiInvoker.deserialize(response, "", UsersResponse.class);
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
  public UsersResponse createUsers (UsersRequest body, String fields, String related, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/user".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (UsersResponse) ApiInvoker.deserialize(response, "", UsersResponse.class);
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
  public UsersResponse updateUsers (UsersRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/user".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (UsersResponse) ApiInvoker.deserialize(response, "", UsersResponse.class);
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
  public UsersResponse deleteUsers (String ids, Boolean force, String fields, String related) throws ApiException {
    // create path and map variables
    String path = "/system/user".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(ids)))
      queryParams.put("ids", String.valueOf(ids));
    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (UsersResponse) ApiInvoker.deserialize(response, "", UsersResponse.class);
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
  public UserResponse getUser (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/user/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (UserResponse) ApiInvoker.deserialize(response, "", UserResponse.class);
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
  public UserResponse updateUser (String id, UserRequest body, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/user/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (UserResponse) ApiInvoker.deserialize(response, "", UserResponse.class);
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
  public UserResponse deleteUser (String id, String fields, String related) throws ApiException {
    // verify required params are set
    if(id == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/system/user/{id}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "id" + "\\}", apiInvoker.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(fields)))
      queryParams.put("fields", String.valueOf(fields));
    if(!"null".equals(String.valueOf(related)))
      queryParams.put("related", String.valueOf(related));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (UserResponse) ApiInvoker.deserialize(response, "", UserResponse.class);
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

