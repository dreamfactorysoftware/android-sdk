package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.CustomSetting;
public class CustomSettings {
  @JsonProperty("name")
  private List<CustomSetting> name = new ArrayList<CustomSetting>();
  public List<CustomSetting> getName() {
    return name;
  }
  public void setName(List<CustomSetting> name) {
    this.name = name;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomSettings {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

