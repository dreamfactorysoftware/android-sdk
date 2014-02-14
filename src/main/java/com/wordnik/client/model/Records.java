package com.wordnik.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.wordnik.client.model.Metadata;
import com.wordnik.client.model.Record;
public class Records {
  /* Array of records of the given resource. */
  @JsonProperty("record")
  private List<Record> record = new ArrayList<Record>();
  /* Available metadata for the response. */
  @JsonProperty("meta")
  private Metadata meta = null;
  public List<Record> getRecord() {
    return record;
  }
  public void setRecord(List<Record> record) {
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
    sb.append("class Records {\n");
    sb.append("  record: ").append(record).append("\n");
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

