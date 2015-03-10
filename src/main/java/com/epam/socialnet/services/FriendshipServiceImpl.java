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
		List<Friendship> f = friendshipDAO.get(friendship.getFirsPersonId(), friendship.getSecondPersonId());
		if(f!=null && !f.isEmpty()){
			f.get(0).setFriendshipApproved(true);
			friendshipDAO.update(f.get(0));
		}
	}

	@Override
	public void delete(Friendship friendship) {
		friendshipDAO.delete(friendship);
		
	}

	@Override
	public Friendship get(long firsPersonId, long secondPersonId) {
		List<Friendship> friendships = friendshipDAO.get(firsPersonId, secondPersonId);
		if(friendships !=null && !friendships.isEmpty()){
			return friendships.get(0);
		}
		return null;
	}

	@Override
	public boolean areFriends(long firsPersonId, long secondPersonId) {
		return get(firsPersonId, secondPersonId) != null;
	}
}
