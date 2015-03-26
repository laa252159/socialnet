package com.epam.socialnet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.model.Album;
import com.epam.socialnet.model.Photo;

@Controller
public class GallaryController extends MainUtilController {

	@RequestMapping(value = "/viewAlbum")
	public ModelAndView viewAlbum(HttpServletRequest request) throws IOException {

		ModelAndView model = new ModelAndView("ViewAlbum");
		Album album = galleryService.getAlbumById(Long.parseLong(request.getParameter("id")));
		model.addObject("album", album);
		model.addObject("person", personService.get(album.getPersonId()));
		model.addObject("photos", galleryService.getPhotosForAlbum(album.getId()));

		model.addObject("personInfo", personService.getCurrentPerson());
		setIsMyPageFlag(model, personService.getCurrentPerson().getId());
		addUnreadedLinksToModel(model);
		model.setViewName("ViewAlbum");
		model.addObject("currrentPersonId", personService.getCurrentPerson()
				.getId());
		addPersonsAlbumsToModel(model, personService.getCurrentPerson().getId());
		return model;
	}
	
	@RequestMapping(value = "/addPhoto")
	public ModelAndView addPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {

		long albumId= Long.parseLong(request.getParameter("id"));
		Photo photo = new Photo();
		photo.setAlbumId(albumId);
		galleryService.createPhoto(photo);
		return new ModelAndView("redirect:/viewAlbum?id=" + albumId);
	}
	
	@RequestMapping(value = "/removePhoto")
	public ModelAndView deletePhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {

		long photoId= Long.parseLong(request.getParameter("id"));
		long albumId = galleryService.getPhotoById(photoId).getAlbumId();
		galleryService.deletePhoto(photoId);
		return new ModelAndView("redirect:/viewAlbum?id=" + albumId);
	}
	
}
