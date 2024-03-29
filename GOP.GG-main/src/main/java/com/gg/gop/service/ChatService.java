<<<<<<< HEAD:src/main/java/com/gg/gop/service/ChatService.java
package com.gg.gop.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gop.dao.ChatDao;
import com.gg.gop.dto.ChatDto;
import com.gg.gop.dto.ChatMemberDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
	
	private final ObjectMapper objectMapper;
//	private Map<String, ChatRoom> chatRooms;
	final ChatDao cDao;
//	@PostConstruct
//	private void init() {
//		chatRooms=new LinkedHashMap<>();
//	}
//	
//	public ChatRoom findRoomById(Object roomId) {
//		return chatRooms.get(roomId);
//	}
	
	public String createRoom(String title, Object username) {
//		String randomId=UUID.randomUUID().toString();
//		ChatRoom chatRoom=ChatRoom.builder()
//								  .roomId(randomId)
//								  .build();
//		chatRooms.put(randomId, chatRoom);
		log.info("{}",username);
		Boolean result=cDao.createRoom(title, username);
		if(result) {
			log.info("create complet");
		}else {
			log.info("create fail");
		}
		return title;
	}

	public Boolean findAllRoom(int chatroomId,String memberId) {
		Boolean check=cDao.plusroom(chatroomId, memberId);
		return check;
	}
	
	public List<String> findRoomMember(int roomId){
		return cDao.getRoomMember(roomId);
	}
	
	public List<ChatDto> roomlist() {
		List<ChatDto> clist=cDao.getRoomList();
		return clist;
	}
	public Boolean outRoom(int roomId, String memberId) {
		return cDao.outRoom(roomId, memberId);
	}
	public ChatDto roomData(int roomId) {
		return cDao.intoRoom(roomId);
	}
}
=======
package com.gg.gop.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gop.dao.ChatDao;
import com.gg.gop.dto.ChatDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
	
	final ChatDao cDao;
//	@PostConstruct
//	private void init() {
//		chatRooms=new LinkedHashMap<>();
//	}
//	
//	public ChatRoom findRoomById(Object roomId) {
//		return chatRooms.get(roomId);
//	}
	
	public String createRoom(String title, String username) {
//		String randomId=UUID.randomUUID().toString();
//		ChatRoom chatRoom=ChatRoom.builder()
//								  .roomId(randomId)
//								  .build();
//		chatRooms.put(randomId, chatRoom);
		log.info(username);
		Boolean result=cDao.createRoom(title, username);
		if(result) {
			log.info("create complet");
		}else {
			log.info("create fail");
		}
		return title;
	}

	public ChatDto findAllRoom(int chatroomId) {
//		return new ArrayList<>(chatRooms.values());
		return cDao.intoRoom(chatroomId);
	}

	public List<ChatDto> roomlist() {
		List<ChatDto> clist=cDao.getRoomList();
		return clist;
	}
}
>>>>>>> YS:GOP.GG-main/src/main/java/com/gg/gop/service/ChatService.java
