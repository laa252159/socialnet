package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.model.Album;
import com.epam.socialnet.model.Photo;

public interface GalleryService {
	
	public List<Album> getAlbumsForPerson(Long personId);
	
	public List<Photo> getPhotosForAlbum(Long albumId);
	
	public void createAlbum(Album album);
	
	public void updateAlbum(Album album);
	
	public void deleteAlbum(Long albumId);
	
	public void createPhoto(Photo photo);
	
	public void updatePhoto(Photo photo);
	
	public void deletePhoto(Long photoId);
	
	byte[] getImg(Long photoId);
	
	byte[] getImgPreView(Long photoId);
	
	public Album getAlbumById(Long albumId);
	
	public Photo getPhotoById(Long photoId);

}
