package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.ProviderConfigSetting;
public class ProviderConfigSettings {
  /* Array of provider configuration settings */
  @JsonProperty("settings")
  private List<ProviderConfigSetting> settings = new ArrayList<ProviderConfigSetting>();
  public List<ProviderConfigSetting> getSettings() {
    return settings;
  }
  public void setSettings(List<ProviderConfigSetting> settings) {
    this.settings = settings;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderConfigSettings {\n");
    sb.append("  settings: ").append(settings).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

