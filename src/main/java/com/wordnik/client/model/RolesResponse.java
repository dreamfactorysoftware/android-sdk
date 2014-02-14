package com.wordnik.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.wordnik.client.model.Role;
import com.wordnik.client.model.Metadata;
public class RolesResponse {
  /* Array of system role records. */
  @JsonProperty("record")
  private List<Role> record = new ArrayList<Role>();
  /* Array of metadata returned for GET requests. */
  @JsonProperty("meta")
  private Metadata meta = null;
  public List<Role> getRecord() {
    return record;
  }
  public void setRecord(List<Role> record) {
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
    sb.append("class RolesResponse {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

