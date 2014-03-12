package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.dreamfactory.model.DeviceResponse;
import com.dreamfactory.model.Metadata;
public class DevicesResponse {
  /* Array of system device records. */
  @JsonProperty("record")
  private List<DeviceResponse> record = new ArrayList<DeviceResponse>();
  /* Array of metadata returned for GET requests. */
  @JsonProperty("meta")
  private Metadata meta = null;
  public List<DeviceResponse> getRecord() {
    return record;
  }
  public void setRecord(List<DeviceResponse> record) {
    this.record = record;
  }

  public Metadata getMeta() {
    return meta;
  }
  public void setMeta(Metadata meta) {
    this.meta = meta;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DevicesResponse {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

