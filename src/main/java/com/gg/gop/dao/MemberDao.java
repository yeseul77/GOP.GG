package com.gg.gop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gg.gop.dto.MemberDto;

@Mapper
public interface MemberDao {

	// 회원가입
	boolean insertMember(MemberDto memberDto);
	//닉네임 중복  체크
	public boolean selectusername(String username);
	// 암호화된 비밀번호
	String getSecurityPw(String string);

	//회원프로필이미지 db저장
	void updateMemberProfile(MemberDto memberDto);
	
	 // 이메일로 회원 정보 조회
    MemberDto getMemberInfo(String email);

    // 회원 탈퇴 상태 업데이트
    @Update("UPDATE member_tb SET deleteYn = true WHERE email = #{email} AND password = #{password}")
    int updateMemberToDeleteStatus(@Param("email") String email, @Param("password") String password);
	
    
  //시큐리티세션로그인 유저이름찾기
    @Select("SELECT * FROM member_tb WHERE username = #{username}")
	MemberDto sequsername(Object username);
	
}
