package com.gg.gop.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	


	//회원가입
	  public boolean register(MemberDto memberDto) {
	        try {
	            if (memberDao.findById(memberDto.getM_id()) == null) {
	            	memberDao.insertMember(memberDto);
	                return true; // 회원가입 성공
	            } else {
	                return false; // 이미 존재하는 회원 아이디
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false; // 회원가입 실패
	        }
	    }

//로그인
	  public MemberDto login(HashMap<String, String> loginInfo) {
	        return memberDao.findByLogin(loginInfo.get("m_id"), loginInfo.get("m_pw"));
	    
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

		


