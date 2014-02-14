package com.wordnik.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.wordnik.client.model.Role;
public class RolesRequest {
  /* Array of system role records. */
  @JsonProperty("record")
  private List<Role> record = new ArrayList<Role>();
  /* Array of system record identifiers, used for batch GET, PUT, PATCH, and DELETE. */
  @JsonProperty("ids")
  private List<Integer> ids = new ArrayList<Integer>();
  public List<Role> getRecord() {
    return record;
  }
  public void setRecord(List<Role> record) {
    this.record = record;
  }

  public List<Integer> getIds() {
    return ids;
  }
  public void setIds(List<Integer> ids) {
    this.ids = ids;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RolesRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  ids: ").append(ids).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

