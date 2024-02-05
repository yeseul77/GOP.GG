package com.gg.gop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gg.gop.dto.ChatDto;
import com.gg.gop.service.ChatService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {
	private final ChatService chatService;
	@GetMapping("/duo_matching/chatList")
	public String chatList(Model model, HttpSession session) {
		String username=session.getAttribute("member2").toString();
//		List<ChatRoom> roomList=chatService.findAllRoom();
		List<ChatDto> roomList=new ArrayList<ChatDto>();
		roomList=chatService.roomlist();
//		log.info(""+roomList.size());
//		log.info(roomList.get(roomList.size()-1).getTitle());
		model.addAttribute("roomList",roomList);
		model.addAttribute("username",username);
		return"chatting/chatList";
	}
	
	@PostMapping("/chat/createRoom")
	public String createRoom(Model model, @RequestParam(name="name") String name, HttpSession session) {
		String username=session.getAttribute("member1").toString();
		log.info(username);
//		ChatRoom room=chatService.createRoom(name);
		String title=chatService.createRoom(name, username);
		model.addAttribute("room",title);
//		log.info("{}",room.getRoomId());
		model.addAttribute("username",username);
		return "chatting/chatroom";
	}
	
	@GetMapping("/chat/chatroom")
	public String chatRoom(Model model,@RequestParam(name="chatroomId") Object chatroomId, HttpSession session) {
		log.info("test");
//		ChatRoom room=chatService.findRoomById(chatroomId);
		log.info(""+chatroomId);
//		ChatRoom room=chatService.findRoomById(chatroomId);
		String username=session.getAttribute("member2").toString();	
		model.addAttribute("username", username);
		model.addAttribute("chatroomId",chatroomId);
		return "chatting/chatroom"; 	
	}
	
}
