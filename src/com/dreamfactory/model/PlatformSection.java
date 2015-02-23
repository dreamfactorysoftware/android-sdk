package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class PlatformSection {
  @JsonProperty("is_hosted")
  private Boolean is_hosted = null;
  @JsonProperty("is_private")
  private Boolean is_private = null;
  @JsonProperty("dsp_version_current")
  private String dsp_version_current = null;
  @JsonProperty("dsp_version_latest")
  private String dsp_version_latest = null;
  @JsonProperty("upgrade_available")
  private Boolean upgrade_available = null;
  public Boolean getIs_hosted() {
    return is_hosted;
  }
  public void setIs_hosted(Boolean is_hosted) {
    this.is_hosted = is_hosted;
  }

  public Boolean getIs_private() {
    return is_private;
  }
  public void setIs_private(Boolean is_private) {
    this.is_private = is_private;
  }

  public String getDsp_version_current() {
    return dsp_version_current;
  }
  public void setDsp_version_current(String dsp_version_current) {
    this.dsp_version_current = dsp_version_current;
  }

  public String getDsp_version_latest() {
    return dsp_version_latest;
  }
  public void setDsp_version_latest(String dsp_version_latest) {
    this.dsp_version_latest = dsp_version_latest;
  }

  public Boolean getUpgrade_available() {
    return upgrade_available;
  }
  public void setUpgrade_available(Boolean upgrade_available) {
    this.upgrade_available = upgrade_available;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlatformSection {\n");
    sb.append("  is_hosted: ").append(is_hosted).append("\n");
    sb.append("  is_private: ").append(is_private).append("\n");
    sb.append("  dsp_version_current: ").append(dsp_version_current).append("\n");
    sb.append("  dsp_version_latest: ").append(dsp_version_latest).append("\n");
    sb.append("  upgrade_available: ").append(upgrade_available).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

