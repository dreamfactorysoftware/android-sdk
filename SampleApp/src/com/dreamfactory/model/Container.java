package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public class Container {
  /* Identifier/Name for the container. */
  @JsonProperty("name")
  private String name = null;
  /* Same as name for the container, for consistency. */
  @JsonProperty("path")
  private String path = null;
  /* Storage type specific properties. */
  @JsonProperty("_property_")
  private String _property_ = null;
  /* An array of name-value pairs. */
  @JsonProperty("metadata")
  private List<String> metadata = new ArrayList<String>();
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }

  public String get_property_() {
    return _property_;
  }
  public void set_property_(String _property_) {
    this._property_ = _property_;
  }

  public List<String> getMetadata() {
    return metadata;
  }
  public void setMetadata(List<String> metadata) {
    this.metadata = metadata;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Container {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  path: ").append(path).append("\n");
    sb.append("  _property_: ").append(_property_).append("\n");
    sb.append("  metadata: ").append(metadata).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

