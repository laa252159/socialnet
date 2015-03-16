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

import com.epam.socialnet.model.Friendship;
import com.epam.socialnet.model.Person;
import com.epam.socialnet.services.FriendshipService;
import com.epam.socialnet.services.PersonService;

@Controller
public class HomeController {

	@Autowired
	private PersonService personService;

	@Autowired
	private FriendshipService friendshipService;

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
		model.setViewName("main");

		return model;
	}

	private void setIsMyPageFlag(ModelAndView model, Long personId) {
		if (personId == null || personId == 0L) {
			model.addObject("isMyPage", false);
		} else {
			model.addObject("isMyPage", personId.longValue() == (personService
					.getCurrentPerson().getId()));
		}
	}

	@RequestMapping(value = "/goHome")
	public ModelAndView goHome(ModelAndView model) throws IOException {
		return home(model);
	}

	// Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
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

	@RequestMapping(value = "/viewPerson", method = RequestMethod.GET)
	public ModelAndView viewPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));

		ModelAndView model = new ModelAndView("main");
		model.addObject("listOfFriends", personService.getFriends(String
				.valueOf(personService.getCurrentPerson().getId())));
		model.addObject("personInfo", personService.get(personId));

		Person personToFind = new Person();
		personToFind.setfName(request.getParameter("fn"));
		personToFind.setlName(request.getParameter("ln"));
		model.addObject("foundedPersons",
				personService.findPersonDto(personToFind));

		initFriendshipButtopns(model, friendshipService.get(personId,
				personService.getCurrentPerson().getId()));

		setIsMyPageFlag(model, personId);

		return model;
	}

	private void initFriendshipButtopns(ModelAndView model,
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

	@RequestMapping(value = "/viewPerson", method = RequestMethod.POST)
	public ModelAndView viewPersonPost(HttpServletRequest request) {
		return viewPerson(request);
	}

	@RequestMapping(value = "/friendshipRequest", method = RequestMethod.GET)
	public ModelAndView friendshipRequest(HttpServletRequest request) {
		friendshipService.add(personService.getCurrentPerson().getId(),
				Long.parseLong(request.getParameter("id")));
		return viewPerson(request);
	}

	@RequestMapping(value = "/deleteFriendship", method = RequestMethod.GET)
	public ModelAndView deleteFriendship(HttpServletRequest request) {
		friendshipService.delete(personService.getCurrentPerson().getId(),
				Long.parseLong(request.getParameter("id")));
		return viewPerson(request);
	}

	@RequestMapping(value = "/approveFriendship", method = RequestMethod.GET)
	public ModelAndView approveFriendship(HttpServletRequest request) {
		friendshipService.approve(personService.getCurrentPerson().getId(),
				Long.parseLong(request.getParameter("id")));
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
		// long personId = Long.parseLong(request.getParameter("id"));
		// TODO
		return new ModelAndView("redirect:/");
	}

	// Если не будет работать, обрати внимание на формат даты
	@RequestMapping(value = "/editPerson", method = RequestMethod.GET)
	public ModelAndView editPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		if (personId != personService.getCurrentPerson().getId()) {
			return new ModelAndView("redirect:/");
		}
		Person person = personService.get(personId);
		ModelAndView model = new ModelAndView("EditInfo");
		model.addObject("person", person);

		return model;
	}

	@RequestMapping(value = "/savePerson", method = RequestMethod.POST)
	public ModelAndView savePerson(@ModelAttribute Person person) {
		try {
			personService.save(person);
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView model = new ModelAndView("Registration");
			model.addObject("reasonDenine", e.getMessage().toString());
			model.addObject("person", person);
			return model;
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
	public ModelAndView updatePerson(@ModelAttribute Person person) {
		personService.update(person);
		return new ModelAndView("redirect:/");
	}

	private void addToModelRequestersAndResponsersOfFriendshipToCurrentPerson(
			ModelAndView model) {
		model.addObject("requesters", personService.getFriendshipWaiters(String
				.valueOf(personService.getCurrentPerson().getId())));
		model.addObject("responsers", personService
				.getFriendshipApprovers(String.valueOf(personService
						.getCurrentPerson().getId())));
	}

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
