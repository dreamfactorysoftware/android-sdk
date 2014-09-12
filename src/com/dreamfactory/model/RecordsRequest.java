package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RecordRequest;
public class RecordsRequest {
  /* Array of records. */
  @JsonProperty("record")
  private List<RecordRequest> record = new ArrayList<RecordRequest>();
  public List<RecordRequest> getRecord() {
    return record;
  }
  public void setRecord(List<RecordRequest> record) {
    this.record = record;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecordsRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

