package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.ServiceRequest;
public class ServicesRequest {
  /* Array of system service records. */
  @JsonProperty("record")
  private List<ServiceRequest> record = new ArrayList<ServiceRequest>();
  /* Array of system record identifiers, used for batch GET, PUT, PATCH, and DELETE. */
  @JsonProperty("ids")
  private List<Integer> ids = new ArrayList<Integer>();
  public List<ServiceRequest> getRecord() {
    return record;
  }
  public void setRecord(List<ServiceRequest> record) {
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
    sb.append("class ServicesRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  ids: ").append(ids).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

