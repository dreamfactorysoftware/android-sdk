package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RelatedApps;
public class AppGroupRequest {
  /* Identifier of this application group. */
  @JsonProperty("id")
  private Integer id = null;
  /* Displayable name of this application group. */
  @JsonProperty("name")
  private String name = null;
  /* Description of this application group. */
  @JsonProperty("description")
  private String description = null;
  /* Related apps by app to group assignment. */
  @JsonProperty("apps")
  private RelatedApps apps = null;
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

  public RelatedApps getApps() {
    return apps;
  }
  public void setApps(RelatedApps apps) {
    this.apps = apps;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppGroupRequest {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  apps: ").append(apps).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

