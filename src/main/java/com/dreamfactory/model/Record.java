package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Record {
  /* Array of DB table specific field name-value pairs. */
  @JsonProperty("_field_")
  private String _field_ = null;
  public String get_field_() {
    return _field_;
  }
  public void set_field_(String _field_) {
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

