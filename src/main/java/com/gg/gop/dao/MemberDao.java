package com.gg.gop.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {
	
	//로그인 
	boolean login(HashMap<String, String> hMap);

	// 회원가입
	boolean insertMember(MemberDto memberDto);
	MemberDto getMemberInfo(String string);
	// 회원정보조회
	MemberDto getMemberById(String m_id);

	void updatemyInfo(MemberDto memberDto);

	// 내정보 수정
	void updateMember(MemberDto memberDto);

	// 회원탈퇴
	void deleteMember(String m_id);
	
	//회원아이디중복조회
	boolean idcheck(String m_id);

	
	String getSecurityPw(String string);

	

}
