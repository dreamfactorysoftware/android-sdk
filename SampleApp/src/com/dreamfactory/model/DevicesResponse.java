package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.DeviceResponse;
public class DevicesResponse {
  /* Array of system device records. */
  @JsonProperty("record")
  private List<DeviceResponse> record = new ArrayList<DeviceResponse>();
  public List<DeviceResponse> getRecord() {
    return record;
  }
  public void setRecord(List<DeviceResponse> record) {
    this.record = record;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class DevicesResponse {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

