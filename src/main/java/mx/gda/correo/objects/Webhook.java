package mx.gda.correo.objects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Webhook {

	@JsonProperty("id")
	@ApiModelProperty(value = "Webhook id")
	private String webhookid;
	@ApiModelProperty(value = "Recipient of message")
	private String email;
	@ApiModelProperty(value = "Message subject")
	private String subject;
	@ApiModelProperty(value = "Type of Event")	
	private String event;
	@ApiModelProperty(value = "URL accessed by recipient")
	private String link;
	@ApiModelProperty(value = "The reason the message has been deferred")
	private String reason;
	@ApiModelProperty(value = "Date sent listed in YEAR-MONTH-DAY, HOUR:MINUTE:SECOND in your timezone")
	private String date;
	@ApiModelProperty(value = "Ip used to send message")
	private String sending_ip;
	@JsonProperty("message-id")
	@ApiModelProperty(value = "Internal message id")
	private String messageId;
	@JsonProperty("template_id")
	@ApiModelProperty(value = "Internal id of the template")
	private String templateid;
	@ApiModelProperty(value = "Tags you might have used to identify your message (one field)")
	private String tag;
	@ApiModelProperty(value = "Tags you might have used to identify your message")
	private List<String> tags;	
	@ApiModelProperty(value = "Timestamp in seconds of when event occurred")
	private Integer ts;			
	@ApiModelProperty(value = "Time stamp in seconds GMT of when event occurred")
	private Integer ts_event;
	@ApiModelProperty(value = "Time stamp in seconds UTC of when message was sent")
	private String ts_epoch;

	public Webhook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Webhook(String webhookid, String email, String subject, String event, String link, String reason,
			String date, String sending_ip, String messageId, String templateid, String tag, List<String> tags,
			Integer ts, Integer ts_event, String ts_epoch) {
		super();
		this.webhookid = webhookid;
		this.email = email;
		this.subject = subject;
		this.event = event;
		this.link = link;
		this.reason = reason;
		this.date = date;
		this.sending_ip = sending_ip;
		this.messageId = messageId;
		this.templateid = templateid;
		this.tag = tag;
		this.tags = tags;
		this.ts = ts;
		this.ts_event = ts_event;
		this.ts_epoch = ts_epoch;
	}

	public String getWebhookid() {
		return webhookid;
	}

	public void setWebhookid(String webhookid) {
		this.webhookid = webhookid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSending_ip() {
		return sending_ip;
	}

	public void setSending_ip(String sending_ip) {
		this.sending_ip = sending_ip;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer getTs() {
		return ts;
	}

	public void setTs(Integer ts) {
		this.ts = ts;
	}

	public Integer getTs_event() {
		return ts_event;
	}

	public void setTs_event(Integer ts_event) {
		this.ts_event = ts_event;
	}

	public String getTs_epoch() {
		return ts_epoch;
	}

	public void setTs_epoch(String ts_epoch) {
		this.ts_epoch = ts_epoch;
	}

	
	
	

}