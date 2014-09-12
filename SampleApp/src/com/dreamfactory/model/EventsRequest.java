package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EventRequest;
public class EventsRequest {
  /* Array of system event records. */
  @JsonProperty("record")
  private List<EventRequest> record = new ArrayList<EventRequest>();
  public List<EventRequest> getRecord() {
    return record;
  }
  public void setRecord(List<EventRequest> record) {
    this.record = record;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventsRequest {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

