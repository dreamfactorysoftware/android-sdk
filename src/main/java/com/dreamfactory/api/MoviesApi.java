package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.Movies;
import java.util.*;

public class MoviesApi {
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

  public Movies getMovies (String q) throws ApiException {
    // verify required params are set
    if(q == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/movies".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(q)))
      queryParams.put("q", String.valueOf(q));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (Movies) ApiInvoker.deserialize(response, "", Movies.class);
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

