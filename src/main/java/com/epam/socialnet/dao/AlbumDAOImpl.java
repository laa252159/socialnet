package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.epam.socialnet.model.Album;

public class AlbumDAOImpl implements AlbumDAO {

	private JdbcTemplate jdbcTemplate;

	public AlbumDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createOrUpdateAlbum(Album album) {
		if (album.getId() != null && album.getId() > 0) {
			String sql = "UPDATE albums SET "
					+ "name = ?, description = ? where id = ?";
			jdbcTemplate.update(sql, album.getName(), album.getDescription(),
					album.getId());
		} else {
			String sql = "INSERT INTO albums (person_id, name, description)"
					+ " VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, album.getPersonId(), album.getName(),
					album.getDescription());
		}
	}

	@Override
	public void deleteAlbum(Long albumId) {
		String sql = "DELETE FROM albums WHERE id = ?";
		jdbcTemplate.update(sql, albumId);

	}

	@Override
	public List<Album> getAlbumsForPerson(Long personId) {
		String sql = "SELECT * FROM albums WHERE person_id = ?";
		List<Album> listAlbum = jdbcTemplate.query(sql, new RowMapper<Album>() {

			@Override
			public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
				Album album = new Album();
				album.setId(rs.getLong("id"));
				album.setPersonId(rs.getLong("person_id"));
				album.setName(rs.getString("name"));
				album.setDescription(rs.getString("description"));
				return album;
			}
		}, personId);

		return listAlbum;
	}

	@Override
	public Album getAlbumById(Long albumId) {
		String sql = "SELECT * FROM albums WHERE id = ?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Album>() {

			@Override
			public Album extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Album album = new Album();
					album.setId(rs.getLong("id"));
					album.setPersonId(rs.getLong("person_id"));
					album.setName(rs.getString("name"));
					album.setDescription(rs.getString("description"));
					return album;
				}
				return null;
			}
		}, albumId);

	}

}
