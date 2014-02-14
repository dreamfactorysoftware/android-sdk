package com.wordnik.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table {
  /* Name of the table. */
  @JsonProperty("name")
  private String name = null;
  /* DB type specific property name-value pairs. */
  @JsonProperty("_property_")
  private String _property_ = null;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String get_property_() {
    return _property_;
  }
  public void set_property_(String _property_) {
    this._property_ = _property_;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Table {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  _property_: ").append(_property_).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

