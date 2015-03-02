package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.PersonDAO;
import net.codejava.spring.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.socialnet.services.PersonService;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods. 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/")
	public ModelAndView home(ModelAndView model) throws IOException{
		model.addObject("personInfo", personService.getCurrentPerson());
		model.addObject("listOfFriends", personService.getFriends());
		model.setViewName("main");
		
		return model;
	}
	
	@RequestMapping(value="/goHome")
	public ModelAndView goHome(ModelAndView model) throws IOException{
		return home(model);
	}
	
	@RequestMapping(value = "/viewPerson", method = RequestMethod.GET)
	public ModelAndView viewPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		Person person = personService.get(personId);
		ModelAndView model = new ModelAndView("main");
		model.addObject("listOfFriends", personService.getFriends());
		model.addObject("personInfo", personService.get(personId));
		return model;
	}
	
	@RequestMapping(value = "/newPerson", method = RequestMethod.GET)
	public ModelAndView newPerson(ModelAndView model) {
		Person newPerson = new Person();
		model.addObject("person", newPerson);
		model.setViewName("PersonForm");
		return model;
	}
	
	@RequestMapping(value = "/savePerson", method = RequestMethod.POST)
	public ModelAndView savePerson(@ModelAttribute Person person) {
		personService.saveOrUpdate(person);		
		return new ModelAndView("redirect:/");
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
		//TODO добавить удаление из друзей
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editPerson", method = RequestMethod.GET)
	public ModelAndView editPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		Person person = personService.get(personId);
		ModelAndView model = new ModelAndView("PersonFormEdit");
		model.addObject("person", person);
		
		return model;
	}
}
