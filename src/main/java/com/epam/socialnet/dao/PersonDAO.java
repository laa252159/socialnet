package com.epam.socialnet.dao;

import java.util.List;

import com.epam.socialnet.dto.PersonDto;
import com.epam.socialnet.model.Person;

public interface PersonDAO {
	
	public void saveOrUpdate(Person person);
	
	public void delete(long personId);
	
	public Person get(long personId);
	
	public List<Person> list();
	
	public List<PersonDto> listDtos();

    public void setPhoto(String id, byte[] img);

    public byte[] getPhoto(String id);
}
