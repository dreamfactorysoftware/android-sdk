package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class EmailResponse {
  /* Number of emails successfully sent. */
  @JsonProperty("count")
  private Integer count = null;
  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailResponse {\n");
    sb.append("  count: ").append(count).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

