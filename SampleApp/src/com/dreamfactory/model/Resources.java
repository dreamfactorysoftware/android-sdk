package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.Resource;
public class Resources {
  /* Array of resources available by this service. */
  @JsonProperty("resource")
  private List<Resource> resource = new ArrayList<Resource>();
  public List<Resource> getResource() {
    return resource;
  }
  public void setResource(List<Resource> resource) {
    this.resource = resource;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resources {\n");
    sb.append("  resource: ").append(resource).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

