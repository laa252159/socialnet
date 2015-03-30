package com.epam.socialnet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.model.Person;

@Controller
public class FriendshipAndPersonController extends MainUtilController {

	// FRIENDSHIP ACTIONS

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

	// PERSON ACTIONS

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
			person.setPassword(personService.getSHA256(person.getPassword()));
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
	public String updatePerson(@Valid Person person, BindingResult result, HttpServletRequest request, Model model) {
		
		if (result.hasErrors()) {
			return "EditInfo";
		} else {
			personService.update(person);
		}
		return "redirect:/";
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
		messageService.setAllMessagesForReceiverFromSenderToReaded(personId,
				personService.getCurrentPerson().getId());
		addUnreadedLinksToModel(model);
		setIsMyPageFlag(model, personId);
		model.addObject("currrentPersonId", personService.getCurrentPerson()
				.getId());
		addPersonsAlbumsToModel(model, personId);
		return model;
	}

	@RequestMapping(value = "/viewPerson", method = RequestMethod.POST)
	public ModelAndView viewPersonPost(HttpServletRequest request) {
		return viewPerson(request);
	}
}
