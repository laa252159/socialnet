package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.dao.PersonDAO;
import com.epam.socialnet.model.Person;

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
		Person person = new Person(1000, "Me");
		return person;
	}

	@Override
	public List<Person> getFriends() {
		// TODO change logic!!!
		return personDAO.list();
	}
	
	
}
