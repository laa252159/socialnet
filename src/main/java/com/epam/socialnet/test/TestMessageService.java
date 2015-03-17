package com.epam.socialnet.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.socialnet.model.Message;
import com.epam.socialnet.services.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
		"file:src/main/webapp/WEB-INF/spring-security.xml" })
public class TestMessageService {

	@Autowired
	private MessageService messageService;

	@Test
	public void getFriends() {
		Message message = new Message("Hello World!", 1, 2, new Date());
		messageService.add(message);
		System.out.println("done");
	}

}
