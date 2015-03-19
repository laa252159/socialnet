package com.epam.socialnet.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.socialnet.model.Message;

@Controller
public class ChatController extends MainUtilController {
	
	private final static String SPACE = " ";
	private final static String COLON = ":";
	private final static String PARAGRAPH_START = "<p>";
	private final static String PARAGRAPH_END = "</p>";

	@RequestMapping(value = "getAllMessages", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	public @ResponseBody String getMessagesHTML(HttpServletRequest request) {

		return getHtmlFromMessagesList(messageService.getAllMessagesBetweenPersons(
				Long.parseLong(request.getParameter("senderId")),
				Long.parseLong(request.getParameter("receiverId"))));
	}

	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public void addMessage(HttpServletRequest request) {
		messageService.add(new Message(request.getParameter("value"), Long
				.parseLong(request.getParameter("senderId")), Long
				.parseLong(request.getParameter("receiverId")), new Date()));

		System.out.println("Value: " + request.getParameter("value")
				+ "  senderId: " + request.getParameter("senderId")
				+ "receiverId: " + request.getParameter("receiverId"));

	}

	private String getHtmlFromMessagesList(List<Message> msgs) {
		StringBuilder sb = new StringBuilder();
		for(Message message: msgs){
			sb.append(PARAGRAPH_START);
			sb.append(message.getSenderName());
			sb.append(COLON);
			sb.append(SPACE);
			sb.append(message.getValue());
			sb.append(PARAGRAPH_END);
		}
		return sb.toString();
	}

}
