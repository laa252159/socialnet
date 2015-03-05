package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.epam.socialnet.model.Person;

public class PersonDAOImpl implements PersonDAO {

	private JdbcTemplate jdbcTemplate;
	
	public PersonDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Person person) {
		if (person.getId() > 0) {
			// update
			
//			private long id;
//			private String login;
//			private String password;
//			private String fName;
//			private String lName;
//			private Date dob;
//			private String phone;
//			private String address;
			
			String sql = "UPDATE \"PERSONS\" SET "
					+ "login = ?, "
					+ "password = ?, "
					+ "fn = ?, "
					+ "ln = ?, "
					+ "dob = ?, "
					+ "phone = ?, "
					+ "address = ? "
					+ " WHERE id = ?";
			jdbcTemplate.update(sql, 
					person.getLogin(),
					person.getPassword(),
					person.getfName(),
					person.getlName(),
					person.getDob(),
					person.getPhone(),
					person.getAddress(),
					person.getId());
		} else {
			// insert
			String sql = "INSERT INTO \"PERSONS\" (login, password, fn, ln, dob, phone, address)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, 
					person.getLogin(),
					person.getPassword(),
					person.getfName(),
					person.getlName(),
					person.getDob(),
					person.getPhone(),
					person.getAddress());	
		}
	}

	@Override
	public void delete(long personId) {
		String sql = "DELETE FROM \"PERSONS\" WHERE id = ?";
		jdbcTemplate.update(sql, personId);
		
	}

	@Override
	public Person get(long personId) {
		String sql = "SELECT * FROM \"PERSONS\" WHERE id=" + personId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Person>() {

			@Override
			public Person extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Person person = new Person();
					person.setId(rs.getLong("id"));
					person.setLogin(rs.getString("login"));
					person.setPassword(rs.getString("password"));
					person.setfName(rs.getString("fn"));
					person.setlName(rs.getString("ln"));
					person.setPhone(rs.getString("phone"));
					person.setAddress(rs.getString("address"));
					person.setDob(rs.getDate("dob"));
					return person;
				}
				
				return null;
			}
			
		});
	}

	@Override
	public List<Person> list() {
		String sql = "SELECT * FROM \"PERSONS\"";
		List<Person> listPerson = jdbcTemplate.query(sql, new RowMapper<Person>() {

			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				Person aPerson = new Person();
	
				aPerson.setId(rs.getLong("id"));
				aPerson.setLogin(rs.getString("login"));
				aPerson.setPassword(rs.getString("password"));
				
				return aPerson;
			}
			
		});
		
		return listPerson;
	}
}
