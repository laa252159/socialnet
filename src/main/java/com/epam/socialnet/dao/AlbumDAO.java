package com.epam.socialnet.dao;

import java.util.List;

import com.epam.socialnet.model.Album;

public interface AlbumDAO {

	public void createOrUpdateAlbum(Album album);

	public void deleteAlbum(Long albumId);
	
	public List<Album> getAlbumsForPerson(Long personId);
	
	public Album getAlbumById(Long albumId);

}
