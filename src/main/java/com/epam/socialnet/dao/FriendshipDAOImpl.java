package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.epam.socialnet.model.Friendship;

public class FriendshipDAOImpl implements FriendshipDAO {

	private JdbcTemplate jdbcTemplate;

	public FriendshipDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void add(Friendship friendship) {
		String sql = "INSERT INTO \"FRIENDSHIP\" (first_person_id, second_person_id, date_of_friendship_starting)"
				+ " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, friendship.getFirsPersonId(),
				friendship.getSecondPersonId(),
				friendship.getDateOfFriendshipStarting());
	}

	@Override
	public void delete(Friendship friendship) {
		String sql = "DELETE FROM \"FRIENDSHIP\" WHERE first_person_id = ? and second_person_id = ?";
		jdbcTemplate.update(sql, friendship.getFirsPersonId(), friendship.getSecondPersonId());
	}

	@Override
    public List<Friendship> get(long firsPersonId, long secondPersonId) {
        String sql = "SELECT * FROM \"FRIENDSHIP\" "
        		+ "where"
        		+ " (first_person_id = "+firsPersonId+" and second_person_id = " + secondPersonId + ") "
        				+ "or"
        				+ " (first_person_id = " + secondPersonId + " and second_person_id = " +secondPersonId+ ")";
        List<Friendship> listFriendship = jdbcTemplate.query(sql,
				new RowMapper<Friendship>() {

					@Override
					public Friendship mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Friendship friendship = new Friendship();
						friendship.setFirsPersonId(rs
								.getLong("first_person_id"));
						friendship.setSecondPersonId(rs
								.getLong("second_person_id"));
						friendship.setDateOfFriendshipStarting(rs
								.getDate("date_of_friendship_starting"));
						friendship.setFriendshipApproved(rs
								.getBoolean("approve"));
						return friendship;
					}
				});

		return listFriendship;
    }

	@Override
	public void update(Friendship friendship) {
		 String sql = "UPDATE \"FRIENDSHIP\" SET "
                 + "approve = ?, date_of_friendship_starting = ? where first_person_id = ? and second_person_id = ?";
         jdbcTemplate.update(sql,
        		 friendship.isFriendshipApproved(),
        		 friendship.getDateOfFriendshipStarting(),
        		 friendship.getFirsPersonId(),
        		 friendship.getSecondPersonId());
	}
}
