package com.epam.socialnet.providers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.epam.socialnet.model.Person;
import com.epam.socialnet.services.PersonService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private PersonService personService;

	public CustomAuthenticationProvider(PersonService personService) {
		super();
		this.personService = personService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		 String name = authentication.getName();
	        String password = authentication.getCredentials().toString();
//	        Person person = personService.f)
	        
	        if (name.equals("test") && password.equals("test")) {
	            List<GrantedAuthority> grantedAuths = new ArrayList<>();
	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
	            return auth;
	        } else {
	            return null;
	        }
	}


	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
