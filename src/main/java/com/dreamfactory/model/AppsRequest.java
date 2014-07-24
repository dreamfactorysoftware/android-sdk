package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.AppRequest;
public class AppsRequest {
  /* Array of system application records. */
  @JsonProperty("record")
  private List<AppRequest> record = new ArrayList<AppRequest>();
  /* Array of system application record identifiers, used for batch GET, PUT, PATCH, and DELETE. */
  @JsonProperty("ids")
  private List<Integer> ids = new ArrayList<Integer>();
  public List<AppRequest> getRecord() {
    return record;
  }
  public void setRecord(List<AppRequest> record) {
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
    sb.append("class AppsRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  ids: ").append(ids).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

