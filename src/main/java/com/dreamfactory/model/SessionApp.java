package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class SessionApp {
  /* Id of the application. */
  @JsonProperty("id")
  private Integer id = null;
  /* Displayed name of the application. */
  @JsonProperty("name")
  private String name = null;
  /* Description of the application. */
  @JsonProperty("description")
  private String description = null;
  /* Does this application exist on a separate server. */
  @JsonProperty("is_url_external")
  private Boolean is_url_external = null;
  /* URL at which this app can be accessed. */
  @JsonProperty("launch_url")
  private String launch_url = null;
  /* True if the application requires fullscreen to run. */
  @JsonProperty("requires_fullscreen")
  private Boolean requires_fullscreen = null;
  /* True allows the fullscreen toggle widget to be displayed. */
  @JsonProperty("allow_fullscreen_toggle")
  private Boolean allow_fullscreen_toggle = null;
  /* Where the fullscreen toggle widget is to be displayed, defaults to top. */
  @JsonProperty("toggle_location")
  private String toggle_location = null;
  /* True if this app is set to launch by default at sign in. */
  @JsonProperty("is_default")
  private Boolean is_default = null;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getIs_url_external() {
    return is_url_external;
  }
  public void setIs_url_external(Boolean is_url_external) {
    this.is_url_external = is_url_external;
  }

  public String getLaunch_url() {
    return launch_url;
  }
  public void setLaunch_url(String launch_url) {
    this.launch_url = launch_url;
  }

  public Boolean getRequires_fullscreen() {
    return requires_fullscreen;
  }
  public void setRequires_fullscreen(Boolean requires_fullscreen) {
    this.requires_fullscreen = requires_fullscreen;
  }

  public Boolean getAllow_fullscreen_toggle() {
    return allow_fullscreen_toggle;
  }
  public void setAllow_fullscreen_toggle(Boolean allow_fullscreen_toggle) {
    this.allow_fullscreen_toggle = allow_fullscreen_toggle;
  }

  public String getToggle_location() {
    return toggle_location;
  }
  public void setToggle_location(String toggle_location) {
    this.toggle_location = toggle_location;
  }

  public Boolean getIs_default() {
    return is_default;
  }
  public void setIs_default(Boolean is_default) {
    this.is_default = is_default;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionApp {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  is_url_external: ").append(is_url_external).append("\n");
    sb.append("  launch_url: ").append(launch_url).append("\n");
    sb.append("  requires_fullscreen: ").append(requires_fullscreen).append("\n");
    sb.append("  allow_fullscreen_toggle: ").append(allow_fullscreen_toggle).append("\n");
    sb.append("  toggle_location: ").append(toggle_location).append("\n");
    sb.append("  is_default: ").append(is_default).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

