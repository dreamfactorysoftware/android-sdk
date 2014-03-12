package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Record {
  /* Auto-incrementing identifying field. */
  @JsonProperty("id")
  private Integer id = null;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Record {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

