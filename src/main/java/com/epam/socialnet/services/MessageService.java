package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.model.Message;

public interface MessageService {

	public void add(Message message);

	public void update(long messageId, String newValue);

	public void delete(long messageId);

	public Message get(long messageId);

	public Message getBySenderAndReceiver(String login);

	public List<Message> getAllMessagesBetweenPersons(long senderId,
			long receiverId);

	public void setAllMessagesForReceiverFromSenderToReaded(long senderId,
			long receiverId);

	public List<Message> getUnreadedLinksForreceiver(String receiverId);
}
