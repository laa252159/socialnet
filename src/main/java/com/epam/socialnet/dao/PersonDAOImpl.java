package com.epam.socialnet.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.epam.socialnet.model.Person;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

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


}
