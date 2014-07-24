package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class FilterRequest {
  /* SQL or native filter to determine records where modifications will be applied. */
  @JsonProperty("filter")
  private String filter = null;
  /* Array of name-value pairs, used for parameter replacement on filters. */
  @JsonProperty("params")
  private List<String> params = new ArrayList<String>();
  public String getFilter() {
    return filter;
  }
  public void setFilter(String filter) {
    this.filter = filter;
  }

  public List<String> getParams() {
    return params;
  }
  public void setParams(List<String> params) {
    this.params = params;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FilterRequest {\n");
    sb.append("  filter: ").append(filter).append("\n");
    sb.append("  params: ").append(params).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

