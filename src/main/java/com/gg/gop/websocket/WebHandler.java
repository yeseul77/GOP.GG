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
import com.gg.gop.dto.ChatDto;
import com.gg.gop.dto.ChatMessage;
import com.gg.gop.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebHandler extends TextWebSocketHandler{
	private final ObjectMapper objectMapper;
	private final List<WebSocketSession> sessions=new ArrayList<>();
	private final ChatService cSer;
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);		
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		List<WebSocketSession> roomSession=new ArrayList<>();
		String payload=message.getPayload();
		log.info(message.getClass().getName());
		ChatMessage chatMessage=objectMapper.readValue(payload, ChatMessage.class);
		int roomId=chatMessage.getRoomId();
		List<String> chatMember=cSer.findRoomMember(roomId);
		for(int i=0;i<chatMember.size();i++){
			for(int j=0;j<sessions.size();j++) {
				if(sessions.get(j).getPrincipal().getName().equals(chatMember.get(i))) {
					roomSession.add(sessions.get(j));
				};
			}
		}
		log.info("handler set complet");
		if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
			chatMessage.setMessage(session.getPrincipal().getName()+"입장");
			sendToEachSocket(roomSession, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
		}else if(chatMessage.getType().equals(ChatMessage.MessageType.QUIT)){
			sessions.remove(session);
			chatMessage.setMessage(chatMessage.getSender()+"퇴장");
			sendToEachSocket(roomSession,new TextMessage(objectMapper.writeValueAsString(chatMessage)));
		}else if(chatMessage.getType().equals(ChatMessage.MessageType.submit)) {
			List<WebSocketSession> hostSession=new ArrayList<>();
			ChatDto roomData=cSer.roomData(roomId);
			for(int i=0;i<sessions.size();i++) {
				if(sessions.get(i).getPrincipal().getName().equals(roomData.getUserId())) {
					hostSession.add(sessions.get(i));
					break;
				}
			sendToEachSocket(hostSession,message);
			}
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
			try {
				roomSession.sendMessage(message);
			}catch(IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
}
