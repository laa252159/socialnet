package com.epam.socialnet.test;

import java.util.Date;
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
	public void getPhotosForAlbum() {
		List<Photo> photos = galleryService.getPhotosForAlbum(2L);
		for(Photo p : photos){
			System.out.println("Photo name: " + p.getName());
		}
	}

//	@Test
	public void createAlbum() {
		Album album = new Album();
		album.setDescription("Ѕыло очень круто.");
		album.setName("‘ото с —очи");
		album.setPersonId(2L);
		galleryService.createAlbum(album);

	}
	
//	@Test
	public void getAlbumByIdandUpdate() {
		Album album = galleryService.getAlbumById(2L);
		System.out.println("Old album name was " + album.getName());
		album.setName("Cool album " + Math.random());
		galleryService.updateAlbum(album);
		
		System.out.println("New album name is " + galleryService.getAlbumById(2l).getName());

	}
	
//	@Test
	public void deleteAlbum() {
		galleryService.deleteAlbum(2L);

	}

//	@Test
	public void createPhoto() {
		Photo photo = new Photo();
		photo.setAlbumId(2L);
		photo.setDescription("Bla bla bla");
		photo.setFileName("pic.jpg");
		photo.setName("My best photo");
		photo.setUploadDate(new Date());
		galleryService.createPhoto(photo);

	}

//	@Test
	public void getAndUpdatePhoto() {
		Photo photo = galleryService.getPhotoById(3L);
		photo.setDescription("Tra lala");
		galleryService.updatePhoto(photo);

	}

//	@Test
	public void deletePhoto() {
		galleryService.deletePhoto(3L);

	}

	@Test
	public void getImgAndPreView() {
		if(galleryService.getImg(5L)!=null){
			System.out.println("IMG exists " + galleryService.getImg(5L).length);
		} else {
			System.out.println("Empty");
		}
	}
}
