package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EventPaths;
public class EventCacheResponse {
  /* The owner API of this event */
  @JsonProperty("name")
  private String name = null;
  /* An array of paths which trigger this event */
  @JsonProperty("paths")
  private List<EventPaths> paths = new ArrayList<EventPaths>();
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public List<EventPaths> getPaths() {
    return paths;
  }
  public void setPaths(List<EventPaths> paths) {
    this.paths = paths;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventCacheResponse {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  paths: ").append(paths).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

