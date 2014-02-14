package com.wordnik.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.wordnik.client.model.App;
import com.wordnik.client.model.Integer;
public class RoleRequest {
  /* Identifier of this role. */
  @JsonProperty("id")
  private Integer id = null;
  /* Displayable name of this role. */
  @JsonProperty("name")
  private String name = null;
  /* Description of this role. */
  @JsonProperty("description")
  private String description = null;
  /* Is this role active for use. */
  @JsonProperty("is_active")
  private Boolean is_active = null;
  /* Default launched app for this role. */
  @JsonProperty("default_app_id")
  private Integer default_app_id = null;
  /* Related app by default_app_id. */
  @JsonProperty("default_app")
  private App default_app = null;
  /* Related users by User.role_id. */
  @JsonProperty("users")
  private List<String> users = new ArrayList<String>();
  /* Related apps by role assignment. */
  @JsonProperty("apps")
  private List<String> apps = new ArrayList<String>();
  /* Related services by role assignment. */
  @JsonProperty("services")
  private List<String> services = new ArrayList<String>();
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

  public Integer getDefault_app_id() {
    return default_app_id;
  }
  public void setDefault_app_id(Integer default_app_id) {
    this.default_app_id = default_app_id;
  }

  public App getDefault_app() {
    return default_app;
  }
  public void setDefault_app(App default_app) {
    this.default_app = default_app;
  }

  public List<String> getUsers() {
    return users;
  }
  public void setUsers(List<String> users) {
    this.users = users;
  }

  public List<String> getApps() {
    return apps;
  }
  public void setApps(List<String> apps) {
    this.apps = apps;
  }

  public List<String> getServices() {
    return services;
  }
  public void setServices(List<String> services) {
    this.services = services;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleRequest {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  is_active: ").append(is_active).append("\n");
    sb.append("  default_app_id: ").append(default_app_id).append("\n");
    sb.append("  default_app: ").append(default_app).append("\n");
    sb.append("  users: ").append(users).append("\n");
    sb.append("  apps: ").append(apps).append("\n");
    sb.append("  services: ").append(services).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

