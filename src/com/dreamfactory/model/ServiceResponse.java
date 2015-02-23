package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RelatedApps;
import com.dreamfactory.model.RelatedRoles;
public class ServiceResponse {
  /* Identifier of this service. */
  @JsonProperty("id")
  private Integer id = null;
  /* Displayable name of this service. */
  @JsonProperty("name")
  private String name = null;
  /* Name of the service to use in API transactions. */
  @JsonProperty("api_name")
  private String api_name = null;
  /* Description of this service. */
  @JsonProperty("description")
  private String description = null;
  /* True if this service is active for use. */
  @JsonProperty("is_active")
  private Boolean is_active = null;
  /* One of the supported service types. */
  @JsonProperty("type")
  private String type = null;
  /* One of the supported enumerated service types. */
  @JsonProperty("type_id")
  private Integer type_id = null;
  /* They supported storage service type. */
  @JsonProperty("storage_type")
  private String storage_type = null;
  /* One of the supported enumerated storage service types. */
  @JsonProperty("storage_type_id")
  private Integer storage_type_id = null;
  /* Any credentials data required by the service. */
  @JsonProperty("credentials")
  private String credentials = null;
  /* The format of the returned data of the service. */
  @JsonProperty("native_format")
  private String native_format = null;
  /* The base URL for remote web services. */
  @JsonProperty("base_url")
  private String base_url = null;
  /* Additional URL parameters required by the service. */
  @JsonProperty("parameters")
  private String parameters = null;
  /* Additional headers required by the service. */
  @JsonProperty("headers")
  private String headers = null;
  /* Related apps by app to service assignment. */
  @JsonProperty("apps")
  private RelatedApps apps = null;
  /* Related roles by service to role assignment. */
  @JsonProperty("roles")
  private RelatedRoles roles = null;
  /* Date this service was created. */
  @JsonProperty("created_date")
  private String created_date = null;
  /* User Id of who created this service. */
  @JsonProperty("created_by_id")
  private Integer created_by_id = null;
  /* Date this service was last modified. */
  @JsonProperty("last_modified_date")
  private String last_modified_date = null;
  /* User Id of who last modified this service. */
  @JsonProperty("last_modified_by_id")
  private Integer last_modified_by_id = null;
  /* True if this service is a default system service. */
  @JsonProperty("is_system")
  private Boolean is_system = null;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getApi_name() {
    return api_name;
  }
  public void setApi_name(String api_name) {
    this.api_name = api_name;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getIs_active() {
    return is_active;
  }
  public void setIs_active(Boolean is_active) {
    this.is_active = is_active;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public Integer getType_id() {
    return type_id;
  }
  public void setType_id(Integer type_id) {
    this.type_id = type_id;
  }

  public String getStorage_type() {
    return storage_type;
  }
  public void setStorage_type(String storage_type) {
    this.storage_type = storage_type;
  }

  public Integer getStorage_type_id() {
    return storage_type_id;
  }
  public void setStorage_type_id(Integer storage_type_id) {
    this.storage_type_id = storage_type_id;
  }

  public String getCredentials() {
    return credentials;
  }
  public void setCredentials(String credentials) {
    this.credentials = credentials;
  }

  public String getNative_format() {
    return native_format;
  }
  public void setNative_format(String native_format) {
    this.native_format = native_format;
  }

  public String getBase_url() {
    return base_url;
  }
  public void setBase_url(String base_url) {
    this.base_url = base_url;
  }

  public String getParameters() {
    return parameters;
  }
  public void setParameters(String parameters) {
    this.parameters = parameters;
  }

  public String getHeaders() {
    return headers;
  }
  public void setHeaders(String headers) {
    this.headers = headers;
  }

  public RelatedApps getApps() {
    return apps;
  }
  public void setApps(RelatedApps apps) {
    this.apps = apps;
  }

  public RelatedRoles getRoles() {
    return roles;
  }
  public void setRoles(RelatedRoles roles) {
    this.roles = roles;
  }

  public String getCreated_date() {
    return created_date;
  }
  public void setCreated_date(String created_date) {
    this.created_date = created_date;
  }

  public Integer getCreated_by_id() {
    return created_by_id;
  }
  public void setCreated_by_id(Integer created_by_id) {
    this.created_by_id = created_by_id;
  }

  public String getLast_modified_date() {
    return last_modified_date;
  }
  public void setLast_modified_date(String last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  public Integer getLast_modified_by_id() {
    return last_modified_by_id;
  }
  public void setLast_modified_by_id(Integer last_modified_by_id) {
    this.last_modified_by_id = last_modified_by_id;
  }

  public Boolean getIs_system() {
    return is_system;
  }
  public void setIs_system(Boolean is_system) {
    this.is_system = is_system;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceResponse {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  api_name: ").append(api_name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  is_active: ").append(is_active).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  type_id: ").append(type_id).append("\n");
    sb.append("  storage_type: ").append(storage_type).append("\n");
    sb.append("  storage_type_id: ").append(storage_type_id).append("\n");
    sb.append("  credentials: ").append(credentials).append("\n");
    sb.append("  native_format: ").append(native_format).append("\n");
    sb.append("  base_url: ").append(base_url).append("\n");
    sb.append("  parameters: ").append(parameters).append("\n");
    sb.append("  headers: ").append(headers).append("\n");
    sb.append("  apps: ").append(apps).append("\n");
    sb.append("  roles: ").append(roles).append("\n");
    sb.append("  created_date: ").append(created_date).append("\n");
    sb.append("  created_by_id: ").append(created_by_id).append("\n");
    sb.append("  last_modified_date: ").append(last_modified_date).append("\n");
    sb.append("  last_modified_by_id: ").append(last_modified_by_id).append("\n");
    sb.append("  is_system: ").append(is_system).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

