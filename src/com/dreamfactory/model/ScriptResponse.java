package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

//import com.dreamfactory.model.Integer;
public class ScriptResponse {
  /* The script ID */
  @JsonProperty("script_id")
  private String script_id = null;
  /* The body of the script */
  @JsonProperty("script_body")
  private String script_body = null;
  /* The script file name */
  @JsonProperty("script")
  private String script = null;
  /* True if this is a user script */
  @JsonProperty("is_user_script")
  private Boolean is_user_script = null;
  /* The scripting language. Only "js" is supported at this time */
  @JsonProperty("language")
  private String language = null;
  /* The script file name */
  @JsonProperty("file_name")
  private String file_name = null;
  /* The path where the script file lives */
  @JsonProperty("file_path")
  private String file_path = null;
  /* The last modified time of the file in UNIX time. */
  @JsonProperty("file_mtime")
  private Integer file_mtime = null;
  /* The name of the event this script is fired on or FALSE if none" */
  @JsonProperty("event_name")
  private String event_name = null;
  /* An array of name-value pairs. */
  @JsonProperty("metadata")
  private List<String> metadata = new ArrayList<String>();
  /* The creation date and time of the record */
  @JsonProperty("created_date")
  private String created_date = null;
  /* The ID of the user that created this record */
  @JsonProperty("created_by_id")
  private Long created_by_id = null;
  /* The date and time of this record's last modification */
  @JsonProperty("last_modified_date")
  private String last_modified_date = null;
  /* The ID of the user that last modified this record */
  @JsonProperty("last_modified_by_id")
  private Long last_modified_by_id = null;
  public String getScript_id() {
    return script_id;
  }
  public void setScript_id(String script_id) {
    this.script_id = script_id;
  }

  public String getScript_body() {
    return script_body;
  }
  public void setScript_body(String script_body) {
    this.script_body = script_body;
  }

  public String getScript() {
    return script;
  }
  public void setScript(String script) {
    this.script = script;
  }

  public Boolean getIs_user_script() {
    return is_user_script;
  }
  public void setIs_user_script(Boolean is_user_script) {
    this.is_user_script = is_user_script;
  }

  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }

  public String getFile_name() {
    return file_name;
  }
  public void setFile_name(String file_name) {
    this.file_name = file_name;
  }

  public String getFile_path() {
    return file_path;
  }
  public void setFile_path(String file_path) {
    this.file_path = file_path;
  }

  public Integer getFile_mtime() {
    return file_mtime;
  }
  public void setFile_mtime(Integer file_mtime) {
    this.file_mtime = file_mtime;
  }

  public String getEvent_name() {
    return event_name;
  }
  public void setEvent_name(String event_name) {
    this.event_name = event_name;
  }

  public List<String> getMetadata() {
    return metadata;
  }
  public void setMetadata(List<String> metadata) {
    this.metadata = metadata;
  }

  public String getCreated_date() {
    return created_date;
  }
  public void setCreated_date(String created_date) {
    this.created_date = created_date;
  }

  public Long getCreated_by_id() {
    return created_by_id;
  }
  public void setCreated_by_id(Long created_by_id) {
    this.created_by_id = created_by_id;
  }

  public String getLast_modified_date() {
    return last_modified_date;
  }
  public void setLast_modified_date(String last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  public Long getLast_modified_by_id() {
    return last_modified_by_id;
  }
  public void setLast_modified_by_id(Long last_modified_by_id) {
    this.last_modified_by_id = last_modified_by_id;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScriptResponse {\n");
    sb.append("  script_id: ").append(script_id).append("\n");
    sb.append("  script_body: ").append(script_body).append("\n");
    sb.append("  script: ").append(script).append("\n");
    sb.append("  is_user_script: ").append(is_user_script).append("\n");
    sb.append("  language: ").append(language).append("\n");
    sb.append("  file_name: ").append(file_name).append("\n");
    sb.append("  file_path: ").append(file_path).append("\n");
    sb.append("  file_mtime: ").append(file_mtime).append("\n");
    sb.append("  event_name: ").append(event_name).append("\n");
    sb.append("  metadata: ").append(metadata).append("\n");
    sb.append("  created_date: ").append(created_date).append("\n");
    sb.append("  created_by_id: ").append(created_by_id).append("\n");
    sb.append("  last_modified_date: ").append(last_modified_date).append("\n");
    sb.append("  last_modified_by_id: ").append(last_modified_by_id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

