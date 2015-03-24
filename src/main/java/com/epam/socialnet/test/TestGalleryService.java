package com.epam.socialnet.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socialnet.model.Album;
import com.epam.socialnet.model.Photo;
import com.epam.socialnet.services.GalleryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/spring-security.xml" })
public class TestGalleryService {

	static Logger log = Logger.getLogger(TestGalleryService.class.getName());

	@Autowired
	private GalleryService galleryService;

//	@Test
	public List<Album> getAlbumsForPerson() {
		return galleryService.getAlbumsForPerson(2L);
	}

//	@Test
	public List<Photo> getPhotosForAlbum(Long albumId) {
		return galleryService.getPhotosForAlbum(albumId);
	}

	@Test
	public void createAlbum() {
		Album album = new Album();
		album.setDescription("���� ����� �����.");
		album.setName("���� � ����");
		album.setPersonId(2L);
		galleryService.createAlbum(album);

	}

//	@Test
	public void updateAlbum(Album album) {
		galleryService.updateAlbum(album);

	}

//	@Test
	public void deleteAlbum(Long albumId) {
		galleryService.deleteAlbum(albumId);

	}

//	@Test
	public void createPhoto(Photo photo) {
		galleryService.createPhoto(photo);

	}

//	@Test
	public void updatePhoto(Photo photo) {
		galleryService.updatePhoto(photo);

	}

//	@Test
	public void deletePhoto(Long photoId) {
		galleryService.deletePhoto(photoId);

	}

//	@Test
	public Photo readPhoto(Long photoId) {
		return galleryService.readPhoto(photoId);
	}

//	@Test
	public byte[] getImg(Long photoId) {
		return galleryService.getImg(photoId);
	}
}
