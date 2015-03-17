package com.epam.socialnet.model;

import java.util.Date;

public class Message {
	
	public Message(String value, long senderId, long receiverId,
			Date messageDate) {
		super();
		this.value = value;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.messageDate = messageDate;
	}
	
	public Message() {
		super();
	}

	private String value;
	private long senderId;
	private long receiverId;
	private Date messageDate;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
}
