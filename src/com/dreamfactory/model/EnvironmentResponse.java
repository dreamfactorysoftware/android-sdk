package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.ReleaseSection;
import com.dreamfactory.model.PlatformSection;
import com.dreamfactory.model.PhpInfoSection;
import com.dreamfactory.model.ServerSection;
public class EnvironmentResponse {
  @JsonProperty("server")
  private ServerSection server = null;
  @JsonProperty("release")
  private ReleaseSection release = null;
  @JsonProperty("platform")
  private PlatformSection platform = null;
  @JsonProperty("php_info")
  private List<PhpInfoSection> php_info = new ArrayList<PhpInfoSection>();
  public ServerSection getServer() {
    return server;
  }
  public void setServer(ServerSection server) {
    this.server = server;
  }

  public ReleaseSection getRelease() {
    return release;
  }
  public void setRelease(ReleaseSection release) {
    this.release = release;
  }

  public PlatformSection getPlatform() {
    return platform;
  }
  public void setPlatform(PlatformSection platform) {
    this.platform = platform;
  }

  public List<PhpInfoSection> getPhp_info() {
    return php_info;
  }
  public void setPhp_info(List<PhpInfoSection> php_info) {
    this.php_info = php_info;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnvironmentResponse {\n");
    sb.append("  server: ").append(server).append("\n");
    sb.append("  release: ").append(release).append("\n");
    sb.append("  platform: ").append(platform).append("\n");
    sb.append("  php_info: ").append(php_info).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

