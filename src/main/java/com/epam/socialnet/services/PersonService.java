package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.model.Person;

public interface PersonService {
	
	public void saveOrUpdate(Person person);
	
	public void delete(long personId);
	
	public Person get(long personId);
	
	public List<Person> list();
	
	public Person getCurrentPerson();
	
	public List<Person> getFriends();
	
}
