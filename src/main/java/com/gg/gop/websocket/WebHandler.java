package com.gg.gop.websocket;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gop.dto.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebHandler extends TextWebSocketHandler{
	private final ObjectMapper objectMapper;
	private final List<WebSocketSession> sessions=new ArrayList<WebSocketSession>(); //= ConcurrentHashMap.newKeySet();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//		// Spring Security 세션에서 인증 정보 가져오기
//		String httpSessionId = (String) session.getAttributes().get("HTTPSESSIONID");
//        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        log.info(username);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDetails principal = (UserDetails) o.getAuthentication().getPrincipal();
//		SecurityContext securityContext = (SecurityContext) session.getAttributes().get("SPRING_SECURITY_CONTEXT");
//        SecurityContextImpl o = (SecurityContextImpl) session.getAttributes().get("SPRING_SECURITY_CONTEXT");
//        UserDetails principal = (UserDetails) o.getAuthentication().getPrincipal();
//        log.info("username = {}" , principal.getUsername());
        
//		Principal principal = null;
		log.info("========{}",session.getPrincipal());
//		log.info("{}",session);
		String payload=message.getPayload();
		log.info(message.getClass().getName());
		ChatMessage chatMessage=objectMapper.readValue(payload, ChatMessage.class);
		log.info("handler");
		if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getSender()+"입장");
			sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
		}else if(chatMessage.getType().equals(ChatMessage.MessageType.QUIT)){
			sessions.remove(session);
			chatMessage.setMessage(chatMessage.getSender()+"퇴장");
			sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessage)));
		}else {
			log.info("pay: {}", message);
			log.info("{}",payload);
			sendToEachSocket(sessions, message);
		}
	}
	private void sendToEachSocket(List<WebSocketSession> sessions, TextMessage message) throws Exception{
		String payload=message.getPayload();
		ChatMessage chatMessage=objectMapper.readValue(payload, ChatMessage.class);
//		String chatId=chatMessage.getRoomId();
//		ModelAndView modelAndView = new ModelAndView();
//		ModelAndView chatroomId=modelAndView.addObject("chatroomId");
//		log.info(chatroomId.toString());
		sessions.parallelStream().forEach(roomSession->{
//			if(chatId.equals(messageId)) {
				try {
					roomSession.sendMessage(message);
				}catch(IOException e) {
					throw new RuntimeException(e);
				}
//			}
		});
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}


}
