package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.FileResponse;
public class FolderResponse {
  /* Identifier/Name for the folder, localized to requested resource. */
  @JsonProperty("name")
  private String name = null;
  /* Full path of the folder, from the service including container. */
  @JsonProperty("path")
  private String path = null;
  /* An array of name-value pairs. */
  @JsonProperty("metadata")
  private List<String> metadata = new ArrayList<String>();
  /* A GMT date timestamp of when the file was last modified. */
  @JsonProperty("last_modified")
  private String last_modified = null;
  /* An array of contained sub-folders. */
  @JsonProperty("folder")
  private List<FolderResponse> folder = new ArrayList<FolderResponse>();
  /* An array of contained files. */
  @JsonProperty("file")
  private List<FileResponse> file = new ArrayList<FileResponse>();
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

  public List<String> getMetadata() {
    return metadata;
  }
  public void setMetadata(List<String> metadata) {
    this.metadata = metadata;
  }

  public String getLast_modified() {
    return last_modified;
  }
  public void setLast_modified(String last_modified) {
    this.last_modified = last_modified;
  }

  public List<FolderResponse> getFolder() {
    return folder;
  }
  public void setFolder(List<FolderResponse> folder) {
    this.folder = folder;
  }

  public List<FileResponse> getFile() {
    return file;
  }
  public void setFile(List<FileResponse> file) {
    this.file = file;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FolderResponse {\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  path: ").append(path).append("\n");
    sb.append("  metadata: ").append(metadata).append("\n");
    sb.append("  last_modified: ").append(last_modified).append("\n");
    sb.append("  folder: ").append(folder).append("\n");
    sb.append("  file: ").append(file).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

