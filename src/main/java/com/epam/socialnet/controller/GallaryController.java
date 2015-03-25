package com.epam.socialnet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.model.Album;

@Controller
public class GallaryController extends MainUtilController {

	@RequestMapping(value = "/viewAlbum")
	public ModelAndView home(HttpServletRequest request) throws IOException {

		ModelAndView model = new ModelAndView("ViewAlbum");
		Album album = null;
		if (request.getParameter("id") != null) {
			album = galleryService.getAlbumById(Long.parseLong(request
					.getParameter("id")));
			model.addObject("album", album);
			model.addObject("person", personService.get(album.getPersonId()));
		}

		model.addObject("personInfo", personService.getCurrentPerson());
		setIsMyPageFlag(model, personService.getCurrentPerson().getId());
		addUnreadedLinksToModel(model);
		model.setViewName("ViewAlbum");
		model.addObject("currrentPersonId", personService.getCurrentPerson()
				.getId());
		addPersonsAlbumsToModel(model, personService.getCurrentPerson().getId());
		return model;
	}
}
