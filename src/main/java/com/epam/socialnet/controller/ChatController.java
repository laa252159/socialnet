package com.epam.socialnet.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.socialnet.model.Message;

@Controller
public class ChatController extends MainUtilController {

	@RequestMapping(value = "getAllMessages", method = RequestMethod.GET)
	public @ResponseBody String getMessagesHTML(HttpServletRequest request) {

		StringBuilder html = new StringBuilder();

		html.append("Sender ID: " + request.getParameter("senderId")
				+ " ReceiverID: " + request.getParameter("receiverId"));

		return html.toString();
	}

	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public void addMessage(HttpServletRequest request) {
		messageService.add(new Message(request.getParameter("value"), Long
				.parseLong(request.getParameter("senderId")), Long
				.parseLong(request.getParameter("receiverId")), new Date()));
		
		System.out.println("value: " + request.getParameter("value")
				+ "senderId: " + request.getParameter("senderId")
				+ "receiverId: " + request.getParameter("receiverId"));

	}

}
