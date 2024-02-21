package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gg.gop.dto.ChatDto;
import com.gg.gop.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatRestController {

	@Autowired
	ChatService cSer;
	
	@GetMapping("/chatroom/list")
	public List<ChatDto> list(){
		List<ChatDto> clist=cSer.roomlist();
		return clist;
	}
	
	@PostMapping("/chatroom/chat")
	public ChatDto enterChatRoom(@RequestParam("t_id") int t_id,
								 @RequestParam("title") String title,
								 @RequestParam("owner") String owner) {
		log.info(owner);
		log.info(title);
		log.info("t_id :{}", t_id);
		
		ChatDto cr = new ChatDto();
		
		cr.setChatroomId(t_id);
		cr.setTitle(title);
		cr.setUserId(owner);
		
		return cr;
	}
//	
//	@PostMapping("/chat/deleteroom")
//	public void deleteRoom(@RequestParam("t_id") int t_id) {
//		log.info("chatroomId : {}", t_id);
//		cSer.deleteRoom(t_id);
//	}
}
