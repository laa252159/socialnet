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
		String sql = "INSERT INTO messages (senderid, receiverid, messagedate, value)"
				+ " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, message.getSenderId(),
				message.getReceiverId(), new Timestamp(message.getMessageDate()
						.getTime()), message.getValue());
	}

	@Override
	public void update(long messageId, String newValue) {
		String sql = "UPDATE messages SET " + "value = ? where id = ?";
		jdbcTemplate.update(sql, newValue, messageId);

	}

	@Override
	public void delete(long mesageId) {
		String sql = "DELETE FROM messages WHERE id = ?";
		jdbcTemplate.update(sql, mesageId);
	}

	@Override
	public Message get(long messageId) {
		String sql = "SELECT * FROM messages WHERE id = ?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Message>() {

			@Override
			public Message extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Message message = new Message();
					message.setId(rs.getLong("id"));
					message.setMessageDate(rs.getDate("messagedate"));
					message.setReceiverId(rs.getLong("receiverId"));
					message.setSenderId(rs.getLong("senderId"));
					message.setValue(rs.getString("value"));
					return message;
				}

				return null;
			}

		}, messageId);
	}

	@Override
	public List<Message> getAllMessagesBetweenPersons(long senderId,
			long receiverId) {
		String sql = "SELECT DISTINCT ON (m) m.id, m.messagedate, p.fn, m.senderid, m.receiverid, m.value FROM messages as m inner "
				+ "join persons as p "
				+ " on (m.senderid = p.id)"
				+ " where"
				+ " ((m.senderid = ? and m.receiverid = ?)"
				+ " or (m.senderid = ? and m.receiverid = ?)) order by m DESC;";
		List<Message> listMessage = jdbcTemplate.query(sql,
				new RowMapper<Message>() {

					@Override
					public Message mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Message message = new Message();
						message.setId(rs.getLong("id"));
						message.setMessageDate(rs.getDate("messagedate"));
						message.setReceiverId(rs.getLong("receiverid"));
						message.setSenderId(rs.getLong("senderid"));
						message.setValue(rs.getString("value"));
						message.setSenderName(rs.getString("fn"));
						return message;
					}
				}, senderId, receiverId, receiverId, senderId);

		return listMessage;
	}

	@Override
	public void setAllMessagesForReceiverFromSenderToReaded(long senderId,
			long receiverId) {
		String sql = "UPDATE messages as m SET has_been_read = true where m.receiverid = ? and m.senderid = ? and m.has_been_read = false";
		jdbcTemplate.update(sql, receiverId, senderId);
	}

	@Override
	public List<Message> getUnreadedLinksForreceiver(String receiverId) {
		String sql = "select DISTINCT ON (m.senderid) m.senderid, m.id, m.messagedate, m.receiverid, m.value,  m.has_been_read, p.fn, p.ln  from messages as m "
				+ "join persons as p on (p.id = m.senderid) "
				+ "where m.has_been_read = false and receiverid = ?";
		List<Message> listMessage = jdbcTemplate.query(sql,
				new RowMapper<Message>() {

					@Override
					public Message mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Message message = new Message();
						message.setReceiverId(rs.getLong("receiverid"));
						message.setSenderId(rs.getLong("senderid"));
						message.setSenderName(rs.getString("fn") + " "
								+ rs.getString("ln"));
						return message;
					}
				}, Long.parseLong(receiverId));

		return listMessage;
	}

}
