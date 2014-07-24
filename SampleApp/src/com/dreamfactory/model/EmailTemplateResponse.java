package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EmailAddress;
public class EmailTemplateResponse {
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
  /* Date this email template was created. */
  @JsonProperty("created_date")
  private String created_date = null;
  /* User Id of who created this email template. */
  @JsonProperty("created_by_id")
  private Integer created_by_id = null;
  /* Date this email template was last modified. */
  @JsonProperty("last_modified_date")
  private String last_modified_date = null;
  /* User Id of who last modified this email template. */
  @JsonProperty("last_modified_by_id")
  private Integer last_modified_by_id = null;
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

  public String getCreated_date() {
    return created_date;
  }
  public void setCreated_date(String created_date) {
    this.created_date = created_date;
  }

  public Integer getCreated_by_id() {
    return created_by_id;
  }
  public void setCreated_by_id(Integer created_by_id) {
    this.created_by_id = created_by_id;
  }

  public String getLast_modified_date() {
    return last_modified_date;
  }
  public void setLast_modified_date(String last_modified_date) {
    this.last_modified_date = last_modified_date;
  }

  public Integer getLast_modified_by_id() {
    return last_modified_by_id;
  }
  public void setLast_modified_by_id(Integer last_modified_by_id) {
    this.last_modified_by_id = last_modified_by_id;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailTemplateResponse {\n");
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
    sb.append("  created_date: ").append(created_date).append("\n");
    sb.append("  created_by_id: ").append(created_by_id).append("\n");
    sb.append("  last_modified_date: ").append(last_modified_date).append("\n");
    sb.append("  last_modified_by_id: ").append(last_modified_by_id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

