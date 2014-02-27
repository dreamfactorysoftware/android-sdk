package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public class Record {
  /* Array of field name-value pairs. */
  @JsonProperty("_field_")
  private List<String> _field_ = new ArrayList<String>();
  public List<String> get_field_() {
    return _field_;
  }
  public void set_field_(List<String> _field_) {
    this._field_ = _field_;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Record {\n");
    sb.append("  _field_: ").append(_field_).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

