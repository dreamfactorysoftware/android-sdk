package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class ReleaseSection {
  @JsonProperty("id")
  private String id = null;
  @JsonProperty("release")
  private String release = null;
  @JsonProperty("codename")
  private String codename = null;
  @JsonProperty("description")
  private String description = null;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getRelease() {
    return release;
  }
  public void setRelease(String release) {
    this.release = release;
  }

  public String getCodename() {
    return codename;
  }
  public void setCodename(String codename) {
    this.codename = codename;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReleaseSection {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  release: ").append(release).append("\n");
    sb.append("  codename: ").append(codename).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

