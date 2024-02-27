//package com.gg.gop.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import com.gg.gop.dto.ChatDto;
//import com.gg.gop.dto.ChatMemberDto;
//import com.gg.gop.dto.ChatMessage;
//import com.gg.gop.service.ChatService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@Slf4j
//public class ChatRestController {
//
//	@Autowired
//	ChatService cSer;
//	
//	@GetMapping("/chatroom/list")
//	public List<ChatDto> list(){
//		List<ChatDto> clist=cSer.roomlist();
//		cSer.deleteRoom();
//		return clist;
//	}
//	@GetMapping("chatroom/mylist")
//	public List<ChatMemberDto> mylist(){
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
//		UserDetails userDetails = (UserDetails)principal;
//		Object username= userDetails.getUsername();
//		List<ChatMemberDto> mylist=cSer.mylist((String)username);
//		return mylist;
//	}
//	
//	@PostMapping("/chatroom/chat")
//	public ChatDto enterChatRoom(@RequestParam("t_id") int t_id,
//								 @RequestParam("title") String title,
//								 @RequestParam("owner") String owner) {
//		log.info(owner);
//		log.info(title);
//		log.info("t_id :{}", t_id);
//		
//		ChatDto cr = new ChatDto();
//		
//		cr.setChatroomId(t_id);
//		cr.setTitle(title);
//		cr.setUserId(owner);
//		
//		return cr;
//	}
//	@GetMapping("/chatroom/chatlist")
//	public List<ChatMessage> beforemsg(int chatroomId){
//		log.info("{}",chatroomId);
//		List<ChatMessage> message=cSer.beforeMsg(chatroomId);
//		log.info("msg{}",message);
//		return message;
//	}
//	@GetMapping("/chatroom/search")
//	public List<ChatDto> search(String title){
////		log.info("reststert");
//		log.info("============{}",title);
//		List<ChatDto> search=cSer.roomSearch(title);
//		return search;
//	}
////	@PostMapping("/chat/deleteroom")
////	public void deleteRoom(@RequestParam("t_id") int t_id) {
////		log.info("chatroomId : {}", t_id);
////		cSer.deleteRoom(t_id);
////	}
//}
