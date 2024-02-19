package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.dto.ChatDto;
import com.gg.gop.service.ChatService;

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
