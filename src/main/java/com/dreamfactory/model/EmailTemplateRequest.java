package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EmailAddress;
public class EmailTemplateRequest {
  /* Identifier of this email template. */
  @JsonProperty("id")
  private Integer id = null;
  /* Displayable name of this email template. */
  @JsonProperty("name")
  private String name = null;
  /* Description of this email template. */
  @JsonProperty("description")
  private String description = null;
  /* Single or multiple receiver addresses. */
  @JsonProperty("to")
  private List<EmailAddress> to = new ArrayList<EmailAddress>();
  /* Optional CC receiver addresses. */
  @JsonProperty("cc")
  private List<EmailAddress> cc = new ArrayList<EmailAddress>();
  /* Optional BCC receiver addresses. */
  @JsonProperty("bcc")
  private List<EmailAddress> bcc = new ArrayList<EmailAddress>();
  /* Text only subject line. */
  @JsonProperty("subject")
  private String subject = null;
  /* Text only version of the body. */
  @JsonProperty("body_text")
  private String body_text = null;
  /* Escaped HTML version of the body. */
  @JsonProperty("body_html")
  private String body_html = null;
  /* Required sender name and email. */
  @JsonProperty("from")
  private EmailAddress from = null;
  /* Optional reply to name and email. */
  @JsonProperty("reply_to")
  private EmailAddress reply_to = null;
  /* Array of default name value pairs for template replacement. */
  @JsonProperty("defaults")
  private List<String> defaults = new ArrayList<String>();
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public List<EmailAddress> getTo() {
    return to;
  }
  public void setTo(List<EmailAddress> to) {
    this.to = to;
  }

  public List<EmailAddress> getCc() {
    return cc;
  }
  public void setCc(List<EmailAddress> cc) {
    this.cc = cc;
  }

  public List<EmailAddress> getBcc() {
    return bcc;
  }
  public void setBcc(List<EmailAddress> bcc) {
    this.bcc = bcc;
  }

  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody_text() {
    return body_text;
  }
  public void setBody_text(String body_text) {
    this.body_text = body_text;
  }

  public String getBody_html() {
    return body_html;
  }
  public void setBody_html(String body_html) {
    this.body_html = body_html;
  }

  public EmailAddress getFrom() {
    return from;
  }
  public void setFrom(EmailAddress from) {
    this.from = from;
  }

  public EmailAddress getReply_to() {
    return reply_to;
  }
  public void setReply_to(EmailAddress reply_to) {
    this.reply_to = reply_to;
  }

  public List<String> getDefaults() {
    return defaults;
  }
  public void setDefaults(List<String> defaults) {
    this.defaults = defaults;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailTemplateRequest {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  to: ").append(to).append("\n");
    sb.append("  cc: ").append(cc).append("\n");
    sb.append("  bcc: ").append(bcc).append("\n");
    sb.append("  subject: ").append(subject).append("\n");
    sb.append("  body_text: ").append(body_text).append("\n");
    sb.append("  body_html: ").append(body_html).append("\n");
    sb.append("  from: ").append(from).append("\n");
    sb.append("  reply_to: ").append(reply_to).append("\n");
    sb.append("  defaults: ").append(defaults).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

