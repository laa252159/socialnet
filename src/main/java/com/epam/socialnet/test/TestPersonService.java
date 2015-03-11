package com.epam.socialnet.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socialnet.model.Person;
import com.epam.socialnet.services.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-context.xml")
public class TestPersonService {

	@Autowired
	private PersonService personService;

	
	@Test
	public void getFriends() {
		List<Person> persons =  personService.getFriends("2");
		for(Person p : persons){
			System.out.println("Name: " + p.getfName()+" DOB: " + p.getDob());
		}
	}

}
