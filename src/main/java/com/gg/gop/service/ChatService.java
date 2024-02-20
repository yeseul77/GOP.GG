package com.gg.gop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gg.gop.dao.ChatDao;
import com.gg.gop.dto.ChatDto;

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
		return cDao.outRoom(roomId, memberId);
	}
	public ChatDto roomData(int roomId) {
		return cDao.roomData(roomId);
	}
	public void deleteRoom() {
		cDao.deleteRoom();
	}
}