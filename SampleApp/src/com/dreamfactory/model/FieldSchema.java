package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class FieldSchema {
  /* The API name of the field. */
  @JsonProperty("name")
  private String name = null;
  /* The displayable label for the field. */
  @JsonProperty("label")
  private String label = null;
  /* The DSP abstract data type for this field. */
  @JsonProperty("type")
  private String type = null;
  /* The native database type used for this field. */
  @JsonProperty("db_type")
  private String db_type = null;
  /* The maximum length allowed (in characters for string, displayed for numbers). */
  @JsonProperty("length")
  private Integer length = null;
  /* Total number of places for numbers. */
  @JsonProperty("precision")
  private Integer precision = null;
  /* Number of decimal places allowed for numbers. */
  @JsonProperty("scale")
  private Integer scale = null;
  /* Default value for this field. */
  @JsonProperty("default_value")
  private String default_value = null;
  /* Is a value required for record creation. */
  @JsonProperty("required")
  private Boolean required = null;
  /* Is null allowed as a value. */
  @JsonProperty("allow_null")
  private Boolean allow_null = null;
  /* Is the length fixed (not variable). */
  @JsonProperty("fixed_length")
  private Boolean fixed_length = null;
  /* Does the data type support multibyte characters. */
  @JsonProperty("supports_multibyte")
  private Boolean supports_multibyte = null;
  /* Does the integer field value increment upon new record creation. */
  @JsonProperty("auto_increment")
  private Boolean auto_increment = null;
  /* Is this field used as/part of the primary key. */
  @JsonProperty("is_primary_key")
  private Boolean is_primary_key = null;
  /* Is this field used as a foreign key. */
  @JsonProperty("is_foreign_key")
  private Boolean is_foreign_key = null;
  /* For foreign keys, the referenced table name. */
  @JsonProperty("ref_table")
  private String ref_table = null;
  /* For foreign keys, the referenced table field name. */
  @JsonProperty("ref_fields")
  private String ref_fields = null;
  /* validations to be performed on this field. */
  @JsonProperty("validation")
  private List<String> validation = new ArrayList<String>();
  /* Selectable string values for client menus and picklist validation. */
  @JsonProperty("value")
  private List<String> value = new ArrayList<String>();
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

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public String getDb_type() {
    return db_type;
  }
  public void setDb_type(String db_type) {
    this.db_type = db_type;
  }

  public Integer getLength() {
    return length;
  }
  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getPrecision() {
    return precision;
  }
  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public Integer getScale() {
    return scale;
  }
  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public String getDefault_value() {
    return default_value;
  }
  public void setDefault_value(String default_value) {
    this.default_value = default_value;
  }

  public Boolean getRequired() {
    return required;
  }
  public void setRequired(Boolean required) {
    this.required = required;
  }

  public Boolean getAllow_null() {
    return allow_null;
  }
  public void setAllow_null(Boolean allow_null) {
    this.allow_null = allow_null;
  }

  public Boolean getFixed_length() {
    return fixed_length;
  }
  public void setFixed_length(Boolean fixed_length) {
    this.fixed_length = fixed_length;
  }

  public Boolean getSupports_multibyte() {
    return supports_multibyte;
  }
  public void setSupports_multibyte(Boolean supports_multibyte) {
    this.supports_multibyte = supports_multibyte;
  }

  public Boolean getAuto_increment() {
    return auto_increment;
  }
  public void setAuto_increment(Boolean auto_increment) {
    this.auto_increment = auto_increment;
  }

  public Boolean getIs_primary_key() {
    return is_primary_key;
  }
  public void setIs_primary_key(Boolean is_primary_key) {
    this.is_primary_key = is_primary_key;
  }

  public Boolean getIs_foreign_key() {
    return is_foreign_key;
  }
  public void setIs_foreign_key(Boolean is_foreign_key) {
    this.is_foreign_key = is_foreign_key;
  }

  public String getRef_table() {
    return ref_table;
  }
  public void setRef_table(String ref_table) {
    this.ref_table = ref_table;
  }

  public String getRef_fields() {
    return ref_fields;
  }
  public void setRef_fields(String ref_fields) {
    this.ref_fields = ref_fields;
  }

  public List<String> getValidation() {
    return validation;
  }
  public void setValidation(List<String> validation) {
    this.validation = validation;
  }

  public List<String> getValue() {
    return value;
  }
  public void setValue(List<String> value) {
    this.value = value;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldSchema {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  label: ").append(label).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  db_type: ").append(db_type).append("\n");
    sb.append("  length: ").append(length).append("\n");
    sb.append("  precision: ").append(precision).append("\n");
    sb.append("  scale: ").append(scale).append("\n");
    sb.append("  default_value: ").append(default_value).append("\n");
    sb.append("  required: ").append(required).append("\n");
    sb.append("  allow_null: ").append(allow_null).append("\n");
    sb.append("  fixed_length: ").append(fixed_length).append("\n");
    sb.append("  supports_multibyte: ").append(supports_multibyte).append("\n");
    sb.append("  auto_increment: ").append(auto_increment).append("\n");
    sb.append("  is_primary_key: ").append(is_primary_key).append("\n");
    sb.append("  is_foreign_key: ").append(is_foreign_key).append("\n");
    sb.append("  ref_table: ").append(ref_table).append("\n");
    sb.append("  ref_fields: ").append(ref_fields).append("\n");
    sb.append("  validation: ").append(validation).append("\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

