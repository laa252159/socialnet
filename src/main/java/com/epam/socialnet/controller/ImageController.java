package com.epam.socialnet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.model.Person;

@Controller
public class ImageController extends MainUtilController {

	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
	public ModelAndView uploadFileHandler(@RequestParam("id_person") String id,
			@RequestParam("file") MultipartFile file) throws Exception {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			personService.setPhoto(id, bytes);
		}

		long personId = Long.parseLong(id);
		Person person = personService.get(personId);
		ModelAndView model = new ModelAndView("EditInfo");
		model.addObject("person", person);

		return model;
	}

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") String id,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		byte[] photo = personService.getPhoto(id);
		if (photo != null) {
			response.getOutputStream().write(photo);
		}
		response.getOutputStream().close();
	}
}
