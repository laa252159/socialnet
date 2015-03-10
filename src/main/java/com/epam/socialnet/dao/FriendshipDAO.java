package com.epam.socialnet.dao;

import java.util.List;

import com.epam.socialnet.model.Friendship;

public interface FriendshipDAO {

	public void add(Friendship friendship);
	
	public void update(Friendship friendship);

	void delete(Friendship friendship);

	public List<Friendship> get(long firsPersonId, long secondPersonId);

}
