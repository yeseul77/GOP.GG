package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.ChatDto;

@Mapper
public interface ChatDao {
	List<ChatDto> getRoomList();
	Boolean createRoom(String title, String userId);
	ChatDto intoRoom(int roomId);
	Boolean deleteRoom(int chatroomId);
}
