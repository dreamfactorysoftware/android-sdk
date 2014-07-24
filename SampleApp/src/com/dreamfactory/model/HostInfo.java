package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class HostInfo {
  /* URL, server name, or * to define the CORS host. */
  @JsonProperty("host")
  private String host = null;
  /* Allow this host's configuration to be used by CORS. */
  @JsonProperty("is_enabled")
  private Boolean is_enabled = null;
  /* Allowed HTTP verbs for this host. */
  @JsonProperty("verbs")
  private List<String> verbs = new ArrayList<String>();
  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }

  public Boolean getIs_enabled() {
    return is_enabled;
  }
  public void setIs_enabled(Boolean is_enabled) {
    this.is_enabled = is_enabled;
  }

  public List<String> getVerbs() {
    return verbs;
  }
  public void setVerbs(List<String> verbs) {
    this.verbs = verbs;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class HostInfo {\n");
    sb.append("  host: ").append(host).append("\n");
    sb.append("  is_enabled: ").append(is_enabled).append("\n");
    sb.append("  verbs: ").append(verbs).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

