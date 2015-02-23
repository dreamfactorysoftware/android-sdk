package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RecordRequest;
public class GetRecordsRequest {
  /* Array of records. */
  @JsonProperty("record")
  private List<RecordRequest> record = new ArrayList<RecordRequest>();
  /* Array of record identifiers. */
  @JsonProperty("ids")
  private List<Integer> ids = new ArrayList<Integer>();
  /* SQL or native filter to determine records where modifications will be applied. */
  @JsonProperty("filter")
  private String filter = null;
  /* Array of name-value pairs, used for parameter replacement on filters. */
  @JsonProperty("params")
  private List<String> params = new ArrayList<String>();
  public List<RecordRequest> getRecord() {
    return record;
  }
  public void setRecord(List<RecordRequest> record) {
    this.record = record;
  }

  public List<Integer> getIds() {
    return ids;
  }
  public void setIds(List<Integer> ids) {
    this.ids = ids;
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
    sb.append("class GetRecordsRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  ids: ").append(ids).append("\n");
    sb.append("  filter: ").append(filter).append("\n");
    sb.append("  params: ").append(params).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

