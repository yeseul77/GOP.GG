package com.gg.gop.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	private final String DEFAULT_PROFILE_IMAGE_PATH = "/images/defaultprofile.png";

	// 회원가입
    public boolean register(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(pwEncoder.encode(memberDto.getPassword()));

        // 처음 회원가입시 ,프로필 이미지가 설정되지 않은 경우 defaultprofile로 
        if (memberDto.getProfile() == null || memberDto.getProfile().isEmpty()) {
            memberDto.setProfile(DEFAULT_PROFILE_IMAGE_PATH);
        }

        // 회원 정보 데이터베이스에 저장
        return memberDao.insertMember(memberDto);
    }
	
    
    //아이디 중복체크
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

	//회원탈퇴
	public Boolean withdraw(String email, String password) {
		
		return null;
	}


	public boolean updateMemberInfo(String email, String username, MultipartFile profileImage) {
		
		return false;
	}

	//회원의 프로필사진변경 메소드


	

	

	
}
