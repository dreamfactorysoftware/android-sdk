package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class FileResponse {
	@JsonProperty("content")
	private byte[] content = null;
	/* Identifier/Name for the file, localized to requested resource. */
	@JsonProperty("name")
	private String name = null;
	/* Full path of the file, from the service including container. */
	@JsonProperty("path")
	private String path = null;
	/* The media type of the content of the file. */
	@JsonProperty("content_type")
	private String content_type = null;
	/* An array of name-value pairs. */
	@JsonProperty("metadata")
	private List<String> metadata = new ArrayList<String>();
	/* Size of the file in bytes. */
	@JsonProperty("content_length")
	private String content_length = null;
	/* A GMT date timestamp of when the file was last modified. */
	@JsonProperty("last_modified")
	private String last_modified = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getContent_type() {
		return content_type;
	}

	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public List<String> getMetadata() {
		return metadata;
	}
	public void setMetadata(List<String> metadata) {
		this.metadata = metadata;
	}

	public String getContent_length() {
		return content_length;
	}
	public void setContent_length(String content_length) {
		this.content_length = content_length;
	}

	public String getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(String last_modified) {
		this.last_modified = last_modified;
	}

	@Override
	public String toString()  {
		StringBuilder sb = new StringBuilder();
		sb.append("class FileResponse {\n");
		sb.append("  name: ").append(name).append("\n");
		sb.append("  path: ").append(path).append("\n");
		sb.append("  content_type: ").append(content_type).append("\n");
		sb.append("  metadata: ").append(metadata).append("\n");
		sb.append("  content_length: ").append(content_length).append("\n");
		sb.append("  last_modified: ").append(last_modified).append("\n");
		sb.append("}\n");
		return sb.toString();
	}
}

