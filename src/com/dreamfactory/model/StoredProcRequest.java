package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.StoredProcResultSchema;
import com.dreamfactory.model.StoredProcParam;
public class StoredProcRequest {
  /* Optional array of input and output parameters. */
  @JsonProperty("params")
  private List<StoredProcParam> params = new ArrayList<StoredProcParam>();
  /* Optional name to type pairs to be applied to returned data. */
  @JsonProperty("schema")
  private StoredProcResultSchema schema = null;
  /* Add this wrapper around the expected data set before returning, same as URL parameter. */
  @JsonProperty("wrapper")
  private String wrapper = null;
  public List<StoredProcParam> getParams() {
    return params;
  }
  public void setParams(List<StoredProcParam> params) {
    this.params = params;
  }

  public StoredProcResultSchema getSchema() {
    return schema;
  }
  public void setSchema(StoredProcResultSchema schema) {
    this.schema = schema;
  }

  public String getWrapper() {
    return wrapper;
  }
  public void setWrapper(String wrapper) {
    this.wrapper = wrapper;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoredProcRequest {\n");
    sb.append("  params: ").append(params).append("\n");
    sb.append("  schema: ").append(schema).append("\n");
    sb.append("  wrapper: ").append(wrapper).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

