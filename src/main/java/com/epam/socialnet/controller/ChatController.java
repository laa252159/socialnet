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

	@RequestMapping(value = "addMessage", method = RequestMethod.POST)
	public @ResponseBody List<Message> createEmployee(
			@RequestBody Message message) {
		// logger.info("...");
		List<Message> messages = new ArrayList<Message>();
		messages.add(message);
		messages.add(new Message("Hello World!", 2, 1, new Date()));
		return messages;
	}

}
