package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class StoredProcResultSchema {
  /* The name of the returned element where the value is set to the requested type for the returned value, i.e. integer, boolean, string, etc. */
  @JsonProperty("_field_name_")
  private String _field_name_ = null;
  public String get_field_name_() {
    return _field_name_;
  }
  public void set_field_name_(String _field_name_) {
    this._field_name_ = _field_name_;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoredProcResultSchema {\n");
    sb.append("  _field_name_: ").append(_field_name_).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

