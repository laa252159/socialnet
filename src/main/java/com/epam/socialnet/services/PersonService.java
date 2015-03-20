package com.epam.socialnet.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.epam.socialnet.dto.PersonDto;
import com.epam.socialnet.model.Person;

public interface PersonService {
	
	public void save(Person person) throws Exception;
	
	public void update(Person person);
	
	public void delete(long personId);
	
	public Person get(long personId);
	
	public List<Person> list();
	
	public Person getCurrentPerson();
	
    public void setPhoto(String id, byte[] img);

    public byte[] getPhoto(String id);
    
	public List<Person> getFriends(String personId);
	
	public List<Person> getFriendshipApprovers(String personId);
	
	public List<Person> getFriendshipWaiters(String personId);
	
	public List<PersonDto> getFriendsDtos(String personId);
	
	public List<Person> findPerson(Person person);
	
	public List<PersonDto> findPersonDto(Person person);
	
	public Person getByLogin(String login);
	
	public String getSHA256(String str);
	
}
