package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.ScriptResponse;
public class ScriptsResponse {
  /* An array of script resources */
  @JsonProperty("script")
  private List<ScriptResponse> script = new ArrayList<ScriptResponse>();
  public List<ScriptResponse> getScript() {
    return script;
  }
  public void setScript(List<ScriptResponse> script) {
    this.script = script;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScriptsResponse {\n");
    sb.append("  script: ").append(script).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

