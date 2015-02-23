package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RecordRequest;
public class FilterRecordRequest {
  /* A single record, array of fields, used to modify existing records. */
  @JsonProperty("record")
  private RecordRequest record = null;
  /* SQL or native filter to determine records where modifications will be applied. */
  @JsonProperty("filter")
  private String filter = null;
  /* Array of name-value pairs, used for parameter replacement on filters. */
  @JsonProperty("params")
  private List<String> params = new ArrayList<String>();
  public RecordRequest getRecord() {
    return record;
  }
  public void setRecord(RecordRequest record) {
    this.record = record;
  }

  public String getFilter() {
    return filter;
  }
  public void setFilter(String filter) {
    this.filter = filter;
  }

  public List<String> getParams() {
    return params;
  }
  public void setParams(List<String> params) {
    this.params = params;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilterRecordRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  filter: ").append(filter).append("\n");
    sb.append("  params: ").append(params).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

