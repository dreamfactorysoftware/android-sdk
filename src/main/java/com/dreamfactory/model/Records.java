package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.dreamfactory.model.Metadata;
public class Records {
  /* Array of records of the given resource. */
  @JsonProperty("record")
  private List<String> record = new ArrayList<String>();
  /* Available metadata for the response. */
  @JsonProperty("meta")
  private Metadata meta = null;
  public List<String> getRecord() {
    return record;
  }
  public void setRecord(List<String> record) {
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
    sb.append("class Records {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

