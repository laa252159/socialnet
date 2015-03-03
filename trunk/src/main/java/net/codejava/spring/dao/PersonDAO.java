package net.codejava.spring.dao;

import java.util.List;

import com.epam.socialnet.model.Person;

/**
 * Defines DAO operations for the person model.
 * @author www.codejava.net
 *
 */
public interface PersonDAO {
	
	public void saveOrUpdate(Person person);
	
	public void delete(long personId);
	
	public Person get(long personId);
	
	public List<Person> list();
}
