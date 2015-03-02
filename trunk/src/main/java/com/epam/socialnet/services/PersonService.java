package com.epam.socialnet.services;

import java.util.List;

import net.codejava.spring.model.Person;

public interface PersonService {
	
	public void saveOrUpdate(Person person);
	
	public void delete(long personId);
	
	public Person get(long personId);
	
	public List<Person> list();
	
	public Person getCurrentPerson();
	
}
