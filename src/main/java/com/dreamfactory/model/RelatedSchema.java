package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class RelatedSchema {
  /* Name of the relationship. */
  @JsonProperty("name")
  private String name = null;
  /* Relationship type - belongs_to, has_many, many_many. */
  @JsonProperty("type")
  private String type = null;
  /* The table name that is referenced by the relationship. */
  @JsonProperty("ref_table")
  private String ref_table = null;
  /* The field name that is referenced by the relationship. */
  @JsonProperty("ref_field")
  private String ref_field = null;
  /* The intermediate joining table used for many_many relationships. */
  @JsonProperty("join")
  private String join = null;
  /* The current table field that is used in the relationship. */
  @JsonProperty("field")
  private String field = null;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public String getRef_table() {
    return ref_table;
  }
  public void setRef_table(String ref_table) {
    this.ref_table = ref_table;
  }

  public String getRef_field() {
    return ref_field;
  }
  public void setRef_field(String ref_field) {
    this.ref_field = ref_field;
  }

  public String getJoin() {
    return join;
  }
  public void setJoin(String join) {
    this.join = join;
  }

  public String getField() {
    return field;
  }
  public void setField(String field) {
    this.field = field;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedSchema {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  ref_table: ").append(ref_table).append("\n");
    sb.append("  ref_field: ").append(ref_field).append("\n");
    sb.append("  join: ").append(join).append("\n");
    sb.append("  field: ").append(field).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

