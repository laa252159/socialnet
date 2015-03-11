package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.dto.PersonDto;
import com.epam.socialnet.model.Person;

public interface PersonService {
	
	public void saveOrUpdate(Person person);
	
	public void delete(long personId);
	
	public Person get(long personId);
	
	public List<Person> list();
	
	public Person getCurrentPerson();
	
    public void setPhoto(String id, byte[] img);

    public byte[] getPhoto(String id);
    
	public List<Person> getFriends(String personId);
	
	public List<PersonDto> getFriendsDtos(String personId);
	
	public List<Person> findPerson(Person person);
	
	public List<PersonDto> findPersonDto(Person person);
}
