package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;
import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {
	

	// 회원가입
	void insertMember(MemberDto memberDto);
	MemberDto findById(Long id);
	
	//로그인
	 MemberDto findByLogin(String id, String m_pw);
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	// 회원탈퇴
	void deleteMember(String m_id);
	
	//회원아이디중복조회
	boolean idcheck(String m_id);
	Object findById(Long id);


	


	


	

}
