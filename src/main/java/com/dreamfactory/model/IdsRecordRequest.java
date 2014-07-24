package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RecordRequest;
public class IdsRecordRequest {
  /* A single record, array of fields, used to modify existing records. */
  @JsonProperty("record")
  private RecordRequest record = null;
  /* Array of record identifiers. */
  @JsonProperty("ids")
  private List<Integer> ids = new ArrayList<Integer>();
  public RecordRequest getRecord() {
    return record;
  }
  public void setRecord(RecordRequest record) {
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
    sb.append("class IdsRecordRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  ids: ").append(ids).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

