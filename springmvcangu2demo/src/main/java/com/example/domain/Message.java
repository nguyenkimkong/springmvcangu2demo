package com.example.domain;

public class Message {

	private long uuid;
	private String senderFullName = "";
	private String sendDateFormat = "";
	private long sendDateInMillis;
	private String subject = "";
	private String shortContent = "";
	private String fullContent = "";
	
	public long getUuid() {
		return uuid;
	}

	public void setUuid(long uuid) {
		this.uuid = uuid;
	}

	public String getSenderFullName() {
		return senderFullName;
	}

	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}

	public String getSendDateFormat() {
		return sendDateFormat;
	}

	public void setSendDateFormat(String sendDateFormat) {
		this.sendDateFormat = sendDateFormat;
	}

	public long getSendDateInMillis() {
		return sendDateInMillis;
	}

	public void setSendDateInMillis(long sendDateInMillis) {
		this.sendDateInMillis = sendDateInMillis;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public String getFullContent() {
		return fullContent;
	}

	public void setFullContent(String fullContent) {
		this.fullContent = fullContent;
	}

}
