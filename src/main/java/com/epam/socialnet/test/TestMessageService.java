package com.epam.socialnet.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
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
	
	 static Logger log = Logger.getLogger(TestMessageService.class.getName());

	@Autowired
	private MessageService messageService;

//	@Test
	public void add() {
		Message message = new Message("Hello World!", 1, 2, new Date());
		messageService.add(message);
		System.out.println("done");
	}
	
//	@Test
	public void update() {
		messageService.update(8, "He he he !!!");
		System.out.println("done");
	}
	
//	@Test
	public void get() {
		Message message = messageService.get(146);
		System.out.println("Id: " + message.getId() +"  Value:" + message.getValue());
	}
	
//	@Test
	public void delete() {
		messageService.delete(8);
		System.out.println("done!");
	}
	
	
//	@Test
	public void getAllMessagesBetweenPersons() {
		List<Message> messages =  messageService.getAllMessagesBetweenPersons(2,33);
		for(Message m: messages){
			System.out.println("SenderName: "+ m.getSenderName() +"id: " + m.getId() + " val: " +  m.getValue());
		}
		
	}
	
	@Test
	public void getUnreadedLinksForreceiver(){
		List<Message> messages = messageService.getUnreadedLinksForreceiver(String.valueOf(2));
		for(Message m: messages){
			System.out.println("Name: " + m.getSenderName() + " sender id: " + m.getSenderId());
		}
	}

}
