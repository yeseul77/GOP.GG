package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;
import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {

	// 회원가입
	boolean insertMember(MemberDto memberDto);

	// 암호화된  비밀번호
	String getSecurityPw(String string);

	MemberDto getMemberInfo(String email);

	// 회원아이디중복조회
	boolean idCheck(String email);

	// 회원탈퇴
	void deleteMember(String email);

	//회원정보수정
	int updateMemberInfo(MemberDto memberDto);

}
