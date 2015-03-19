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

	private long id;
	private String value;
	private long senderId;
	private long receiverId;
	private Date messageDate;
	
	//Transient fields (only for UI usage)	
	private String senderName;
	private String receiverName;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
}
