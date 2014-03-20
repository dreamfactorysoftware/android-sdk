package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.dreamfactory.model.CustomSetting;
public class CustomSettings {
  @JsonProperty("type_name")
  private List<CustomSetting> type_name = new ArrayList<CustomSetting>();
  public List<CustomSetting> getType_name() {
    return type_name;
  }
  public void setType_name(List<CustomSetting> type_name) {
    this.type_name = type_name;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomSettings {\n");
    sb.append("  type_name: ").append(type_name).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

