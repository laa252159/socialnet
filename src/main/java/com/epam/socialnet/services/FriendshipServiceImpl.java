package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.dao.FriendshipDAO;
import com.epam.socialnet.model.Friendship;

public class FriendshipServiceImpl implements FriendshipService {

	
	private FriendshipDAO friendshipDAO;
	
	public FriendshipServiceImpl(FriendshipDAO friendshipDAO) {
		super();
		this.friendshipDAO = friendshipDAO;
	}

	@Override
	public void add(Friendship friendship) {
		List<Friendship> f = friendshipDAO.get(friendship.getFirsPersonId(), friendship.getSecondPersonId());
		if(f == null || f.isEmpty()){
			friendshipDAO.add(friendship);
		}
	}

	@Override
	public void approve(Friendship friendship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long friendshipId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Friendship get(String firsPersonId, String secondPersonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean areFriends(String firsPersonId, String secondPersonId) {
		// TODO Auto-generated method stub
		return false;
	}
}
