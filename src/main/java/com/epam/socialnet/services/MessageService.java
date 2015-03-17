package com.epam.socialnet.services;

import com.epam.socialnet.model.Message;

public interface MessageService {
	
	public void save(Message message) throws Exception;
	
	public void update(Message message);
	
	public void delete(long messageId);
	
	public Message get(long messageId);
	
	public Message getBySenderAndReceiver(String login);
	
}
