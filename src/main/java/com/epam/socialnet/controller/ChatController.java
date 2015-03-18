package com.epam.socialnet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController extends MainUtilController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(ChatController.class);

/*	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public @ResponseBody List<Message> getMessagesJSON(
			@RequestBody Message message) {
		// logger.info("...");
		List<Message> messages = new ArrayList<Message>();
		messages.add(message);
		messages.add(new Message("Hello World!", 2, 1, new Date()));
		return messages;
	}*/
	
	@RequestMapping(value = "addMessage", method = RequestMethod.GET)
	public @ResponseBody String getMessagesHTML(HttpServletRequest request) {
		
		StringBuilder html = new StringBuilder();
	
		html.append("Sender ID: " + request.getParameter("senderId") + " ReceiverID: " + request.getParameter("receiverId"));
	 
		return html.toString();
	}

}
