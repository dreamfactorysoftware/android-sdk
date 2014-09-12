package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public class ScriptOutput {
  /* The output of the script, if any. */
  @JsonProperty("script_output")
  private String script_output = null;
  /* The value of the last variable created within the script. */
  @JsonProperty("script_last_variable")
  private String script_last_variable = null;
  public String getScript_output() {
    return script_output;
  }
  public void setScript_output(String script_output) {
    this.script_output = script_output;
  }

  public String getScript_last_variable() {
    return script_last_variable;
  }
  public void setScript_last_variable(String script_last_variable) {
    this.script_last_variable = script_last_variable;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScriptOutput {\n");
    sb.append("  script_output: ").append(script_output).append("\n");
    sb.append("  script_last_variable: ").append(script_last_variable).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

