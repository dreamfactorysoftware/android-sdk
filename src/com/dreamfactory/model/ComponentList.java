package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class ComponentList {
  /* Array of accessible components available by this service. */
  @JsonProperty("resource")
  private List<String> resource = new ArrayList<String>();
  public List<String> getResource() {
    return resource;
  }
  public void setResource(List<String> resource) {
    this.resource = resource;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComponentList {\n");
    sb.append("  resource: ").append(resource).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

