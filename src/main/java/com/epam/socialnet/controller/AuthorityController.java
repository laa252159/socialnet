package com.epam.socialnet.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.dto.PersonDto;
import com.epam.socialnet.model.Person;

@Controller
public class AuthorityController extends MainUtilController {

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
		PersonDto personDto = new PersonDto();
		model.addObject("personDto", personDto);
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
	public ModelAndView savePerson(@Valid PersonDto personDto, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return new ModelAndView("Registration");
		} else {
			try {
				Person person = new Person(personDto);
				person.setPassword(personService.getSHA256(person.getPassword()));
				personService.save(person);
			} catch (Exception e) {
				ModelAndView modelAndView = new ModelAndView("Registration");
				modelAndView.addObject("reasonDenine", e.getMessage()
						.toString());
				return modelAndView;
			}
		}
		return new ModelAndView("redirect:/");
	}
}
