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
	
//	@Test
	public void getFrienship() {
		Friendship friendship = friendshipService.get(111, 222);
		if(friendship != null){
			System.out.println("Getted friendship!!! Approve status: " + friendship.isFriendshipApproved());	
		}
	}
	
//	@Test
	public void areFriens() {
		boolean ans = friendshipService.areFriends(111, 222);
			System.out.println("Friends status:" + ans);	
	} 
	
	@Test
	public void approve() {
		Friendship friendship = friendshipService.get(111, 222);
		if(friendship != null){
			System.out.println("Getted friendship!!! Approve status: " + friendship.isFriendshipApproved());	
			friendshipService.approve(friendship);
			friendship = friendshipService.get(111, 222);
			System.out.println("Getted friendship!!! Approve status: " + friendship.isFriendshipApproved());	
		}
			
	} 

}
