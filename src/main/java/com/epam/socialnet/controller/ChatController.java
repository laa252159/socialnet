package com.epam.socialnet.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.socialnet.model.Message;

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
	
	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public @ResponseBody String getMessagesHTML(
			@RequestBody Message message) {
		
		StringBuilder html = new StringBuilder();
		html.append("<ul>");
		html.append("<li>");
		html.append("Hello Worlsd");
		html.append("</li>");
		html.append("</ul>");
	
		return html.toString();
	}

}
