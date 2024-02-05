package com.gg.gop.websocket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatDao {
	List<ChatDto> getRoomList();
	Boolean createRoom(String title, String userId);
	ChatDto intoRoom(int roomId);
	Boolean deleteRoom(int chatroomId);
}
