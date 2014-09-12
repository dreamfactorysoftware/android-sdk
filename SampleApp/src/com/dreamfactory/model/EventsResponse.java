package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EventResponse;
public class EventsResponse {
  /* Array of event records. */
  @JsonProperty("record")
  private List<EventResponse> record = new ArrayList<EventResponse>();
  public List<EventResponse> getRecord() {
    return record;
  }
  public void setRecord(List<EventResponse> record) {
    this.record = record;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventsResponse {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

