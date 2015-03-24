package com.epam.socialnet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.epam.socialnet.model.Photo;

public class PhotoDAOImpl implements PhotoDAO {

	private JdbcTemplate jdbcTemplate;

	public PhotoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createPhoto(Photo photo) {
		String sql = "INSERT INTO photos (album_id, file_name, name, description, upload_date)"
				+ " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, photo.getAlbumId(), photo.getFileName(), photo.getName(), photo.getDescription(), photo.getUploadDate());

	}

	@Override
	public void updatePhoto(Photo photo) {
		String sql = "UPDATE photos SET " + "album_id = ?, description = ? where id = ?";
		jdbcTemplate.update(sql, photo.getAlbumId(), photo.getDescription(), photo.getId());

	}

	@Override
	public void deletePhoto(Long photoId) {
		String sql = "DELETE FROM photos WHERE id = ?";
		jdbcTemplate.update(sql, photoId);

	}

	@Override
	public List<Photo> getPhotosForAlbum(Long albumId) {
		String sql = "SELECT * FROM photos WHERE album_id = ?";
		List<Photo> listPhoto = jdbcTemplate.query(sql,
				new RowMapper<Photo>() {

					@Override
					public Photo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Photo photo = new Photo();
						photo.setId(rs.getLong("id"));
						photo.setAlbumId(rs.getLong("album_id"));
						photo.setFileName(rs.getString("file_name"));
						photo.setName(rs.getString("name"));
						photo.setDescription(rs.getString("description"));
						photo.setUploadDate(rs.getDate("uploadDate"));
						return photo;
					}
				}, albumId);

		return listPhoto;
	}

    @Override
    public byte[] getImg(Long photoId) {
        String sql = "SELECT * FROM photos WHERE id = ?";
        return jdbcTemplate.query(sql, new ResultSetExtractor<byte[]>() {

            @Override
            public byte[] extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    return rs.getBytes("img");
                }
                return new byte[0];
            }

        }, photoId);
    }

	@Override
	public Photo getPhotoById(Long photoId) {
		String sql = "SELECT * FROM photos WHERE id = ?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Photo>() {

			@Override
			public Photo extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Photo photo = new Photo();
					photo.setId(rs.getLong("id"));
					photo.setAlbumId(rs.getLong("album_id"));
					photo.setFileName(rs.getString("file_name"));
					photo.setName(rs.getString("name"));
					photo.setDescription(rs.getString("description"));
					photo.setUploadDate(rs.getDate("uploadDate"));
					photo.setImg(rs.getBytes("img"));
					return photo;
				}

				return null;
			}

		}, photoId);
		
	}

	@Override
	public byte[] getImgPreView(Long photoId) {
		   String sql = "SELECT * FROM photos WHERE id = ?";
	        return jdbcTemplate.query(sql, new ResultSetExtractor<byte[]>() {

	            @Override
	            public byte[] extractData(ResultSet rs) throws SQLException,
	                    DataAccessException {
	                if (rs.next()) {
	                    return rs.getBytes("img_preview");
	                }
	                return new byte[0];
	            }

	        }, photoId);
	}

}
