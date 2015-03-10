package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.dao.PersonDAO;
import com.epam.socialnet.dto.PersonDto;
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
		Person person = personDAO.get(2);
		return person;
	}

	@Override
	public List<Person> getFriends(String personId) {
		// TODO change logic!!!
		return personDAO.list();
	}
	
	@Override
	public List<PersonDto> getFriendsDtos(String personId) {
		// TODO change logic!!!
		return personDAO.listDtos();
	}

    @Override
    public void setPhoto(String id, byte[] img) {
        personDAO.setPhoto(id, img);
    }

    @Override
    public byte[] getPhoto(String id) {
        return personDAO.getPhoto(id);
    }
}
