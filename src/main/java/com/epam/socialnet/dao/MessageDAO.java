package com.epam.socialnet.dao;

import com.epam.socialnet.model.Message;

public interface MessageDAO {

	public void add(Message message);
	
	public void update(Message message);

	public void delete(long mesageId);

	public Message get(long mesageId);

	public Message getBySenderAndReceiver(String login);
	
}
