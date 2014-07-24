package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class ProfileResponse {
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
  /* Phone number. */
  @JsonProperty("phone")
  private String phone = null;
  /* Question to be answered to initiate password reset. */
  @JsonProperty("security_question")
  private String security_question = null;
  /* Id of the application to be launched at login. */
  @JsonProperty("default_app_id")
  private Integer default_app_id = null;
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

  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSecurity_question() {
    return security_question;
  }
  public void setSecurity_question(String security_question) {
    this.security_question = security_question;
  }

  public Integer getDefault_app_id() {
    return default_app_id;
  }
  public void setDefault_app_id(Integer default_app_id) {
    this.default_app_id = default_app_id;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfileResponse {\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  first_name: ").append(first_name).append("\n");
    sb.append("  last_name: ").append(last_name).append("\n");
    sb.append("  display_name: ").append(display_name).append("\n");
    sb.append("  phone: ").append(phone).append("\n");
    sb.append("  security_question: ").append(security_question).append("\n");
    sb.append("  default_app_id: ").append(default_app_id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

