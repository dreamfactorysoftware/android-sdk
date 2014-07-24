package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class StoredProcParam {
  /* Name of the parameter, required for OUT and INOUT types, must be the same as the stored procedure's parameter name. */
  @JsonProperty("name")
  private String name = null;
  /* Parameter type of IN, OUT, or INOUT, defaults to IN. */
  @JsonProperty("param_type")
  private String param_type = null;
  /* Value of the parameter, used for the IN and INOUT types, defaults to NULL. */
  @JsonProperty("value")
  private String value = null;
  /* For INOUT and OUT parameters, the requested type for the returned value, i.e. integer, boolean, string, etc. Defaults to value type for INOUT and string for OUT. */
  @JsonProperty("type")
  private String type = null;
  /* For INOUT and OUT parameters, the requested length for the returned value. May be required by some database drivers. */
  @JsonProperty("length")
  private Integer length = null;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getParam_type() {
    return param_type;
  }
  public void setParam_type(String param_type) {
    this.param_type = param_type;
  }

  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public Integer getLength() {
    return length;
  }
  public void setLength(Integer length) {
    this.length = length;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoredProcParam {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  param_type: ").append(param_type).append("\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  length: ").append(length).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

