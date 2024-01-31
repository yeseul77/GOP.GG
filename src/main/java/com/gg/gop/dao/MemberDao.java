package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {
	
	//로그인 (아이디return)
	MemberDto getMemberId(String m_id);
	//회원가입
	void insertMember(MemberDto memberDto);
	//회원탈퇴
	void deleteMember(String m_id);
	//내정보 수정
	void updateMember(MemberDto memberDto);
	

}
