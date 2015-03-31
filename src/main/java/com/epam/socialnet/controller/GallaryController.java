package com.epam.socialnet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
		model.addObject("isEditor", isEditor(galleryService.getAlbumById(album.getId())));
		return model;
	}
	
	@RequestMapping(value = "/addPhoto")
	public ModelAndView addPhoto(HttpServletRequest request) throws IOException {

		long albumId= Long.parseLong(request.getParameter("id"));
		Photo photo = new Photo();
		photo.setAlbumId(albumId);
		galleryService.createOrUpdatePhoto(photo);
		return new ModelAndView("redirect:/viewAlbum?id=" + albumId);
	}
	
	@RequestMapping(value = "/addAlbum")
	public ModelAndView addAlbum(HttpServletRequest request) throws IOException {

		long personId= Long.parseLong(request.getParameter("id"));
		Album album = new Album();
		album.setPersonId(personId);
		galleryService.createOrUpdateAlbum(album);
		return new ModelAndView("redirect:/viewPerson?id=" + personId);
	}
	
	@RequestMapping(value = "/removePhoto")
	public ModelAndView deletePhoto(HttpServletRequest request) throws IOException {

		long photoId= Long.parseLong(request.getParameter("id"));
		long albumId = galleryService.getPhotoById(photoId).getAlbumId();
		galleryService.deletePhoto(photoId);
		return new ModelAndView("redirect:/viewAlbum?id=" + albumId);
	}
	
	@RequestMapping(value = "/removeAlbum")
	public ModelAndView removeAlbum(HttpServletRequest request) throws IOException {

		long albumId= Long.parseLong(request.getParameter("id"));
		long personId = galleryService.getAlbumById(albumId).getPersonId();
		galleryService.deleteAlbum(albumId);
		return new ModelAndView("redirect:/viewPerson?id=" + personId);
	}
	
	@RequestMapping(value = "/editPhoto")
	public ModelAndView editPhoto(HttpServletRequest request) throws IOException {
		Photo photo = galleryService.getPhotoById(Long.parseLong(request.getParameter("id")));
		ModelAndView model = new ModelAndView("EditPhotoInfo");
		model.addObject("photo", photo);
		return model;
	}
	
	
	@RequestMapping(value = "/updatephotoToGallery", method = RequestMethod.POST)
	public ModelAndView updatePhoto(@ModelAttribute Photo photo) {
		galleryService.createOrUpdatePhoto(photo);
		return new ModelAndView("redirect:/viewAlbum?id=" + photo.getAlbumId());
	}
	
	@RequestMapping(value = "/updateAlbumInfo", method = RequestMethod.POST)
	public ModelAndView updateAlbumInfo(@ModelAttribute Album album) {
		galleryService.createOrUpdateAlbum(album);
		return new ModelAndView("redirect:/viewAlbum?id=" + album.getId());
	}
	
	@RequestMapping(value = "/editAlbumInfo")
	public ModelAndView editAlbumInfo(HttpServletRequest request) throws IOException {
		Album album  = galleryService.getAlbumById(Long.parseLong(request.getParameter("id")));
		ModelAndView model = new ModelAndView("EditAlbumInfo");
		model.addObject("album", album);
		return model;
	}
	
	
	@RequestMapping(value = "/photoDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") String id,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		byte[] photo = galleryService.getImgPreView(Long.parseLong(id));
		if (photo != null) {
			response.getOutputStream().write(photo);
		}
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/albumImageDisplay", method = RequestMethod.GET)
	public void albumImageDisplay(@RequestParam("id") String albumId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		byte[] photo = galleryService.getImgForAlbum(Long.parseLong(albumId));
		if (photo != null) {
			response.getOutputStream().write(photo);
		}
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/uploadPhotoToGallery", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("id_photo") String id,
			@RequestParam("file") MultipartFile file) throws Exception {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			galleryService.setPhoto(id, bytes);
		}

		long photoId = Long.parseLong(id);
		Photo photo = galleryService.getPhotoById(photoId);
		ModelAndView model = new ModelAndView("EditPhotoInfo");
		model.addObject("photo", photo);
		return model;
	}
	
	
}
