package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class EventVerbs {
  /* The verb for this path */
  @JsonProperty("type")
  private String type = null;
  /* An array of event names triggered by this path/verb combo */
  @JsonProperty("event")
  private List<String> event = new ArrayList<String>();
  /* An array of scripts registered to this event */
  @JsonProperty("scripts")
  private List<String> scripts = new ArrayList<String>();
  /* An array of listeners registered to this event */
  @JsonProperty("listeners")
  private List<String> listeners = new ArrayList<String>();
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public List<String> getEvent() {
    return event;
  }
  public void setEvent(List<String> event) {
    this.event = event;
  }

  public List<String> getScripts() {
    return scripts;
  }
  public void setScripts(List<String> scripts) {
    this.scripts = scripts;
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
    sb.append("class EventVerbs {\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  event: ").append(event).append("\n");
    sb.append("  scripts: ").append(scripts).append("\n");
    sb.append("  listeners: ").append(listeners).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

