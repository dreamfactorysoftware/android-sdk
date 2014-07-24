package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class PasswordRequest {
  /* Old password to validate change during a session. */
  @JsonProperty("old_password")
  private String old_password = null;
  /* New password to be set. */
  @JsonProperty("new_password")
  private String new_password = null;
  /* User's email to be used with code to validate email confirmation. */
  @JsonProperty("email")
  private String email = null;
  /* Code required with new_password when using email confirmation. */
  @JsonProperty("code")
  private String code = null;
  public String getOld_password() {
    return old_password;
  }
  public void setOld_password(String old_password) {
    this.old_password = old_password;
  }

  public String getNew_password() {
    return new_password;
  }
  public void setNew_password(String new_password) {
    this.new_password = new_password;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
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
    sb.append("class PasswordRequest {\n");
    sb.append("  old_password: ").append(old_password).append("\n");
    sb.append("  new_password: ").append(new_password).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  code: ").append(code).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

