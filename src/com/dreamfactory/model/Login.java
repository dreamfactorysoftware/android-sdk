package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class Login {
  @JsonProperty("email")
  private String email = null;
  @JsonProperty("password")
  private String password = null;
  /* Duration of the session, Defaults to 0, which means until browser is closed. */
  @JsonProperty("duration")
  private Integer duration = null;
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getDuration() {
    return duration;
  }
  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Login {\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  password: ").append(password).append("\n");
    sb.append("  duration: ").append(duration).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

