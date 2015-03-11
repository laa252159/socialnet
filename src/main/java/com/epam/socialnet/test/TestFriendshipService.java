package com.epam.socialnet.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socialnet.model.Friendship;
import com.epam.socialnet.services.FriendshipService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestFriendshipService {

	@Autowired
	private FriendshipService friendshipService;
/*	
	public void add(Friendship friendship);

	public void delete(Friendship friendship);

	public Friendship get(long firsPersonId, long secondPersonId);

	public boolean areFriends(long firsPersonId, long secondPersonId);

	public void approve(Friendship friendship);*/
	
//	@Test
	public void addFrienship() {
		Friendship friendship = new Friendship();
		friendship.setDateOfFriendshipStarting(new Date());
		friendship.setFirsPersonId(111);
		friendship.setSecondPersonId(222);
		friendship.setFriendshipApproved(true);
		friendshipService.add(friendship);
		System.out.println("Friendship added!!");
	}
	
//	@Test
	public void deleteFrienship() {
		
		Friendship friendship = new Friendship();
		friendship.setDateOfFriendshipStarting(new Date());
		friendship.setFirsPersonId(111);
		friendship.setSecondPersonId(222);
		friendship.setFriendshipApproved(true);
		friendshipService.delete(friendship);
		System.out.println("Friendship deleted!!!");
	}
	
	@Test
	public void getFrienship() {
		Friendship friendship = friendshipService.get(111, 222);
		if(friendship != null){
			System.out.println("Getted friendship!!! Approve status: " + friendship.isFriendshipApproved());	
		}
	}

}
