package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.ChatDto;
import com.gg.gop.dto.ChatMemberDto;
import com.gg.gop.dto.ChatMessage;

@Mapper
public interface ChatDao {
	List<String> getRoomMember(int roomId);
	List<ChatDto> getRoomList();
	void createRoom(String title, Object userId, String champ, String position, String memo);
	String intoRoom(int roomId);
	Boolean plusroom(int chatroomId,String title, String memberId);
	Boolean outRoom(int chatroomId, String memberId);
	ChatDto roomData(int roomId);
	ChatMemberDto roomMemberData(int roomId);
	void deleteRoom();
	void deleteRoomData(int roomId);
	List<ChatMemberDto> getMyRoomList(String userId);
	ChatDto roomInfo(String userId, String title);
	void messageLog(int roomId, String chatmember, String message);
	List<ChatMessage> getRoomMessage(int roomId);
	ChatDto lastroomInfo(String username, String title);
}