package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.RecordResponse;
import com.dreamfactory.model.Metadata;
public class RecordsResponse {
	//  /* Array of system user records. */
	//  @JsonProperty("record")
	//  private List<RecordResponse> record = new ArrayList<RecordResponse>();
	//  /* Array of metadata returned for GET requests. */
	//  @JsonProperty("meta")
	//  private Metadata meta = null;
	//  public List<RecordResponse> getRecord() {
	//    return record;
	//  }
	//  public void setRecord(List<RecordResponse> record) {
	//    this.record = record;
	//  }

	@JsonProperty("record")
	  private List<RecordResponse> record = new ArrayList<RecordResponse>();
	  /* Available metadata for the response. */
	  @JsonProperty("meta")
	  private Metadata meta = null;
	  public List<RecordResponse> getRecord() {
	    return record;
	  }
	  public void setRecord(List<RecordResponse> record) {
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
		sb.append("class RecordsResponse {\n");
		sb.append("  record: ").append(record).append("\n");
		sb.append("  meta: ").append(meta).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}

