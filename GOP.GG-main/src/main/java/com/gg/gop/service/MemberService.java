package com.gg.gop.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

import oracle.jdbc.proxy.annotation.Post;


@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	//회원가입
	  public boolean register(MemberDto memberDto) {
		// Encoder(암호화)<------->Decoder(복호화)
			BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
			memberDto.setPassword(pwEncoder.encode(memberDto.getPassword()));
			return memberDao.insertMember(memberDto);
	    }

		public String idCheck(String email) {
			   if(memberDao.idCheck(email)==false) {
					return "ok"; // 사용가능한 아이디
				}
				return "fail";
			}
	  
		public MemberDto login(HashMap<String, String> member) {
			// TODO Auto-generated method stub

		  BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String encoPwd = memberDao.getSecurityPw(member.get("email"));
		  if (encoPwd != null) {
				System.out.println("아이디존재함");
				if (pwEncoder.matches(member.get("password"),encoPwd)) {
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
		  
	
	  




	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	//회원탈퇴
	public Boolean withdraw(String m_id, String m_pw) {
	    memberDao.deleteMember(m_id);
	    return true;
	}

	
//아이디 중복체크
	public String checkid(String m_id) {
		 if(memberDao.idCheck(m_id)==false) {
				return "ok"; //
			}
			return "fail";
		}
//회원정보수정
	public boolean updateMemberInfo(MemberDto memberDto) {
		 int updateCount = memberDao.updateMemberInfo(memberDto);
		    return updateCount > 0; // 업데이트된 행의 수가 0보다 크면 성공으로 간주
		
	}



	


	}
