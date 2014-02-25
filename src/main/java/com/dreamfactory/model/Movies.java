package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.dreamfactory.model.Movie;
public class Movies {
  @JsonProperty("resource")
  private List<Movie> resource = new ArrayList<Movie>();
  public List<Movie> getResource() {
    return resource;
  }
  public void setResource(List<Movie> resource) {
    this.resource = resource;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movies {\n");
    sb.append("  resource: ").append(resource).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

