package net.codejava.spring.model;

public class Person {
	private long id;
	private String login;
	private String password;

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

}
