package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.dreamfactory.model.Table;
public class Tables {
  /* Array of tables and their properties. */
  @JsonProperty("table")
  private List<Table> table = new ArrayList<Table>();
  public List<Table> getTable() {
    return table;
  }
  public void setTable(List<Table> table) {
    this.table = table;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tables {\n");
    sb.append("  table: ").append(table).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

