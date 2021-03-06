package com.epam.socialnet.dao;

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
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import com.epam.socialnet.model.Photo;

public class PhotoDAOImpl implements PhotoDAO {

	private JdbcTemplate jdbcTemplate;

	public PhotoDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createOrUpdatePhoto(Photo photo) {
		if(photo.getId() !=null && photo.getId() > 0){
			String sql = "UPDATE photos SET " + "album_id = ?, description = ?, name = ? where id = ?";
			jdbcTemplate.update(sql, photo.getAlbumId(), photo.getDescription(), photo.getName(), photo.getId());
		} else {
			String sql = "INSERT INTO photos (album_id, file_name, name, description, upload_date)"
					+ " VALUES (?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, photo.getAlbumId(), photo.getFileName(), photo.getName(), photo.getDescription(), new Date());
		}
	}

	@Override
	public void deletePhoto(Long photoId) {
		String sql = "DELETE FROM photos WHERE id = ?";
		jdbcTemplate.update(sql, photoId);

	}

	@Override
	public List<Photo> getPhotosForAlbum(Long albumId) {
		String sql = "SELECT * FROM photos as p WHERE p.album_id = ? order by p.id asc";
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
						photo.setUploadDate(rs.getDate("upload_date"));
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
                return null;
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
					photo.setUploadDate(rs.getDate("upload_date"));
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

	@Override
	public byte[] getImgForAlbum(Long albumId) {
		 String sql = "SELECT * FROM photos as p WHERE p.album_id = ? order by p.id asc";
	        return jdbcTemplate.query(sql, new ResultSetExtractor<byte[]>() {

	            @Override
	            public byte[] extractData(ResultSet rs) throws SQLException,
	                    DataAccessException {
	                if (rs.next()) {
	                    return rs.getBytes("img_preview");
	                }
	                return new byte[0];
	            }

	        }, albumId);
	}

	@Override
	public void setPhoto(String id, byte[] img) {
        String sql = "UPDATE photos SET "
                + "img = ? "
                + " WHERE id = ?";
        LobHandler lobHandler = new DefaultLobHandler();
        jdbcTemplate.update(sql,
                new Object[]{
                        new SqlLobValue(img, lobHandler), id
                },
                new int[]{Types.BLOB, Types.BIGINT});
	}

	@Override
	public void setPhotoPreview(String id, byte[] img) {
		 String sql = "UPDATE photos SET "
	                + "img_preview = ? "
	                + " WHERE id = ?";
	        LobHandler lobHandler = new DefaultLobHandler();
	        jdbcTemplate.update(sql,
	                new Object[]{
	                        new SqlLobValue(img, lobHandler), id
	                },
	                new int[]{Types.BLOB, Types.BIGINT});
	}

}
