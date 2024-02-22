package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;
import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {

	// 회원가입
	boolean insertMember(MemberDto memberDto);
	//닉네임 중복  체크
	public boolean selectusername(String username);
	// 암호화된 비밀번호
	String getSecurityPw(String string);

	MemberDto getMemberInfo(String string);

	//회원프로필이미지 db저장
	void updateMemberProfile(MemberDto memberDto);
	

	
}
