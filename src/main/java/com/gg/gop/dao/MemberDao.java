package com.gg.gop.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gg.gop.dto.MemberDto;
import com.gg.gop.dto.ProfileDto;

import jakarta.servlet.http.HttpSession;

@Mapper
public interface MemberDao {

	// 회원가입
	boolean insertMember(MemberDto memberDto);
	// 닉네임 중복 체크
	String selectusername(String username);

	// 암호화된 비밀번호
	String getSecurityPw(String string);

	// 회원프로필이미지 db저장
	boolean updateMemberProfile(MemberDto memberDto);

	//닉네임 변경
	boolean changeUsername(String username);
	// 이메일로 회원 정보 조회
	MemberDto getMemberInfo(String email);

	// 회원 탈퇴 상태 업데이트
	@Update("UPDATE member_tb SET deleteYn = true WHERE email = #{email} AND password = #{password}")
	int updateMemberToDeleteStatus(@Param("email") String email, @Param("password") String password);

	// 시큐리티세션로그인 유저이름찾기
	@Select("SELECT * FROM member_tb WHERE username = #{username}")
	MemberDto sequsername(Object username);
	// 닉네임 중복 확인
	 boolean selectUsername(String username);

	// 닉네임 업데이트
	boolean updateUsername(String email, String username);

	// 비밀번호 업데이트
	void updatePassword(MemberDto memberDto);

	// 변경사항저장
	void saveinfo(MemberDto memberDto);

	// 이메일로 회원조회=
	 MemberDto findByEmail(String email);
	
	//프로필사진변경 DAO
	boolean updateProfile(ProfileDto profileDto);
	//프로필 insert Map으로 
	boolean profileInsertMap(Map<String, String> fMap);
	////프로필변경
	boolean updateProfileInfo(MemberDto memberDto);
	boolean updateProfileImage(ProfileDto profileDto);
	MemberDto selectMemberInfo(String email);
	
	

	



}
