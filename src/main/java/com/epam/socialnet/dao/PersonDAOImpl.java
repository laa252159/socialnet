package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.epam.socialnet.dto.PersonDto;
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

        });

        return listPerson;
    }
    
	@Override
	public List<PersonDto> listDtos() {
		  String sql = "SELECT * FROM \"PERSONS\"";
	        List<PersonDto> listPersonDtos = jdbcTemplate.query(sql, new RowMapper<PersonDto>() {

	            @Override
	            public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	                PersonDto personDto = new PersonDto();

	                personDto.setId(rs.getLong("id"));
	                personDto.setfName(rs.getString("fn"));
	                personDto.setlName(rs.getString("ln"));
	                return personDto;
	            }
	        });
	        return listPersonDtos;
	}

    @Override
    public void setPhoto(String id, byte[] img) {

        // update
        String sql = "UPDATE \"PERSONS\" SET "
                + "photo = ? "
                + " WHERE id = ?";
        LobHandler lobHandler = new DefaultLobHandler();
        jdbcTemplate.update(sql,
                new Object[]{
                        new SqlLobValue(img, lobHandler), id
                },
                new int[]{Types.BLOB, Types.BIGINT});
    }

    @Override
    public byte[] getPhoto(String id) {
        String sql = "SELECT * FROM \"PERSONS\" WHERE id=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<byte[]>() {

            @Override
            public byte[] extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    return rs.getBytes("photo");
                }
                return new byte[0];
            }

        });
    }

	@Override
	public List<PersonDto> getFriendsDtos(String personId) {
		 String sql = "SELECT * FROM \"PERSONS\" as p "
			 		+ "WHERE"
			 		+ " (p.id IN (SELECT ff.second_person_id FROM \"FRIENDSHIP\" as ff where ff.first_person_id = " + personId + "))"
			 		+ " or"
			 		+ " (p.id IN (SELECT sf.first_person_id FROM \"FRIENDSHIP\" as sf where sf.second_person_id = " + personId + "));";
	        List<PersonDto> listPersonDtos = jdbcTemplate.query(sql, new RowMapper<PersonDto>() {

	            @Override
	            public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	                PersonDto personDto = new PersonDto();

	                personDto.setId(rs.getLong("id"));
	                personDto.setfName(rs.getString("fn"));
	                personDto.setlName(rs.getString("ln"));
	                return personDto;
	            }
	        });
	        return listPersonDtos;
	}

	@Override
	public List<Person> findPerson(Person person) {
		if(person.getfName() == null || person.getfName().isEmpty()){
			person.setfName("null");
		}
		if(person.getlName() == null || person.getlName().isEmpty()){
			person.setlName("null");
		}
		String sql = "SELECT * FROM \"PERSONS\" as p where p.fn like '%" + person.getfName() + "%' or p.ln like '%" + person.getlName() + "%'";
	        List<Person> listPerson = jdbcTemplate.query(sql, new RowMapper<Person>() {

	            @Override
	            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
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

	        });

	        return listPerson;
	}
	
	@Override
	public List<PersonDto> findPersonDto(Person person) {
		if(person.getfName() == null || person.getfName().isEmpty()){
			person.setfName("null");
		}
		if(person.getlName() == null || person.getlName().isEmpty()){
			person.setlName("null");
		}
		String sql = "SELECT * FROM \"PERSONS\" as p where p.fn like '%" + person.getfName() + "%' or p.ln like '%" + person.getlName() + "%'";
	        List<PersonDto> listPersonDto = jdbcTemplate.query(sql, new RowMapper<PersonDto>() {

	            @Override
	            public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	                PersonDto personDto = new PersonDto();

	                personDto.setId(rs.getLong("id"));
	                personDto.setfName(rs.getString("fn"));
	                personDto.setlName(rs.getString("ln"));

	                return personDto;
	            }

	        });

	        return listPersonDto;
	}

	@Override
	public boolean areFriends(long firstPersonId, long secondPersonId) {
		String sql = "SELECT * FROM \"FRIENDSHIP\" as f where (f.first_person_id='"+firstPersonId+"' and f.second_person_id='"+secondPersonId+"')"
				+ " or "
				+ "(f.first_person_id='"+secondPersonId+"' and f.second_person_id='"+firstPersonId+"');";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	    return rows !=null && !rows.isEmpty();
	}

	@Override
	public List<Person> getFriends(String personId) {
		 String sql = "SELECT * FROM \"PERSONS\" as p "
		 		+ "WHERE"
		 		+ " (p.id IN (SELECT ff.second_person_id FROM \"FRIENDSHIP\" as ff where ff.first_person_id = " + personId + "))"
		 		+ " or"
		 		+ " (p.id IN (SELECT sf.first_person_id FROM \"FRIENDSHIP\" as sf where sf.second_person_id = " + personId + "));";
	       return getPersons(sql, personId);
	}
	
	@Override
	public List<Person> getFriendsRequestedByPerson(String personId) { //TODO complit sql!!!
		String sql = "SELECT * FROM \"PERSONS\" as p "
		 		+ "WHERE"
		 		+ " (p.id IN (SELECT ff.second_person_id FROM \"FRIENDSHIP\" as ff where ff.first_person_id = " + personId + " and ff.approve = 'false'))";
	       return getPersons(sql, personId);
	}

	@Override
	public List<Person> getFriendsRequestedToPerson(String personId) {//TODO complit sql!!!
		String sql = "SELECT * FROM \"PERSONS\" as p "
		 		+ "WHERE"
		 		+ " (p.id IN (SELECT ff.first_person_id FROM \"FRIENDSHIP\" as ff where ff.second_person_id = " + personId + " and ff.approve = 'false'))";
	       return getPersons(sql, personId);
	}
	
	public List<Person> getPersons(String sql, String personId) {
		        List<Person> listPerson = jdbcTemplate.query(sql, new RowMapper<Person>() {

		            @Override
		            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
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

		        });

		        return listPerson;
	}
	
}
