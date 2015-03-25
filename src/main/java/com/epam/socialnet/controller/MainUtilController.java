package com.epam.socialnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.model.Friendship;
import com.epam.socialnet.model.Message;
import com.epam.socialnet.services.FriendshipService;
import com.epam.socialnet.services.GalleryService;
import com.epam.socialnet.services.MessageService;
import com.epam.socialnet.services.PersonService;

public class MainUtilController {

	@Autowired
	protected PersonService personService;

	@Autowired
	protected FriendshipService friendshipService;

	@Autowired
	protected MessageService messageService;
	
	@Autowired GalleryService galleryService;

	protected void addToModelRequestersAndResponsersOfFriendshipToCurrentPerson(
			ModelAndView model) {
		model.addObject("requesters", personService.getFriendshipWaiters(String
				.valueOf(personService.getCurrentPerson().getId())));
		model.addObject("responsers", personService
				.getFriendshipApprovers(String.valueOf(personService
						.getCurrentPerson().getId())));
	}

	protected void initFriendshipButtopns(ModelAndView model,
			Friendship friendship) {
		if (friendship == null) {
			model.addObject("bindingExists", false);
			model.addObject("isFriend", false);
			model.addObject("needApprove", false);
		} else {
			model.addObject("bindingExists", true);
			if (friendship.isFriendshipApproved()) {
				model.addObject("isFriend", true);
			} else if (friendship.getSecondPersonId() == personService
					.getCurrentPerson().getId()) {
				model.addObject("needApprove", true);
			}
		}

	}

	protected void setIsMyPageFlag(ModelAndView model, Long personId) {
		if (personId == null || personId == 0L) {
			model.addObject("isMyPage", false);
		} else {
			model.addObject("isMyPage", personId.longValue() == (personService
					.getCurrentPerson().getId()));
		}
	}

	protected void addUreadedLinksToModel(ModelAndView model) {
		List<Message> links = messageService.getUnreadedLinksForreceiver(String
				.valueOf(personService.getCurrentPerson().getId()));
		model.addObject("unreadLinks", links);
	}
	
	protected void addPersonsAlbumsToModel(ModelAndView model, Long personId){
		model.addObject("personsAlbums", galleryService.getAlbumsForPerson(personId));
	}
	

}
