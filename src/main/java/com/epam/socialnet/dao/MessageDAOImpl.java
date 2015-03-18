package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

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
		String sql = "INSERT INTO \"MESSAGES\" (\"senderId\", \"receiverId\", \"messageDate\", \"value\")"
				+ " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, message.getSenderId(),
				message.getReceiverId(), new Timestamp(message.getMessageDate()
						.getTime()), message.getValue());
	}

	@Override
	public void update(long messageId, String newValue) {
		String sql = "UPDATE \"MESSAGES\" SET " + "value = ? where id = ?";
		jdbcTemplate.update(sql, newValue, messageId);

	}

	@Override
	public void delete(long mesageId) {
		String sql = "DELETE FROM \"MESSAGES\" WHERE id = ?";
		jdbcTemplate.update(sql, mesageId);
	}

	@Override
	public Message get(long messageId) {
		String sql = "SELECT * FROM \"MESSAGES\" WHERE id=" + messageId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Message>() {

			@Override
			public Message extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Message message = new Message();
					message.setId(rs.getLong("id"));
					message.setMessageDate(rs.getDate("messageDate"));
					message.setReceiverId(rs.getLong("receiverId"));
					message.setSenderId(rs.getLong("senderId"));
					message.setValue(rs.getString("value"));
					return message;
				}

				return null;
			}

		});
	}

	@Override
	public List<Message> getAllMessagesBetweenPersons(long senderId,
			long receiverId) {
		String sql = "SELECT * FROM \"MESSAGES\" " + "where"
				+ " (\"senderId\" =  " + senderId + " and \"receiverId\" = " + receiverId + ")"
						+ " or "
						+ "(\"senderId\" = " + receiverId + " and \"receiverId\" = " + senderId + ") "
						+ "order by id ASC";
		List<Message> listMessage = jdbcTemplate.query(sql,
				new RowMapper<Message>() {

					@Override
					public Message mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Message message = new Message();
						message.setId(rs.getLong("id"));
						message.setMessageDate(rs.getDate("messageDate"));
						message.setReceiverId(rs.getLong("receiverId"));
						message.setSenderId(rs.getLong("senderId"));
						message.setValue(rs.getString("value"));
						return message;
					}
				});

		return listMessage;
	}
}
