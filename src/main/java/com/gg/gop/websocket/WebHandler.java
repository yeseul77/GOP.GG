package com.gg.gop.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.gg.gop.dto.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload=message.getPayload();
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
			log.info("pay: {}", payload);
			sendToEachSocket(sessions, message);
		}
	}
	private void sendToEachSocket(List<WebSocketSession> sessions, TextMessage message) throws Exception{
		String payload=message.getPayload();
		ChatMessage chatMessage=objectMapper.readValue(payload, ChatMessage.class);
		String chatId=chatMessage.getRoomId();
		ModelAndView modelAndView = new ModelAndView();
		ModelAndView chatroomId=modelAndView.addObject("chatroomId");
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
