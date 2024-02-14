package com.gg.gop.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	// 회원가입
	public boolean register(MemberDto memberDto) {
		// Encoder(암호화)<------->Decoder(복호화)
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(pwEncoder.encode(memberDto.getPassword()));
		return memberDao.insertMember(memberDto);
	}

	public String idCheck(String email) {
		if (memberDao.idCheck(email) == false) {
			return "ok"; // 사용가능한 아이디
		}
		return "fail";
	}

	public MemberDto login(HashMap<String, String> member) {

		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String encoPwd = memberDao.getSecurityPw(member.get("email"));
		if (encoPwd != null) {
			System.out.println("아이디존재함");
			if (pwEncoder.matches(member.get("password"), encoPwd)) {
				System.out.println("로그인성공");
				return memberDao.getMemberInfo(member.get("email"));
			} else {
				System.out.println("비밀번호오류");
				return null;
			}
		} else {
			System.out.println("아이디오류");
			return null;
		}
	}

//아이디 중복체크
	public String checkid(String email) {
		if (memberDao.idCheck(email) == false) {
			return "ok"; //
		}
		return "fail";
	}

	public Boolean withdraw(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//회원 정보 수정 ,삭제 ,탈퇴
	

	
}
