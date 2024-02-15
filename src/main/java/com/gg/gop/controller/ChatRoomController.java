package com.gg.gop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	//	@PreAuthorize("isAuthenticated()")
//	@Secured("ROLE_ADMIN")
	@GetMapping("/chat/chatList")
	public String chatList(Model model, HttpSession session, Principal test) {
		log.info("name={}",test.getName());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		log.info("{}",userDetails.getUsername());
		
//		String username=session.getAttribute("member2").toString();
//		List<ChatRoom> roomList=chatService.findAllRoom();
		List<ChatDto> roomList=new ArrayList<ChatDto>();
		roomList=chatService.roomlist();
		log.info("{}",session.getAttribute(userDetails.getUsername()));
//		log.info(""+roomList.size());
//		log.info(roomList.get(roomList.size()-1).getTitle());
		model.addAttribute("roomList",roomList);
//		model.addAttribute("username",username);
		return"chat/chatList";
	}
	
	@PostMapping("/chat/createRoom")
	public String createRoom(Model model, @RequestParam(name="name") String name, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		Object username= userDetails.getUsername();
//		String username=null;
		log.info(userDetails.getUsername());
		log.info("{}",username);
//		ChatRoom room=chatService.createRoom(name);
		Object title=chatService.createRoom(name, username);
		model.addAttribute("room",title);
//		log.info("{}",room.getRoomId());
		model.addAttribute("username",username);
		return "chat/chatroom";
	}
	
	@GetMapping("/chat/chatroom")
	public String chatRoom(Model model,@RequestParam(name="chatroomId") Object chatroomId, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		Object username= userDetails.getUsername();
		log.info("test");
//		ChatRoom room=chatService.findRoomById(chatroomId);
		log.info(""+chatroomId);
//		ChatRoom room=chatService.findRoomById(chatroomId);
//		String username=session.getAttribute("member2").toString();	
		model.addAttribute("username", username);
		model.addAttribute("chatroomId",chatroomId);
		return "chat/chatroom";
	}
	
}
