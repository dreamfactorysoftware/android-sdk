package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class RecordRequest {
	/* Array of field name-value pairs. */
	@JsonProperty("_field_")
	private List<String> _field_ = new ArrayList<String>();

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("complete")
	private boolean complete;

	public List<String> get_field_() {
		return _field_;
	}
	public void set_field_(List<String> _field_) {
		this._field_ = _field_;
	}

	public void setId(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setComplete(boolean complete){
		this.complete = complete;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public boolean iscomplete(){
		return complete;
	}

	@Override
	public String toString()  {
		StringBuilder sb = new StringBuilder();
		sb.append("class RecordRequest {\n");
		sb.append("  _field_: ").append(_field_).append("\n");
		sb.append("  name: ").append(name).append("\n");
		sb.append("  id: ").append(id).append("\n");
		sb.append("  complete: ").append(complete).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}
