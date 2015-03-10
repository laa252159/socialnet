package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.epam.socialnet.model.Friendship;

public class FriendshipDAOImpl implements FriendshipDAO {

    private JdbcTemplate jdbcTemplate;

    public FriendshipDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Friendship friendship) {
/*        if (friendship.getId() > 0) {
            // update
            String sql = "UPDATE \"FRIENDSHIP\" SET "
                    + "login = ?, "
                    + "password = ?, "
                    + "fn = ?, "
                    + "ln = ?, "
                    + "dob = ?, "
                    + "phone = ?, "
                    + "address = ? "
                    + " WHERE id = ?";
            jdbcTemplate.update(sql,
                    friendship.getLogin(),
                    friendship.getPassword(),
                    friendship.getfName(),
                    friendship.getlName(),
                    friendship.getDob(),
                    friendship.getPhone(),
                    friendship.getAddress(),
                    friendship.getId());
        } else {*/
            // insert
            String sql = "INSERT INTO \"FRIENDSHIP\" (login, password, fn, ln, dob, phone, address)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    friendship.getFirsPersonId(),
                    friendship.getSecondPersonId(),
                    friendship.getDateOfFriendshipStarting());
        }
//    }

    @Override
    public void delete(long friendshipId) {
        String sql = "DELETE FROM \"FRIENDSHIP\" WHERE id = ?";
        jdbcTemplate.update(sql, friendshipId);

    }

    @Override
    public Friendship get(long friendshipId) {
        String sql = "SELECT * FROM \"FRIENDSHIP\" WHERE id=" + friendshipId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Friendship>() {

            @Override
            public Friendship extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    Friendship friendship = new Friendship();
                    friendship.setFirsPersonId(rs.getLong("first_person_id"));
                    friendship.setSecondPersonId(rs.getLong("second_person_id"));
                    friendship.setDateOfFriendshipStarting(rs.getDate("date_of_friendship_starting"));
                    friendship.setFriendshipApproved(rs.getBoolean("approve"));
                    return friendship;
                }

                return null;
            }

        });
    }

    @Override
    public List<Friendship> list() {
        String sql = "SELECT * FROM \"FRIENDSHIP\"";
        List<Friendship> listFriendship = jdbcTemplate.query(sql, new RowMapper<Friendship>() {

            @Override
            public Friendship mapRow(ResultSet rs, int rowNum) throws SQLException {
                Friendship friendship = new Friendship();
                friendship.setFirsPersonId(rs.getLong("first_person_id"));
                friendship.setSecondPersonId(rs.getLong("second_person_id"));
                friendship.setDateOfFriendshipStarting(rs.getDate("date_of_friendship_starting"));
                friendship.setFriendshipApproved(rs.getBoolean("approve"));
                return friendship;
            }

        });

        return listFriendship;
    }

	@Override
	public boolean areFriends(String firsPersonId, String secondPersonId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addFriendship(String firsPersonId, String secondPersonId) {
		// TODO Auto-generated method stub
		
	}
}
