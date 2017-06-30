package com.example.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Message;

@RestController()
@RequestMapping("/messageservice")
public class MessageService {
	@RequestMapping(value = "/unreadMessageCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody int getUnreadMessageCount() {
		return (int) (Math.random() * 100);
	}
	
	@RequestMapping(value = "/getMessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody List<Message> getMessages(@RequestParam(name="numberItem", defaultValue = "5") int numberItem) {
		final List<Message> messages = new ArrayList<Message>();
		for (int i = 0; i < numberItem; i++) {
			messages.add(sampleMessage());
		} 
		return messages;
	}
	
	private Message sampleMessage(){
		Message msg = new Message();
		msg.setUuid((long) (Math.random() * Long.MAX_VALUE));		
		msg.setSenderFullName("Hello" + Math.random() );
		msg.setSubject("subject" + + Math.random() );
		msg.setSendDateInMillis(System.currentTimeMillis());
		msg.setSendDateFormat("Today");		
		return msg;
	};
	
}
