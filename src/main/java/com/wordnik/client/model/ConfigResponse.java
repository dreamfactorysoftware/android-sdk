package com.wordnik.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import com.wordnik.client.model.HostInfo;
import com.wordnik.client.model.Integer;
public class ConfigResponse {
  /* Default Role Id assigned to newly registered users, set to null to turn off open registration. */
  @JsonProperty("open_reg_role_id")
  private Integer open_reg_role_id = null;
  /* Set to an email-type service id to require email confirmation of newly registered users. */
  @JsonProperty("open_reg_email_service_id")
  private Integer open_reg_email_service_id = null;
  /* Default email template used for open registration email confirmations. */
  @JsonProperty("open_reg_email_template_id")
  private Integer open_reg_email_template_id = null;
  /* Set to an email-type service id to allow user invites and invite confirmations via email service. */
  @JsonProperty("invite_email_service_id")
  private Integer invite_email_service_id = null;
  /* Default email template used for user invitations and confirmations via email service. */
  @JsonProperty("invite_email_template_id")
  private Integer invite_email_template_id = null;
  /* Set to an email-type service id to require email confirmation to reset passwords, otherwise defaults to security question and answer. */
  @JsonProperty("password_email_service_id")
  private Integer password_email_service_id = null;
  /* Default email template used for password reset email confirmations. */
  @JsonProperty("password_email_template_id")
  private Integer password_email_template_id = null;
  /* Role Id assigned for all guest sessions, set to null to require authenticated sessions. */
  @JsonProperty("guest_role_id")
  private Integer guest_role_id = null;
  /* Comma-delimited list of fields the user is allowed to edit. */
  @JsonProperty("editable_profile_fields")
  private String editable_profile_fields = null;
  /* CORS whitelist of allowed remote hosts. */
  @JsonProperty("allowed_hosts")
  private List<HostInfo> allowed_hosts = new ArrayList<HostInfo>();
  /* Version of the DSP software. */
  @JsonProperty("dsp_version")
  private String dsp_version = null;
  /* Version of the database schema. */
  @JsonProperty("db_version")
  private String db_version = null;
  public Integer getOpen_reg_role_id() {
    return open_reg_role_id;
  }
  public void setOpen_reg_role_id(Integer open_reg_role_id) {
    this.open_reg_role_id = open_reg_role_id;
  }

  public Integer getOpen_reg_email_service_id() {
    return open_reg_email_service_id;
  }
  public void setOpen_reg_email_service_id(Integer open_reg_email_service_id) {
    this.open_reg_email_service_id = open_reg_email_service_id;
  }

  public Integer getOpen_reg_email_template_id() {
    return open_reg_email_template_id;
  }
  public void setOpen_reg_email_template_id(Integer open_reg_email_template_id) {
    this.open_reg_email_template_id = open_reg_email_template_id;
  }

  public Integer getInvite_email_service_id() {
    return invite_email_service_id;
  }
  public void setInvite_email_service_id(Integer invite_email_service_id) {
    this.invite_email_service_id = invite_email_service_id;
  }

  public Integer getInvite_email_template_id() {
    return invite_email_template_id;
  }
  public void setInvite_email_template_id(Integer invite_email_template_id) {
    this.invite_email_template_id = invite_email_template_id;
  }

  public Integer getPassword_email_service_id() {
    return password_email_service_id;
  }
  public void setPassword_email_service_id(Integer password_email_service_id) {
    this.password_email_service_id = password_email_service_id;
  }

  public Integer getPassword_email_template_id() {
    return password_email_template_id;
  }
  public void setPassword_email_template_id(Integer password_email_template_id) {
    this.password_email_template_id = password_email_template_id;
  }

  public Integer getGuest_role_id() {
    return guest_role_id;
  }
  public void setGuest_role_id(Integer guest_role_id) {
    this.guest_role_id = guest_role_id;
  }

  public String getEditable_profile_fields() {
    return editable_profile_fields;
  }
  public void setEditable_profile_fields(String editable_profile_fields) {
    this.editable_profile_fields = editable_profile_fields;
  }

  public List<HostInfo> getAllowed_hosts() {
    return allowed_hosts;
  }
  public void setAllowed_hosts(List<HostInfo> allowed_hosts) {
    this.allowed_hosts = allowed_hosts;
  }

  public String getDsp_version() {
    return dsp_version;
  }
  public void setDsp_version(String dsp_version) {
    this.dsp_version = dsp_version;
  }

  public String getDb_version() {
    return db_version;
  }
  public void setDb_version(String db_version) {
    this.db_version = db_version;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigResponse {\n");
    sb.append("  open_reg_role_id: ").append(open_reg_role_id).append("\n");
    sb.append("  open_reg_email_service_id: ").append(open_reg_email_service_id).append("\n");
    sb.append("  open_reg_email_template_id: ").append(open_reg_email_template_id).append("\n");
    sb.append("  invite_email_service_id: ").append(invite_email_service_id).append("\n");
    sb.append("  invite_email_template_id: ").append(invite_email_template_id).append("\n");
    sb.append("  password_email_service_id: ").append(password_email_service_id).append("\n");
    sb.append("  password_email_template_id: ").append(password_email_template_id).append("\n");
    sb.append("  guest_role_id: ").append(guest_role_id).append("\n");
    sb.append("  editable_profile_fields: ").append(editable_profile_fields).append("\n");
    sb.append("  allowed_hosts: ").append(allowed_hosts).append("\n");
    sb.append("  dsp_version: ").append(dsp_version).append("\n");
    sb.append("  db_version: ").append(db_version).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

