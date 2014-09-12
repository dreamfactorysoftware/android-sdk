package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class ServerSection {
  @JsonProperty("server_os")
  private String server_os = null;
  @JsonProperty("uname")
  private String uname = null;
  public String getServer_os() {
    return server_os;
  }
  public void setServer_os(String server_os) {
    this.server_os = server_os;
  }

  public String getUname() {
    return uname;
  }
  public void setUname(String uname) {
    this.uname = uname;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerSection {\n");
    sb.append("  server_os: ").append(server_os).append("\n");
    sb.append("  uname: ").append(uname).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

