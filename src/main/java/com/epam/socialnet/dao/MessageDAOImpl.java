package com.epam.socialnet.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.epam.socialnet.model.Message;

public class MessageDAOImpl implements MessageDAO {

	private JdbcTemplate jdbcTemplate;

	public MessageDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public Message getBySenderAndReceiver(String login) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void add(Message message) {
		// TODO COMPLITE!!!
/*		String sql = "INSERT INTO \"FRIENDSHIP\" (first_person_id, second_person_id, date_of_message_starting)"
				+ " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, message.getFirsPersonId(),
				message.getSecondPersonId(),
				message.getDateOfMessageStarting());*/
	}
		


	@Override
	public void update(Message message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(long mesageId) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Message get(long mesageId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public void add(Message message) {
		String sql = "INSERT INTO \"FRIENDSHIP\" (first_person_id, second_person_id, date_of_message_starting)"
				+ " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, message.getFirsPersonId(),
				message.getSecondPersonId(),
				message.getDateOfMessageStarting());
	}

	@Override
	public void delete(Message message) {
		String sql = "DELETE FROM \"FRIENDSHIP\" WHERE (first_person_id = ? and second_person_id = ?) or (second_person_id = ? and first_person_id = ?)";
		jdbcTemplate.update(sql, message.getFirsPersonId(), message.getSecondPersonId(),message.getFirsPersonId(), message.getSecondPersonId());
	}

	@Override
    public List<Message> get(long firsPersonId, long secondPersonId) {
        String sql = "SELECT * FROM \"FRIENDSHIP\" "
        		+ "where"
        		+ " (first_person_id = "+firsPersonId+" and second_person_id = " + secondPersonId + ") "
        				+ "or"
        				+ " (first_person_id = " + secondPersonId + " and second_person_id = " +firsPersonId+ ")";
        List<Message> listMessage = jdbcTemplate.query(sql,
				new RowMapper<Message>() {

					@Override
					public Message mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Message message = new Message();
						message.setFirsPersonId(rs
								.getLong("first_person_id"));
						message.setSecondPersonId(rs
								.getLong("second_person_id"));
						message.setDateOfMessageStarting(rs
								.getDate("date_of_message_starting"));
						message.setMessageApproved(rs
								.getBoolean("approve"));
						return message;
					}
				});

		return listMessage;
    }

	@Override
	public void update(Message message) {
		 String sql = "UPDATE \"FRIENDSHIP\" SET "
                 + "approve = ?, date_of_message_starting = ? where first_person_id = ? and second_person_id = ?";
         jdbcTemplate.update(sql,
        		 message.isMessageApproved(),
        		 message.getDateOfMessageStarting(),
        		 message.getFirsPersonId(),
        		 message.getSecondPersonId());
	}*/
}
