package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.FieldSchema;
import com.dreamfactory.model.RelatedSchema;
public class TableSchema {
  /* Identifier/Name for the table. */
  @JsonProperty("name")
  private String name = null;
  /* Displayable singular name for the table. */
  @JsonProperty("label")
  private String label = null;
  /* Displayable plural name for the table. */
  @JsonProperty("plural")
  private String plural = null;
  /* Field(s), if any, that represent the primary key of each record. */
  @JsonProperty("primary_key")
  private String primary_key = null;
  /* Field(s), if any, that represent the name of each record. */
  @JsonProperty("name_field")
  private String name_field = null;
  /* An array of available fields in each record. */
  @JsonProperty("field")
  private List<FieldSchema> field = new ArrayList<FieldSchema>();
  /* An array of available relationships to other tables. */
  @JsonProperty("related")
  private List<RelatedSchema> related = new ArrayList<RelatedSchema>();
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }

  public String getPlural() {
    return plural;
  }
  public void setPlural(String plural) {
    this.plural = plural;
  }

  public String getPrimary_key() {
    return primary_key;
  }
  public void setPrimary_key(String primary_key) {
    this.primary_key = primary_key;
  }

  public String getName_field() {
    return name_field;
  }
  public void setName_field(String name_field) {
    this.name_field = name_field;
  }

  public List<FieldSchema> getField() {
    return field;
  }
  public void setField(List<FieldSchema> field) {
    this.field = field;
  }

  public List<RelatedSchema> getRelated() {
    return related;
  }
  public void setRelated(List<RelatedSchema> related) {
    this.related = related;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class TableSchema {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  label: ").append(label).append("\n");
    sb.append("  plural: ").append(plural).append("\n");
    sb.append("  primary_key: ").append(primary_key).append("\n");
    sb.append("  name_field: ").append(name_field).append("\n");
    sb.append("  field: ").append(field).append("\n");
    sb.append("  related: ").append(related).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

