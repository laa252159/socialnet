package com.epam.socialnet.services;

import java.util.List;

import com.epam.socialnet.dao.AlbumDao;
import com.epam.socialnet.dao.PhotoDao;
import com.epam.socialnet.model.Album;
import com.epam.socialnet.model.Photo;

public class GalleryServiceImple implements GalleryService {

	private AlbumDao albumDao;

	private PhotoDao photoDao;

	public GalleryServiceImple(AlbumDao albumDao, PhotoDao photoDao) {
		super();
		this.albumDao = albumDao;
		this.photoDao = photoDao;
	}

	@Override
	public List<Album> getAlbumsForPerson(Long personId) {
		return albumDao.getAlbumsForPerson(personId);
	}

	@Override
	public List<Photo> getPhotosForAlbum(Long albumId) {
		return photoDao.getPhotosForAlbum(albumId);
	}

	@Override
	public void createAlbum(Album album) {
		albumDao.createAlbum(album);

	}

	@Override
	public void updateAlbum(Album album) {
		albumDao.updateAlbum(album);

	}

	@Override
	public void deleteAlbum(Long albumId) {
		albumDao.deleteAlbum(albumId);

	}

	@Override
	public void createPhoto(Photo photo) {
		photoDao.createPhoto(photo);

	}

	@Override
	public void updatePhoto(Photo photo) {
		photoDao.updatePhoto(photo);

	}

	@Override
	public void deletePhoto(Long photoId) {
		photoDao.deletePhoto(photoId);

	}

	@Override
	public Photo readPhoto(Long photoId) {
		photoDao.readPhoto(photoId);
		return null;
	}

	@Override
	public byte[] getImg(Long photoId) {
		return photoDao.getImg(photoId);
	}

}
