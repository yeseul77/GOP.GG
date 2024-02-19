<<<<<<< HEAD:src/main/java/com/gg/gop/dao/ChatDao.java
package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.ChatDto;
import com.gg.gop.dto.ChatMemberDto;

@Mapper
public interface ChatDao {
	List<String> getRoomMember(int roomId);
	List<ChatDto> getRoomList();
	Boolean createRoom(String title, Object userId);
	ChatDto intoRoom(int roomId);
	Boolean deleteRoom(int chatroomId);
	Boolean plusroom(int chatroomId, String memberId);
	Boolean outRoom(int chatroomId, String memberId);
}
=======
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
>>>>>>> YS:GOP.GG-main/src/main/java/com/gg/gop/dao/ChatDao.java
