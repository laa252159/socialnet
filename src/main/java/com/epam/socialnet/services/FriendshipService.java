package com.epam.socialnet.services;

import com.epam.socialnet.model.Friendship;

public interface FriendshipService {

	public void add(Friendship friendship);

	public void delete(long friendshipId);

	public Friendship get(String firsPersonId, String secondPersonId);

	public boolean areFriends(String firsPersonId, String secondPersonId);

	public void approve(Friendship friendship);

}
