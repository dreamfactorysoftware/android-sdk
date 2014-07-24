package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.Constant;
public class Constants {
  @JsonProperty("type_name")
  private List<Constant> type_name = new ArrayList<Constant>();
  public List<Constant> getType_name() {
    return type_name;
  }
  public void setType_name(List<Constant> type_name) {
    this.type_name = type_name;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Constants {\n");
    sb.append("  type_name: ").append(type_name).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

