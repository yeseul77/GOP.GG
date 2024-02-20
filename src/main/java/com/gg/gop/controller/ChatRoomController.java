package com.gg.gop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gg.gop.service.ChatService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {
	private final ChatService chatService;

	@PreAuthorize("isAuthenticated()")
//	@Secured("ROLE_ADMIN")
	@GetMapping("/chat/chatList")
	public String chatList(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		Object username= userDetails.getUsername();
		model.addAttribute("username",username);
		return"chat/chatList";
	}
	
	@PostMapping("/chat/createRoom")
	public String createRoom(@RequestParam(name="name") String name,@RequestParam(name="champion") String champ
							, @RequestParam(name="position") String position,@RequestParam(name="memo") String memo 
							,Model model, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		Object username= userDetails.getUsername();
		log.info(userDetails.getUsername());
		log.info("{}",username);
		Object title=chatService.createRoom(name, username, champ, position, memo);
		model.addAttribute("room",title);
		model.addAttribute("username",username);
		return "redirect:/chat/chatList";
	}
	
	@GetMapping("/chat/firstchatroom")
	public String chatRoom(Model model,@RequestParam(name="chatroomId") Object chatroomId, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		String username= userDetails.getUsername().toString();
		Boolean result=chatService.findAllRoom(Integer.parseInt((String) chatroomId),username);
		if(!result) {
			model.addAttribute("alert","진입실패");
			return "redirect:/chat/chatList";
		}
		log.info("test");
		log.info(""+chatroomId);
		model.addAttribute("username", username);
		model.addAttribute("chatroomId",chatroomId);
		return "chat/chatroom";
	}
	@GetMapping("/chat/chatroom")
	public String secondchatRoom(Model model,@RequestParam(name="chatroomId") Object chatroomId, HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal;
		String username= userDetails.getUsername().toString();
//		Boolean result=chatService.findAllRoom(Integer.parseInt((String) chatroomId),username);
//		if(!result) {
//			model.addAttribute("alert","진입실패");
//			return "redirect:/chat/chatList";
//		}
		log.info("test");
		log.info(""+chatroomId);
		model.addAttribute("username", username);
		model.addAttribute("chatroomId",chatroomId);
		return "chat/chatroom";
	}
	
}