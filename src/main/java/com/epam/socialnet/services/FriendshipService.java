package com.epam.socialnet.services;

import com.epam.socialnet.model.Friendship;

public interface FriendshipService {

	public void add(Friendship friendship);
	
	public void add(long firsPersonId, long secondPersonId);

	public void delete(Friendship friendship);
	
	public void delete(long firsPersonId, long secondPersonId);

	public Friendship get(long firsPersonId, long secondPersonId);

	public boolean areFriends(long firsPersonId, long secondPersonId);
	
	public boolean isApprovied(long firsPersonId, long secondPersonId);

	public void approve(Friendship friendship);

}
