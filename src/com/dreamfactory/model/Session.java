package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.SessionApp;
public class Session {
  /* Identifier for the current user. */
  @JsonProperty("id")
  private String id = null;
  /* Email address of the current user. */
  @JsonProperty("email")
  private String email = null;
  /* First name of the current user. */
  @JsonProperty("first_name")
  private String first_name = null;
  /* Last name of the current user. */
  @JsonProperty("last_name")
  private String last_name = null;
  /* Full display name of the current user. */
  @JsonProperty("display_name")
  private String display_name = null;
  /* Is the current user a system administrator. */
  @JsonProperty("is_sys_admin")
  private Boolean is_sys_admin = null;
  /* Name of the role to which the current user is assigned. */
  @JsonProperty("role")
  private String role = null;
  /* Date timestamp of the last login for the current user. */
  @JsonProperty("last_login_date")
  private String last_login_date = null;
  /* App groups and the containing apps. */
  @JsonProperty("app_groups")
  private List<SessionApp> app_groups = new ArrayList<SessionApp>();
  /* Apps that are not in any app groups. */
  @JsonProperty("no_group_apps")
  private List<SessionApp> no_group_apps = new ArrayList<SessionApp>();
  /* Id for the current session, used in X-DreamFactory-Session-Token header for API requests. */
  @JsonProperty("session_id")
  private String session_id = null;
  /* Timed ticket that can be used to start a separate session. */
  @JsonProperty("ticket")
  private String ticket = null;
  /* Expiration time for the given ticket. */
  @JsonProperty("ticket_expiry")
  private String ticket_expiry = null;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirst_name() {
    return first_name;
  }
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getDisplay_name() {
    return display_name;
  }
  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  public Boolean getIs_sys_admin() {
    return is_sys_admin;
  }
  public void setIs_sys_admin(Boolean is_sys_admin) {
    this.is_sys_admin = is_sys_admin;
  }

  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }

  public String getLast_login_date() {
    return last_login_date;
  }
  public void setLast_login_date(String last_login_date) {
    this.last_login_date = last_login_date;
  }

  public List<SessionApp> getApp_groups() {
    return app_groups;
  }
  public void setApp_groups(List<SessionApp> app_groups) {
    this.app_groups = app_groups;
  }

  public List<SessionApp> getNo_group_apps() {
    return no_group_apps;
  }
  public void setNo_group_apps(List<SessionApp> no_group_apps) {
    this.no_group_apps = no_group_apps;
  }

  public String getSession_id() {
    return session_id;
  }
  public void setSession_id(String session_id) {
    this.session_id = session_id;
  }

  public String getTicket() {
    return ticket;
  }
  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public String getTicket_expiry() {
    return ticket_expiry;
  }
  public void setTicket_expiry(String ticket_expiry) {
    this.ticket_expiry = ticket_expiry;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Session {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  first_name: ").append(first_name).append("\n");
    sb.append("  last_name: ").append(last_name).append("\n");
    sb.append("  display_name: ").append(display_name).append("\n");
    sb.append("  is_sys_admin: ").append(is_sys_admin).append("\n");
    sb.append("  role: ").append(role).append("\n");
    sb.append("  last_login_date: ").append(last_login_date).append("\n");
    sb.append("  app_groups: ").append(app_groups).append("\n");
    sb.append("  no_group_apps: ").append(no_group_apps).append("\n");
    sb.append("  session_id: ").append(session_id).append("\n");
    sb.append("  ticket: ").append(ticket).append("\n");
    sb.append("  ticket_expiry: ").append(ticket_expiry).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

