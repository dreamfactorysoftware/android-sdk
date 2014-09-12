package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EventVerbs;
public class EventPaths {
  /* The full path to which triggers this event */
  @JsonProperty("path")
  private String path = null;
  /* An array of path/verb combinations which contain events */
  @JsonProperty("verbs")
  private List<EventVerbs> verbs = new ArrayList<EventVerbs>();
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }

  public List<EventVerbs> getVerbs() {
    return verbs;
  }
  public void setVerbs(List<EventVerbs> verbs) {
    this.verbs = verbs;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventPaths {\n");
    sb.append("  path: ").append(path).append("\n");
    sb.append("  verbs: ").append(verbs).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

