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
	public MemberDto login(String m_id, String m_pw) {
		MemberDto memberDto = memberDao.getMemberId(m_id);

//		if (memberDto.getM_pw().equals(m_pw))
//			return memberDto.getM_id(); // 로그인성공

		return null; // 로그인실패
		// 저장된 dto에 pw와,입력된 pw값이 같으면 id값을 리턴 = 로그인성공
		// 다르다면 null 반환 =로그인 실패
	}
//회원가입
	public void register(MemberDto memberDto) {
		memberDao.insertMember(memberDto);
	}
	//회원탈퇴
	public Boolean withdraw(String m_id, String m_pw) {
	    memberDao.deleteMember(m_id);
	    return true;
	}

	//회원정보 조회
	public MemberDto getMemberById(String m_id) {
		 return memberDao.getMemberById(m_id);
	}
	
	// 회원정보수정
		
	public void updatemyInfo(MemberDto memberDto) {
		memberDao.updatemyInfo(memberDto);
		
	}

		
	}

