package com.epam.socialnet.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
	public void save(Person person) throws Exception {
		if (personDAO.getByLogin(person.getLogin()) != null) {
			throw new Exception("Логин '"
					+ personDAO.getByLogin(person.getLogin()).getLogin()
					+ "' уже существует в системе.");
		}
		;
		personDAO.saveOrUpdate(person);
	}

	@Override
	public void update(Person person) {
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
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		Person person = personDAO.getByLogin(name);
		return person;
	}

	@Override
	public void setPhoto(String id, byte[] img) {
		personDAO.setPhoto(id, img);
	}

	@Override
	public byte[] getPhoto(String id) {
		return personDAO.getPhoto(id);
	}

	@Override
	public List<PersonDto> getFriendsDtos(String personId) {
		return personDAO.getFriendsDtos(personId);
	}

	@Override
	public List<PersonDto> findPersonDto(Person person) {
		return personDAO.findPersonDto(person);
	}

	@Override
	public List<Person> findPerson(Person person) {
		return personDAO.findPerson(person);
	}

	@Override
	public List<Person> getFriends(String personId) {
		return personDAO.getFriends(personId);
	}

	@Override
	public List<Person> getFriendshipApprovers(String personId) {
		return personDAO.getFriendshipApprovers(personId);
	}

	@Override
	public List<Person> getFriendshipWaiters(String personId) {
		return personDAO.getFriendshipWaiters(personId);
	}

	@Override
	public Person getByLogin(String login) {
		return personDAO.getByLogin(login);
	}

	@Override
	public String getSHA256(String str) {
		MessageDigest md;
		byte[] digest = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes("UTF-8"));
			digest = md.digest();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(digest !=null && digest.length != 0){
			//convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<digest.length;i++) {
	    	  hexString.append(Integer.toHexString(0xFF & digest[i]));
	    	}
			return hexString.toString();
		}		
		return null;
	}
}
