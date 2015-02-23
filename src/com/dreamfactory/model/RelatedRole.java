package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class RelatedRole {
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
  /* Date this role was created. */
  @JsonProperty("created_date")
  private String created_date = null;
  /* User Id of who created this role. */
  @JsonProperty("created_by_id")
  private Integer created_by_id = null;
  /* Date this role was last modified. */
  @JsonProperty("last_modified_date")
  private String last_modified_date = null;
  /* User Id of who last modified this role. */
  @JsonProperty("last_modified_by_id")
  private Integer last_modified_by_id = null;
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

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedRole {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  is_active: ").append(is_active).append("\n");
    sb.append("  default_app_id: ").append(default_app_id).append("\n");
    sb.append("  created_date: ").append(created_date).append("\n");
    sb.append("  created_by_id: ").append(created_by_id).append("\n");
    sb.append("  last_modified_date: ").append(last_modified_date).append("\n");
    sb.append("  last_modified_by_id: ").append(last_modified_by_id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

