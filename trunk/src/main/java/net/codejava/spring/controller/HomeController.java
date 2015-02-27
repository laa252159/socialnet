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

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods. 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	private PersonDAO personDAO;
	
	@RequestMapping(value="/")
	public ModelAndView listPerson(ModelAndView model) throws IOException{
		List<Person> listPerson = personDAO.list();
		model.addObject("listPerson", listPerson);
		model.setViewName("home");
		
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
		personDAO.saveOrUpdate(person);		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deletePerson", method = RequestMethod.GET)
	public ModelAndView deletePerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		personDAO.delete(personId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editPerson", method = RequestMethod.GET)
	public ModelAndView editPerson(HttpServletRequest request) {
		long personId = Long.parseLong(request.getParameter("id"));
		Person person = personDAO.get(personId);
		ModelAndView model = new ModelAndView("PersonForm");
		model.addObject("person", person);
		
		return model;
	}
}
