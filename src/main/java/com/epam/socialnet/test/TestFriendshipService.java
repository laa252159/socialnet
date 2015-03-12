package com.epam.socialnet.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socialnet.model.Friendship;
import com.epam.socialnet.services.FriendshipService;
import com.epam.socialnet.services.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-context.xml")
public class TestFriendshipService {

	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	PersonService personService;
	
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
	public void deleteFriendship() {
		friendshipService.delete(45,44);
		System.out.println("Friendship deleted!!!");
	}
	
	@Test
	public void getFrienship() {
		Friendship friendship = friendshipService.get(32, personService.getCurrentPerson().getId());
		if(friendship != null){
			System.out.println("Getted friendship!!! Approve status: " + friendship.isFriendshipApproved());	
		}
	}
	
//	@Test
	public void areFriens() {
		boolean ans = friendshipService.areFriends(2, 32);
			System.out.println("Friends status:" + ans);	
	} 
	
//	@Test
	public void isApproved() {
		boolean ans = friendshipService.isApprovied(personService.getCurrentPerson().getId(), 33);
			System.out.println("ReadyForeApprove status:" + ans);	
	} 
	
//	@Test
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
