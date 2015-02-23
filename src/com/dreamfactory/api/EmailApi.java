package com.dreamfactory.api;

import com.dreamfactory.client.ApiException;
import com.dreamfactory.client.ApiInvoker;
import com.dreamfactory.model.EmailRequest;
import com.dreamfactory.model.EmailResponse;
import java.util.*;

public class EmailApi {
	String basePath = "http://192.168.1.12/rest";
	String serviceName = "/files";

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

	public EmailResponse sendEmail (String template, Integer template_id, EmailRequest body) throws ApiException {
		// create path and map variables
		String path = serviceName.replaceAll("\\{format\\}","json");

		// query params
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> headerParams = new HashMap<String, String>();

		if(!"null".equals(String.valueOf(template)))
			queryParams.put("template", String.valueOf(template));
		if(!"null".equals(String.valueOf(template_id)))
			queryParams.put("template_id", String.valueOf(template_id));
		String contentType = "application/json";

		try {
			String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams, contentType);
			if(response != null){
				return (EmailResponse) ApiInvoker.deserialize(response, "", EmailResponse.class);
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

