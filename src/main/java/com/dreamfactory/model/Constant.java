package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class Constant {
  @JsonProperty("name")
  private List<String> name = new ArrayList<String>();
  public List<String> getName() {
    return name;
  }
  public void setName(List<String> name) {
    this.name = name;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Constant {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

