package com.gg.gop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	// 입력받은 id,pw가 다르면 로그인 실패하는 메서드
	public String login(String m_id, String m_pw) {
		MemberDto memberDto = memberDao.getMemberId(m_id);
<<<<<<< HEAD
		if (memberDto.getM_pw().equals(m_pw))
			return memberDto.getM_id(); // 로그인성공
=======
//		if (memberDto.getM_pw().equals(m_pw))
//			return memberDto.getM_id(); // 로그인성공
>>>>>>> origin/YS
		return null; // 로그인실패
		// 저장된 dto에 pw와,입력된 pw값이 같으면 id값을 리턴 = 로그인성공
		// 다르다면 null 반환 =로그인 실패
	}

	public void register(MemberDto memberDto) {
		
		memberDao.insertMember(memberDto);
	}

	public void withdraw(String m_id) {
		memberDao.deleteMember(m_id);
		
	}
	//회원정보수정
	public void changeinfo(MemberDto memberDto) {
		memberDao.updateMember(memberDto);
		
	}
}
