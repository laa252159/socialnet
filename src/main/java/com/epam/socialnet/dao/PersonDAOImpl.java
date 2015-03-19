package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

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
            String sql = "UPDATE persons SET "
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
            String sql = "INSERT INTO persons (login, password, fn, ln, dob, phone, address)"
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
        String sql = "DELETE FROM persons WHERE id = ?";
        jdbcTemplate.update(sql, personId);

    }

    @Override
    public Person get(long personId) {
        String sql = "SELECT * FROM persons WHERE id= ?";
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

        }, personId);
    }

    @Override
    public List<Person> list() {
        String sql = "SELECT * FROM persons";
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
		  String sql = "SELECT * FROM persons";
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
        String sql = "UPDATE persons SET "
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
        String sql = "SELECT * FROM persons WHERE id = ?";
        return jdbcTemplate.query(sql, new ResultSetExtractor<byte[]>() {

            @Override
            public byte[] extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    return rs.getBytes("photo");
                }
                return new byte[0];
            }

        }, Long.parseLong(id));
    }

	@Override
	public List<PersonDto> getFriendsDtos(String personId) {
		 String sql = "SELECT * FROM persons as p "
			 		+ "WHERE"
			 		+ " (p.id IN (SELECT ff.second_person_id FROM friendship as ff where ff.first_person_id = ? and approve='true'))"
			 		+ " or"
			 		+ " (p.id IN (SELECT sf.first_person_id FROM friendship as sf where sf.second_person_id = ? and approve='true'));";
	        List<PersonDto> listPersonDtos = jdbcTemplate.query(sql, new RowMapper<PersonDto>() {

	            @Override
	            public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	                PersonDto personDto = new PersonDto();

	                personDto.setId(rs.getLong("id"));
	                personDto.setfName(rs.getString("fn"));
	                personDto.setlName(rs.getString("ln"));
	                return personDto;
	            }
	        }, Long.parseLong(personId), Long.parseLong(personId));
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
		String sql = "SELECT * FROM persons as p where p.fn like ? or p.ln like ?";
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

	        }, person.getfName(),person.getlName());

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
		String sql = "SELECT * FROM persons as p where p.fn like ? or p.ln like ?";
	        List<PersonDto> listPersonDto = jdbcTemplate.query(sql, new RowMapper<PersonDto>() {

	            @Override
	            public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	                PersonDto personDto = new PersonDto();

	                personDto.setId(rs.getLong("id"));
	                personDto.setfName(rs.getString("fn"));
	                personDto.setlName(rs.getString("ln"));

	                return personDto;
	            }

	        }, person.getfName(), person.getlName());

	        return listPersonDto;
	}

	@Override
	public List<Person> getFriends(String personId) {
		 String sql = "SELECT * FROM persons as p "
		 		+ "WHERE"
		 		+ " (p.id IN (SELECT ff.second_person_id FROM friendship as ff where ff.first_person_id = ? and approve='true'))"
		 		+ " or"
		 		+ " (p.id IN (SELECT sf.first_person_id FROM friendship as sf where sf.second_person_id = ? and approve='true'));";
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

	        }, Long.parseLong(personId), Long.parseLong(personId));

	        return listPerson;
	}
	
	@Override
	public List<Person> getFriendshipApprovers(String personId) { //TODO complit sql!!!
		String sql = "SELECT * FROM persons as p "
		 		+ "WHERE"
		 		+ " (p.id IN (SELECT ff.second_person_id FROM friendship as ff where ff.first_person_id = ? and ff.approve = 'false'))";
	       return getPersons(sql, personId);
	}

	@Override
	public List<Person> getFriendshipWaiters(String personId) {//TODO complit sql!!!
		String sql = "SELECT * FROM persons as p "
		 		+ "WHERE"
		 		+ " (p.id IN (SELECT ff.first_person_id FROM friendship as ff where ff.second_person_id = ? and ff.approve = 'false'))";
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

		        }, Long.parseLong(personId));

		        return listPerson;
	}

	@Override
	public Person getByLogin(String login) {
        String sql = "SELECT * FROM persons WHERE login = ?";
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

        }, login);
	}
	
}
