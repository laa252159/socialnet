package com.epam.socialnet.dao;

import java.util.List;

import com.epam.socialnet.model.Photo;

public interface PhotoDAO {

	public void createPhoto(Photo photo);

	public void updatePhoto(Photo photo);

	public void deletePhoto(Long photoId);

	public List<Photo> getPhotosForAlbum(Long albumId);
	
	byte[] getImg(Long id);
	
	byte[] getImgPreView(Long photoId);
	
	public Photo getPhotoById(Long photoId);

}
