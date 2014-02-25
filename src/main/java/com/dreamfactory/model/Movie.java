package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
  @JsonProperty("name")
  private String name = null;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movie {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

