package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;
import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {

	// 회원가입
	boolean insertMember(MemberDto memberDto);
	//아이디(이메일)중복  체크
	boolean idCheck(String email);
	// 암호화된 비밀번호
	String getSecurityPw(String string);

	MemberDto getMemberInfo(String string);

	//회원프로필이미지 db저장
	void updateMemberProfile(MemberDto memberDto);
	





	
	// 회원정보 수정,삭제 ,탈퇴
	
}
