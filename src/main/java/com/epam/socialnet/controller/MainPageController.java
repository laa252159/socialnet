package com.epam.socialnet.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController extends MainUtilController {

	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView model) throws IOException {
		model.addObject("personInfo", personService.getCurrentPerson());
		model.addObject("listOfFriends", personService.getFriendsDtos(String
				.valueOf(personService.getCurrentPerson().getId())));
		model.addObject("listOfWaiters", personService
				.getFriendshipWaiters(String.valueOf(personService
						.getCurrentPerson().getId())));
		model.addObject("listOfApprovers", personService
				.getFriendshipApprovers(String.valueOf(personService
						.getCurrentPerson().getId())));
		addToModelRequestersAndResponsersOfFriendshipToCurrentPerson(model);
		setIsMyPageFlag(model, personService.getCurrentPerson().getId());
		addUnreadedLinksToModel(model);
		model.setViewName("main");
		model.addObject("currrentPersonId", personService.getCurrentPerson().getId());
		addPersonsAlbumsToModel(model, personService.getCurrentPerson().getId());
		model.addObject("isEditor", isEditor(personService.getCurrentPerson()));
		return model;
	}

	@RequestMapping(value = "/goHome")
	public ModelAndView goHome(ModelAndView model) throws IOException {
		return home(model);
	}
}
