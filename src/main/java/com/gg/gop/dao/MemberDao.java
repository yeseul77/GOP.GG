package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {
	
	//로그인해서 아이디를 반환하는 메서드
	MemberDto getMemberId(String u_id);
	//회원가입하는 메서드
	void insertMember(MemberDto memberDto);

}
