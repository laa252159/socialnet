package com.epam.socialnet.dao;

import com.epam.socialnet.model.Message;

public interface MessageDAO {

	public void saveOrUpdate(Message mesage);

	public void delete(long mesageId);

	public Message get(long mesageId);

	public Message getByLogin(String login);
	
	public Message getBySenderAndReceiver(String login);
	
}
