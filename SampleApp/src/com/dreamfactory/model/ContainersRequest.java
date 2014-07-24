package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.Container;
public class ContainersRequest {
  /* An array of containers to modify. */
  @JsonProperty("container")
  private List<Container> container = new ArrayList<Container>();
  public List<Container> getContainer() {
    return container;
  }
  public void setContainer(List<Container> container) {
    this.container = container;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContainersRequest {\n");
    sb.append("  container: ").append(container).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

