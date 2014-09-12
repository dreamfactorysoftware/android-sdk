package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.Metadata;
import com.dreamfactory.model.UserResponse;
public class UsersResponse {
  /* Array of system user records. */
  @JsonProperty("record")
  private List<UserResponse> record = new ArrayList<UserResponse>();
  /* Array of metadata returned for GET requests. */
  @JsonProperty("meta")
  private Metadata meta = null;
  public List<UserResponse> getRecord() {
    return record;
  }
  public void setRecord(List<UserResponse> record) {
    this.record = record;
  }

  public Metadata getMeta() {
    return meta;
  }
  public void setMeta(Metadata meta) {
    this.meta = meta;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsersResponse {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

