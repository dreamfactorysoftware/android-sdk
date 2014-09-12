package com.dreamfactory.api;

import java.util.HashMap;
import java.util.Map;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.ComponentList;
import com.dreamfactory.model.Container;
import com.dreamfactory.model.ContainerRequest;
import com.dreamfactory.model.ContainerResponse;
import com.dreamfactory.model.ContainersRequest;
import com.dreamfactory.model.ContainersResponse;
import com.dreamfactory.model.File;
import com.dreamfactory.model.FileRequest;
import com.dreamfactory.model.FileResponse;
import com.dreamfactory.model.Folder;
import com.dreamfactory.model.FolderRequest;
import com.dreamfactory.model.FolderResponse;
import com.dreamfactory.model.Resources;

public class FilesApiDeprecated {
  String basePath = "http://localhost/rest";
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
    String path = "/app".replaceAll("\\{format\\}","json");

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
  public ComponentList getAccessComponents (Boolean as_access_components) throws ApiException {
    // verify required params are set
    if(as_access_components == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app".replaceAll("\\{format\\}","json");

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
  public ContainersResponse getContainers (Boolean include_properties) throws ApiException {
    // verify required params are set
    if(include_properties == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(include_properties)))
      queryParams.put("include_properties", String.valueOf(include_properties));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ContainersResponse) ApiInvoker.deserialize(response, "", ContainersResponse.class);
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
  public ContainersResponse createContainers (ContainersRequest body, Boolean check_exist, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app".replaceAll("\\{format\\}","json");

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
        return (ContainersResponse) ApiInvoker.deserialize(response, "", ContainersResponse.class);
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
  public ContainersResponse deleteContainers (String names, Boolean force) throws ApiException {
    // create path and map variables
    String path = "/app".replaceAll("\\{format\\}","json");

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
        return (ContainersResponse) ApiInvoker.deserialize(response, "", ContainersResponse.class);
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
  public ContainerResponse getContainer (String container, Boolean include_properties, Boolean include_folders, Boolean include_files, Boolean full_tree, Boolean zip) throws ApiException {
    // verify required params are set
    if(container == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(include_properties)))
      queryParams.put("include_properties", String.valueOf(include_properties));
    if(!"null".equals(String.valueOf(include_folders)))
      queryParams.put("include_folders", String.valueOf(include_folders));
    if(!"null".equals(String.valueOf(include_files)))
      queryParams.put("include_files", String.valueOf(include_files));
    if(!"null".equals(String.valueOf(full_tree)))
      queryParams.put("full_tree", String.valueOf(full_tree));
    if(!"null".equals(String.valueOf(zip)))
      queryParams.put("zip", String.valueOf(zip));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ContainerResponse) ApiInvoker.deserialize(response, "", ContainerResponse.class);
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
  public ContainerResponse createContainer (String container, ContainerRequest body, String url, Boolean extract, Boolean clean, Boolean check_exist, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(container == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(url)))
      queryParams.put("url", String.valueOf(url));
    if(!"null".equals(String.valueOf(extract)))
      queryParams.put("extract", String.valueOf(extract));
    if(!"null".equals(String.valueOf(clean)))
      queryParams.put("clean", String.valueOf(clean));
    if(!"null".equals(String.valueOf(check_exist)))
      queryParams.put("check_exist", String.valueOf(check_exist));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (ContainerResponse) ApiInvoker.deserialize(response, "", ContainerResponse.class);
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
  public Container updateContainerProperties (String container, Container body) throws ApiException {
    // verify required params are set
    if(container == null || body == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Container) ApiInvoker.deserialize(response, "", Container.class);
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
  public ContainerResponse deleteContainer (String container, Boolean force, Boolean content_only) throws ApiException {
    // verify required params are set
    if(container == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(content_only)))
      queryParams.put("content_only", String.valueOf(content_only));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (ContainerResponse) ApiInvoker.deserialize(response, "", ContainerResponse.class);
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
  public FolderResponse getFolder (String container, String folder_path, Boolean include_properties, Boolean include_folders, Boolean include_files, Boolean full_tree, Boolean zip) throws ApiException {
    // verify required params are set
    if(container == null || folder_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{folder_path}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "folder_path" + "\\}", apiInvoker.escapeString(folder_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(include_properties)))
      queryParams.put("include_properties", String.valueOf(include_properties));
    if(!"null".equals(String.valueOf(include_folders)))
      queryParams.put("include_folders", String.valueOf(include_folders));
    if(!"null".equals(String.valueOf(include_files)))
      queryParams.put("include_files", String.valueOf(include_files));
    if(!"null".equals(String.valueOf(full_tree)))
      queryParams.put("full_tree", String.valueOf(full_tree));
    if(!"null".equals(String.valueOf(zip)))
      queryParams.put("zip", String.valueOf(zip));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (FolderResponse) ApiInvoker.deserialize(response, "", FolderResponse.class);
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
  public FolderResponse createFolder (String container, String folder_path, FolderRequest body, String url, Boolean extract, Boolean clean, Boolean check_exist, String XHTTPMETHOD) throws ApiException {
    // verify required params are set
    if(container == null || folder_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{folder_path}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "folder_path" + "\\}", apiInvoker.escapeString(folder_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(url)))
      queryParams.put("url", String.valueOf(url));
    if(!"null".equals(String.valueOf(extract)))
      queryParams.put("extract", String.valueOf(extract));
    if(!"null".equals(String.valueOf(clean)))
      queryParams.put("clean", String.valueOf(clean));
    if(!"null".equals(String.valueOf(check_exist)))
      queryParams.put("check_exist", String.valueOf(check_exist));
    headerParams.put("X-HTTP-METHOD", XHTTPMETHOD);
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
      if(response != null){
        return (FolderResponse) ApiInvoker.deserialize(response, "", FolderResponse.class);
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
  public Folder updateFolderProperties (String container, String folder_path, Folder body) throws ApiException {
    // verify required params are set
    if(container == null || folder_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{folder_path}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "folder_path" + "\\}", apiInvoker.escapeString(folder_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (Folder) ApiInvoker.deserialize(response, "", Folder.class);
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
  public FolderResponse deleteFolder (String container, String folder_path, Boolean force, Boolean content_only) throws ApiException {
    // verify required params are set
    if(container == null || folder_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{folder_path}/".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "folder_path" + "\\}", apiInvoker.escapeString(folder_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(force)))
      queryParams.put("force", String.valueOf(force));
    if(!"null".equals(String.valueOf(content_only)))
      queryParams.put("content_only", String.valueOf(content_only));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (FolderResponse) ApiInvoker.deserialize(response, "", FolderResponse.class);
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
  public FileResponse getFile (String container, String file_path, Boolean include_properties, Boolean content, Boolean download) throws ApiException {
    // verify required params are set
    if(container == null || file_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{file_path}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "file_path" + "\\}", apiInvoker.escapeString(file_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(include_properties)))
      queryParams.put("include_properties", String.valueOf(include_properties));
    if(!"null".equals(String.valueOf(content)))
      queryParams.put("content", String.valueOf(content));
    if(!"null".equals(String.valueOf(download)))
      queryParams.put("download", String.valueOf(download));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (FileResponse) ApiInvoker.deserialize(response, "", FileResponse.class);
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
  public FileResponse createFile (String container, String file_path, Boolean check_exist, FileRequest body) throws ApiException {
    // verify required params are set
    if(container == null || file_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{file_path}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "file_path" + "\\}", apiInvoker.escapeString(file_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(check_exist)))
      queryParams.put("check_exist", String.valueOf(check_exist));
//    String contentType = "application/json";
    String contentType = body.getContent_type(); //"application/octet-stream";
    if(contentType == null){
    	contentType = "application/octet-stream";
    }
    headerParams.put("X-File-Name", body.getName());
    
    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType, body);
      if(response != null){
        return (FileResponse) ApiInvoker.deserialize(response, "", FileResponse.class);
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
  public FileResponse replaceFile (String container, String file_path, FileRequest body) throws ApiException {
    // verify required params are set
    if(container == null || file_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{file_path}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "file_path" + "\\}", apiInvoker.escapeString(file_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PUT", queryParams, body, headerParams, contentType);
      if(response != null){
        return (FileResponse) ApiInvoker.deserialize(response, "", FileResponse.class);
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
  public File updateFileProperties (String container, String file_path, File body) throws ApiException {
    // verify required params are set
    if(container == null || file_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{file_path}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "file_path" + "\\}", apiInvoker.escapeString(file_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "PATCH", queryParams, body, headerParams, contentType);
      if(response != null){
        return (File) ApiInvoker.deserialize(response, "", File.class);
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
  public FileResponse deleteFile (String container, String file_path) throws ApiException {
    // verify required params are set
    if(container == null || file_path == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/app/{container}/{file_path}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "container" + "\\}", apiInvoker.escapeString(container.toString())).replaceAll("\\{" + "file_path" + "\\}", apiInvoker.escapeString(file_path.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "DELETE", queryParams, null, headerParams, contentType);
      if(response != null){
        return (FileResponse) ApiInvoker.deserialize(response, "", FileResponse.class);
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

