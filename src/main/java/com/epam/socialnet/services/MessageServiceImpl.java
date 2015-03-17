package com.epam.socialnet.services;

import com.epam.socialnet.dao.MessageDAO;
import com.epam.socialnet.model.Message;

public class MessageServiceImpl implements MessageService {

	private MessageDAO messageDao;

	@Override
	public void add(Message message) {
		messageDao.add(message);
	}

	@Override
	public void update(Message message) {
		messageDao.update(message);
	}

	@Override
	public void delete(long messageId) {
		messageDao.delete(messageId);
	}

	@Override
	public Message get(long messageId) {
		return messageDao.get(messageId);
	}

	@Override
	public Message getBySenderAndReceiver(String login) {
		return messageDao.getBySenderAndReceiver(login);
	}

}
