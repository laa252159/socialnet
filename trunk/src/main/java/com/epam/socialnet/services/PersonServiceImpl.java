package com.epam.socialnet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.codejava.spring.dao.PersonDAO;
import net.codejava.spring.model.Person;

public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;
	
	public PersonServiceImpl(PersonDAO personDAO) {
		super();
		this.personDAO = personDAO;
	}

	@Override
	public void saveOrUpdate(Person person) {
		personDAO.saveOrUpdate(person);
	}

	@Override
	public void delete(long personId) {
		personDAO.delete(personId);
		
	}

	@Override
	public Person get(long personId) {
		return personDAO.get(personId);
	}

	@Override
	public List<Person> list() {
		return personDAO.list();
	}

	@Override
	public Person getCurrentPerson() {
		//TODO get Current user ID from session, and get it from DAO by ID
		Person person = new Person(1000, "Alex");
		return person;
	}
	
	
}
