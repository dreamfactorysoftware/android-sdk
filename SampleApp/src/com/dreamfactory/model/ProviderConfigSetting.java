package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class ProviderConfigSetting {
  @JsonProperty("key")
  private String key = null;
  @JsonProperty("value")
  private String value = null;
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderConfigSetting {\n");
    sb.append("  key: ").append(key).append("\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

