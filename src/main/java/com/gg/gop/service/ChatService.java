package com.gg.gop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gg.gop.dao.ChatDao;
import com.gg.gop.dto.ChatDto;
import com.gg.gop.dto.ChatMemberDto;
import com.gg.gop.dto.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
	
	final ChatDao cDao;
	
	public String createRoom(String title, Object username, String champ, String position, String memo) {

		log.info("{}",username);
		Boolean result=cDao.createRoom(title, username, champ, position, memo);
		ChatDto myroom=cDao.roomInfo((String)username, title);
		cDao.plusroom(myroom.getChatroomId(), title,(String)username );
		if(result) {
			log.info("create complet");
		}else {
			log.info("create fail");
		}
		return title;
	}

	public Boolean findAllRoom(int chatroomId,String memberId) {
		String title=cDao.intoRoom(chatroomId);
		Boolean check=cDao.plusroom(chatroomId, title, memberId);
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
		ChatDto roomData=cDao.roomData(roomId);
		if(memberId.equals(roomData.getUserId())) {
			cDao.deleteRoomData(roomId);
		}
		return cDao.outRoom(roomId, memberId);
	}
	public ChatDto roomData(int roomId) {
		return cDao.roomData(roomId);
	}
	public ChatMemberDto roomMemberData(int roomId) {
		return cDao.roomMemberData(roomId);
	}
	public void deleteRoom() {
		cDao.deleteRoom();
	}

	public List<ChatMemberDto> mylist(String userId) {
		List<ChatMemberDto> mylist=cDao.getMyRoomList(userId);
		return mylist;
	}
	public void chatlog(int roomId, String userId, String message) {
		cDao.messageLog(roomId, userId, message);
	}

	public List<ChatMessage> beforeMsg(int roomId) {
		log.info("{}",roomId);
		List<ChatMessage> messages=cDao.getRoomMessage(roomId);
		log.info("{}",messages);
		return messages;
	}
}