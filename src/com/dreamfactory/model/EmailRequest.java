package com.dreamfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

import com.dreamfactory.model.EmailAddress;
public class EmailRequest {
  /* Email Template name to base email on. */
  @JsonProperty("template")
  private String template = null;
  /* Email Template id to base email on. */
  @JsonProperty("template_id")
  private Integer template_id = null;
  /* Required single or multiple receiver addresses. */
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
  /* Required sender name. */
  @JsonProperty("from_name")
  private String from_name = null;
  /* Required sender email. */
  @JsonProperty("from_email")
  private String from_email = null;
  /* Optional reply to name. */
  @JsonProperty("reply_to_name")
  private String reply_to_name = null;
  /* Optional reply to email. */
  @JsonProperty("reply_to_email")
  private String reply_to_email = null;
  public String getTemplate() {
    return template;
  }
  public void setTemplate(String template) {
    this.template = template;
  }

  public Integer getTemplate_id() {
    return template_id;
  }
  public void setTemplate_id(Integer template_id) {
    this.template_id = template_id;
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

  public String getFrom_name() {
    return from_name;
  }
  public void setFrom_name(String from_name) {
    this.from_name = from_name;
  }

  public String getFrom_email() {
    return from_email;
  }
  public void setFrom_email(String from_email) {
    this.from_email = from_email;
  }

  public String getReply_to_name() {
    return reply_to_name;
  }
  public void setReply_to_name(String reply_to_name) {
    this.reply_to_name = reply_to_name;
  }

  public String getReply_to_email() {
    return reply_to_email;
  }
  public void setReply_to_email(String reply_to_email) {
    this.reply_to_email = reply_to_email;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailRequest {\n");
    sb.append("  template: ").append(template).append("\n");
    sb.append("  template_id: ").append(template_id).append("\n");
    sb.append("  to: ").append(to).append("\n");
    sb.append("  cc: ").append(cc).append("\n");
    sb.append("  bcc: ").append(bcc).append("\n");
    sb.append("  subject: ").append(subject).append("\n");
    sb.append("  body_text: ").append(body_text).append("\n");
    sb.append("  body_html: ").append(body_html).append("\n");
    sb.append("  from_name: ").append(from_name).append("\n");
    sb.append("  from_email: ").append(from_email).append("\n");
    sb.append("  reply_to_name: ").append(reply_to_name).append("\n");
    sb.append("  reply_to_email: ").append(reply_to_email).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

