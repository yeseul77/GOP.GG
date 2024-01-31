package com.gg.gop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	private PasswordEncoder passwordEncoder;

	// 입력받은 id,pw가 다르면 로그인 실패하는 메서드
	public String login(String u_id, String u_pw) {
		MemberDto memberDto = memberDao.getMemberId(u_id);
		if (memberDto.getU_pw().equals(u_pw))
			return memberDto.getU_id(); // 로그인성공
		return null; // 로그인실패
		// 저장된 dto에 pw와,입력된 pw값이 같으면 id값을 리턴 = 로그인성공
		// 다르다면 null 반환 =로그인 실패
	}

	public void register(MemberDto memberDto) {
		memberDao.insertMember(memberDto);
	}

}
