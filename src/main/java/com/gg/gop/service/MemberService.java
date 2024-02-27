package com.gg.gop.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gg.gop.common.FileService;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private FileService fileService;

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

//닉네임중복
	public boolean selectusername(String username) {
		return memberDao.selectusername(username);
	}


//로그인
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

	// 회원탈퇴
	public Boolean withdraw(String email, String password) {
		 MemberDto member = memberDao.getMemberInfo(email);
	      
	        if (member == null || !member.getPassword().equals(password)) {
	            return false;
	        }

	        member.setDeleteYn(true);

	        return true;
	}

	//회원정보 +프로필변경
	public boolean updateMemberInfo(String email, String username, MultipartFile profileImage, HttpSession session) {
		try {
			// 프로필 이미지 업로드 및 파일명 반환
			String fileName = fileService.uploadProfileImage(profileImage, session);

			if (fileName != null) {
				MemberDto memberDto = new MemberDto();
				memberDto.setEmail(email);
				memberDto.setUsername(username);
				memberDao.updateMemberProfile(memberDto); 
			} else {
				MemberDto memberDto = new MemberDto();
				memberDto.setEmail(email);
				memberDto.setUsername(username);
				memberDao.updateMemberProfile(memberDto); 
			}
			return true;
		} catch (Exception e) {
			// 예외 처리 로직
			return false;
		}
	}

}
