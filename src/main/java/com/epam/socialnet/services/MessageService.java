package com.epam.socialnet.services;

import com.epam.socialnet.model.Message;

public interface MessageService {
	
	public void add(Message message);
	
	public void update(long messageId, String newValue);
	
	public void delete(long messageId);
	
	public Message get(long messageId);
	
	public Message getBySenderAndReceiver(String login);
	
}
