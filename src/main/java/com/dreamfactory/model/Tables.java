package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.TableSchema;
public class Tables {
  /* An array of table definitions. */
  @JsonProperty("field")
  private List<TableSchema> field = new ArrayList<TableSchema>();
  public List<TableSchema> getField() {
    return field;
  }
  public void setField(List<TableSchema> field) {
    this.field = field;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tables {\n");
    sb.append("  field: ").append(field).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

