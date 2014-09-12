package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class EventResponse {
  /* The name of this event */
  @JsonProperty("event_name")
  private String event_name = null;
  /* An array of listeners attached to this event. */
  @JsonProperty("listeners")
  private List<String> listeners = new ArrayList<String>();
  public String getEvent_name() {
    return event_name;
  }
  public void setEvent_name(String event_name) {
    this.event_name = event_name;
  }

  public List<String> getListeners() {
    return listeners;
  }
  public void setListeners(List<String> listeners) {
    this.listeners = listeners;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventResponse {\n");
    sb.append("  event_name: ").append(event_name).append("\n");
    sb.append("  listeners: ").append(listeners).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

