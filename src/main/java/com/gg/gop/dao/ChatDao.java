package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.ChatDto;

@Mapper
public interface ChatDao {
	List<String> getRoomMember(int roomId);
	List<ChatDto> getRoomList();
	Boolean createRoom(String title, Object userId);
	ChatDto intoRoom(int roomId);
	Boolean plusroom(int chatroomId, String memberId);
	Boolean outRoom(int chatroomId, String memberId);
	void deleteRoom(int chatroomId);
}
