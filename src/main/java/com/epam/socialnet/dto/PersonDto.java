package com.epam.socialnet.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PersonDto {
	
	private long id;
	
	@NotEmpty
	protected String login;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String fName;
	
	@NotEmpty
	private String lName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
}
