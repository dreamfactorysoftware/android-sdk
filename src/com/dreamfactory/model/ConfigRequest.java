package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.HostInfo;
//import com.dreamfactory.model.Integer;
public class ConfigRequest {
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
  /* An array of HTTP verbs that must be tunnelled on this server. */
  @JsonProperty("restricted_verbs")
  private List<String> restricted_verbs = new ArrayList<String>();
  /* The internal installation type ID for this server. */
  @JsonProperty("install_type")
  private Integer install_type = null;
  /* The name of the installation type for this server. */
  @JsonProperty("install_name")
  private String install_name = null;
  /* True if this is a free hosted DreamFactory DSP. */
  @JsonProperty("is_hosted")
  private Boolean is_hosted = null;
  /* True if this is a non-free DreamFactory hosted DSP. */
  @JsonProperty("is_private")
  private Boolean is_private = null;
  /* True if the current user has not logged in. */
  @JsonProperty("is_guest")
  private Boolean is_guest = null;
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

  public List<String> getRestricted_verbs() {
    return restricted_verbs;
  }
  public void setRestricted_verbs(List<String> restricted_verbs) {
    this.restricted_verbs = restricted_verbs;
  }

  public Integer getInstall_type() {
    return install_type;
  }
  public void setInstall_type(Integer install_type) {
    this.install_type = install_type;
  }

  public String getInstall_name() {
    return install_name;
  }
  public void setInstall_name(String install_name) {
    this.install_name = install_name;
  }

  public Boolean getIs_hosted() {
    return is_hosted;
  }
  public void setIs_hosted(Boolean is_hosted) {
    this.is_hosted = is_hosted;
  }

  public Boolean getIs_private() {
    return is_private;
  }
  public void setIs_private(Boolean is_private) {
    this.is_private = is_private;
  }

  public Boolean getIs_guest() {
    return is_guest;
  }
  public void setIs_guest(Boolean is_guest) {
    this.is_guest = is_guest;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConfigRequest {\n");
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
    sb.append("  restricted_verbs: ").append(restricted_verbs).append("\n");
    sb.append("  install_type: ").append(install_type).append("\n");
    sb.append("  install_name: ").append(install_name).append("\n");
    sb.append("  is_hosted: ").append(is_hosted).append("\n");
    sb.append("  is_private: ").append(is_private).append("\n");
    sb.append("  is_guest: ").append(is_guest).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

