package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class PasswordResponse {
  /* User's security question, returned on reset request when no email confirmation required. */
  @JsonProperty("security_question")
  private String security_question = null;
  /* True if password updated or reset request granted via email confirmation. */
  @JsonProperty("success")
  private Boolean success = null;
  public String getSecurity_question() {
    return security_question;
  }
  public void setSecurity_question(String security_question) {
    this.security_question = security_question;
  }

  public Boolean getSuccess() {
    return success;
  }
  public void setSuccess(Boolean success) {
    this.success = success;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordResponse {\n");
    sb.append("  security_question: ").append(security_question).append("\n");
    sb.append("  success: ").append(success).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

