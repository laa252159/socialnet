package com.epam.socialnet.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GallaryController extends MainUtilController {
	
	@RequestMapping(value = "/viewAlbum")
	public ModelAndView home(ModelAndView model) throws IOException {
		model.addObject("personInfo", personService.getCurrentPerson());
		setIsMyPageFlag(model, personService.getCurrentPerson().getId());
		addUnreadedLinksToModel(model);
		model.setViewName("ViewAlbum");
		model.addObject("currrentPersonId", personService.getCurrentPerson().getId());
		addPersonsAlbumsToModel(model, personService.getCurrentPerson().getId());
		return model;
	}
}
