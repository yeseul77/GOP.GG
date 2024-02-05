package com.gg.gop.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	

	// 입력받은 id,pw가 다르면 로그인 실패하는 메서드
	public MemberDto login(HashMap<String, String>memberDto) {
		// 복호화는 안되지만 비교는 가능 
		//아이디 aaa - 1234-->db : 432pi3p45328095-403 비교해줌
		String encoPwd = memberDao.getSecurityPw(memberDto.get("m_id"));
	
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		if (encoPwd != null) {
			log.info("아이디존재함");
			if (pwEncoder.matches(memberDto.get("m_pw"), encoPwd)) {
			log.info("로그인성공");
				return memberDao.getMemberInfo(memberDto.get("m_id"));
			} else {
				log.info("비밀번호오류");
				return null;
			}
		} else {
			log.info("아이디오류");
			return null;
		}
	}

//회원가입
	public boolean register(MemberDto memberDto) {
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		memberDto.setM_pw(pwEncoder.encode(memberDto.getM_pw()));
		return	memberDao.insertMember(memberDto);
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
	
//아이디 중복체크
	public String checkid(String m_id) {
		 if(memberDao.idcheck(m_id)==false) {
				return "ok"; // 
			}
			return "fail";
		}	
	}

		


