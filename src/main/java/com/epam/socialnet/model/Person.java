package com.epam.socialnet.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


public class Person {
	private long id;
	private String login;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String fName;
	
	@NotEmpty
	private String lName;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull @Past
	private Date dob;
	
	@NotEmpty
	private String phone;
	
	@NotEmpty
	private String address;

	public Person() {
		super();
	}

	public Person(long id, String login) {
		super();
		this.id = id;
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
