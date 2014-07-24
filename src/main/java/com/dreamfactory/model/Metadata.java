package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class Metadata {
  /* Array of table schema. */
  @JsonProperty("schema")
  private List<String> schema = new ArrayList<String>();
  /* Record count returned for GET requests. */
  @JsonProperty("count")
  private Integer count = null;
  public List<String> getSchema() {
    return schema;
  }
  public void setSchema(List<String> schema) {
    this.schema = schema;
  }

  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    sb.append("  schema: ").append(schema).append("\n");
    sb.append("  count: ").append(count).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

