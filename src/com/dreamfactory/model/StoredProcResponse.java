package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class StoredProcResponse {
  /* Array of returned data. */
  @JsonProperty("_wrapper_if_supplied_")
  private List<String> _wrapper_if_supplied_ = new ArrayList<String>();
  /* Name and value of any given output parameter. */
  @JsonProperty("_out_param_name_")
  private String _out_param_name_ = null;
  public List<String> get_wrapper_if_supplied_() {
    return _wrapper_if_supplied_;
  }
  public void set_wrapper_if_supplied_(List<String> _wrapper_if_supplied_) {
    this._wrapper_if_supplied_ = _wrapper_if_supplied_;
  }

  public String get_out_param_name_() {
    return _out_param_name_;
  }
  public void set_out_param_name_(String _out_param_name_) {
    this._out_param_name_ = _out_param_name_;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoredProcResponse {\n");
    sb.append("  _wrapper_if_supplied_: ").append(_wrapper_if_supplied_).append("\n");
    sb.append("  _out_param_name_: ").append(_out_param_name_).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

