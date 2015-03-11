package com.epam.socialnet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.model.Person;
import com.epam.socialnet.services.PersonService;

@Controller
public class HomeController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView model) throws IOException {
		model.addObject("personInfo", personService.getCurrentPerson());
		model.addObject("listOfFriends", personService.getFriendsDtos(String.valueOf(personService.getCurrentPerson().getId())));
		model.setViewName("main");

		return model;
	}

	@RequestMapping(value = "/goHome")
	public ModelAndView goHome(ModelAndView model) throws IOException {
		return home(model);
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView model) throws IOException {
		Person person = new Person();
		model.addObject("person", person);
		model.setViewName("Login");

		return model;
	}

	@RequestMapping(value = "/reg")
	public ModelAndView registration(ModelAndView model) throws IOException {
		Person person = new Person();
		model.addObject("person", person);
		model.setViewName("Registration");

		return model;
	}

	@RequestMapping(value = "/credentials")
	public ModelAndView loginEnter(@ModelAttribute Person person)
			throws IOException {
		System.out.println(person.getLogin());
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/savePerson", method = RequestMethod.POST)
	public ModelAndView savePerson(@ModelAttribute Person person) {
		personService.saveOrUpdate(person);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/viewPerson", method = RequestMethod.GET)
	public ModelAndView viewPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));

		Person person = personService.get(personId);
		ModelAndView model = new ModelAndView("main");
		model.addObject("listOfFriends", personService.getFriends(String.valueOf(personService.getCurrentPerson().getId())));
		model.addObject("personInfo", personService.get(personId));
		
		String fName = request.getParameter("fn");
		String lName = request.getParameter("ln");
		Person personToFind = new Person();
		personToFind.setfName(fName);
		personToFind.setlName(lName);
		
		model.addObject("foundedPersons", personService.findPersonDto(personToFind));
		
		return model;
	}
	
	@RequestMapping(value = "/viewPerson", method = RequestMethod.POST)
	public ModelAndView viewPersonPost(HttpServletRequest request) {
		return viewPerson(request);
	}

	@RequestMapping(value = "/newPerson", method = RequestMethod.GET)
	public ModelAndView newPerson(ModelAndView model) {
		Person newPerson = new Person();
		model.addObject("person", newPerson);
		model.setViewName("PersonForm");
		return model;
	}

	@RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
	public ModelAndView deletePerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		personService.delete(personId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deletePersonFromFriends", method = RequestMethod.GET)
	public ModelAndView deletePersonFromFriends(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		// TODO
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editPerson", method = RequestMethod.GET)
	public ModelAndView editPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		Person person = personService.get(personId);
		ModelAndView model = new ModelAndView("EditInfo");
		// model.addObject("id", person.getId());
		model.addObject("person", person);

		return model;
	}

	
	
	
	
	@RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    public ModelAndView uploadFileHandler(
    		@RequestParam("id_person") String id,
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
    public void showImage(@RequestParam("id") String id, HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException {

        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        byte[] photo = personService.getPhoto(id);
        if(photo != null){
        	response.getOutputStream().write(photo);	
        }
        response.getOutputStream().close();
    }
}
