package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class Register {
  /* Email address of the new user. */
  @JsonProperty("email")
  private String email = null;
  /* First name of the new user. */
  @JsonProperty("first_name")
  private String first_name = null;
  /* Last name of the new user. */
  @JsonProperty("last_name")
  private String last_name = null;
  /* Full display name of the new user. */
  @JsonProperty("display_name")
  private String display_name = null;
  /* Password for the new user. */
  @JsonProperty("new_password")
  private String new_password = null;
  /* Code required with new_password when using email confirmation. */
  @JsonProperty("code")
  private String code = null;
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

  public String getNew_password() {
    return new_password;
  }
  public void setNew_password(String new_password) {
    this.new_password = new_password;
  }

  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Register {\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  first_name: ").append(first_name).append("\n");
    sb.append("  last_name: ").append(last_name).append("\n");
    sb.append("  display_name: ").append(display_name).append("\n");
    sb.append("  new_password: ").append(new_password).append("\n");
    sb.append("  code: ").append(code).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

