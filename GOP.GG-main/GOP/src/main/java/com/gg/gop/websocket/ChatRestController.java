package com.gg.gop.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRestController {

	@Autowired
	ChatService cSer;
	
	@GetMapping("/chatroom/list")
	public List<ChatDto> list(){
		List<ChatDto> clist=cSer.roomlist();
		return clist;
	}
}
