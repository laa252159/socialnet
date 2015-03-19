package com.epam.socialnet.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socialnet.dto.PersonDto;
import com.epam.socialnet.model.Person;
import com.epam.socialnet.services.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/spring-security.xml" })
public class TestPersonService {

	@Autowired
	private PersonService personService;

//	 @Test
	public void getFriends() {
		List<Person> persons = personService.getFriends("2");
		for (Person p : persons) {
			System.out.println("Name: " + p.getfName() + " DOB: " + p.getDob());
		}
	}

//	@Test
	public void getPersonByLogin() {
		Person person = personService.getByLogin("mock");
		System.out.println("Login: " + person.getLogin() + " Password: "
				+ person.getPassword());
	}

	// @Test
	public void findPersonDto() {
		Person person = new Person();
		person.setfName("Пет");
		person.setlName("");
		List<PersonDto> persons = personService.findPersonDto(person);
		for (PersonDto p : persons) {
			System.out.println("Name: " + p.getfName() + " Last name: "
					+ p.getlName());
		}
	}

	// @Test
	public void findPerson() {
		Person person = new Person();
		person.setfName("Пе");
		person.setlName("");
		List<Person> persons = personService.findPerson(person);
		for (Person p : persons) {
			System.out.println("Name: " + p.getfName() + " Last name: "
					+ p.getlName());
		}
	}

	// @Test
	public void getFriendsRequestedByPerson() {
		List<Person> persons = personService.getFriendshipApprovers("2");
		for (Person p : persons) {
			System.out.println("Name: " + p.getfName() + " id:" + p.getId()
					+ " DOB: " + p.getDob());
		}
	}

	// @Test
	public void getFriendsRequestedToPerson() {
		List<Person> persons = personService.getFriendshipWaiters("2");
		for (Person p : persons) {
			System.out.println("Name: " + p.getfName() + " id:" + p.getId()
					+ " DOB: " + p.getDob());
		}
	}

}
