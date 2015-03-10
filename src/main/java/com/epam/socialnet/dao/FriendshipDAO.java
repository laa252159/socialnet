package com.epam.socialnet.dao;

import java.util.List;

import com.epam.socialnet.model.Friendship;

public interface FriendshipDAO {

	public boolean areFriends(String firsPersonId, String secondPersonId);

	public void addFriendship(String firsPersonId, String secondPersonId);

	public void add(Friendship friendship);

	void delete(long friendshipId);

	Friendship get(long friendshipId);

	List<Friendship> list();

}
