package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.model.Album;
import com.epam.socialnet.model.Photo;

public interface GalleryService {
	
	public List<Album> getAlbumsForPerson(Long personId);
	
	public List<Photo> getPhotosForAlbum(Long albumId);
	
	public byte[] getImgForAlbum(Long albumId);
	
	public void createOrUpdateAlbum(Album album);
	
	public void deleteAlbum(Long albumId);
	
	public void createOrUpdatePhoto(Photo photo);
	
	public void deletePhoto(Long photoId);
	
	byte[] getImg(Long photoId);
	
	byte[] getImgPreView(Long photoId);
	
	public Album getAlbumById(Long albumId);
	
	public Photo getPhotoById(Long photoId);
	
	public void setPhoto(String id, byte[] img);
	
}
