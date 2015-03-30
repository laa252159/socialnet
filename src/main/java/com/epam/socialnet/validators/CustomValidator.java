/*package com.epam.socialnet.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.epam.socialnet.model.Person;
import com.epam.socialnet.services.PersonService;

public class CustomValidator implements Validator {

	
	@Autowired PersonService personService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		 return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		  Person p = (Person) obj;
		  
		  Person person = personService.getByLogin(p.getLogin());
		  if(person != null){
			  errors.rejectValue("login", "Login already exists!");
		  }
	}

}
*/