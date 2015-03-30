package com.epam.socialnet.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;

import com.epam.socialnet.dao.AlbumDAO;
import com.epam.socialnet.dao.PhotoDAO;
import com.epam.socialnet.model.Album;
import com.epam.socialnet.model.Photo;

public class GalleryServiceImpl implements GalleryService {

	private AlbumDAO albumDao;

	private PhotoDAO photoDao;

	public GalleryServiceImpl(AlbumDAO albumDao, PhotoDAO photoDao) {
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
	public void deletePhoto(Long photoId) {
		photoDao.deletePhoto(photoId);

	}

	@Override
	public byte[] getImg(Long photoId) {
		return photoDao.getImg(photoId);
	}

	@Override
	public Album getAlbumById(Long albumId) {
		return albumDao.getAlbumById(albumId);

	}

	@Override
	public Photo getPhotoById(Long photoId) {
		return photoDao.getPhotoById(photoId);

	}

	@Override
	public byte[] getImgPreView(Long photoId) {
		return photoDao.getImgPreView(photoId);
	}

	@Override
	public void createOrUpdatePhoto(Photo photo) {
		photoDao.createOrUpdatePhoto(photo);

	}

	@Override
	public byte[] getImgForAlbum(Long albumId) {
		return photoDao.getImgForAlbum(albumId);
	}

	@Override
	public void setPhoto(String id, byte[] img) {
		photoDao.setPhoto(id, img);
		photoDao.setPhotoPreview(id, getPreview(img));
	}

	private byte[] getPreview(byte[] img) {
		BufferedImage imgage;
		try {
			imgage = ImageIO.read(new ByteArrayInputStream(img));
			BufferedImage scaledImg = Scalr.resize(imgage, Method.QUALITY,
					150, 150);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaledImg, "jpg", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0]; 
	}

}
