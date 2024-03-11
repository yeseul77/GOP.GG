package com.gg.gop.service;

import java.util.HashMap;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gg.gop.common.FileManager;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;
import com.gg.gop.dto.ProfileDto;
import jakarta.servlet.http.HttpSession;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MailService mailService;
    @Autowired
    private FileManager fileManager;

    private final String DEFAULT_PROFILE_IMAGE_PATH = "/images/defaultprofile.png";
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    public boolean register(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(pwEncoder.encode(memberDto.getPassword()));

        // 처음 회원가입시 ,프로필 이미지가 설정되지 않은 경우 defaultprofile로
        if (memberDto.getProfile() == null || memberDto.getProfile().isEmpty()) {
            System.out.println("test");
            memberDto.setProfile(DEFAULT_PROFILE_IMAGE_PATH);

        }

        // 회원 정보 데이터베이스에 저장
        return memberDao.insertMember(memberDto);
    }

    // 시큐리티 로그인 세션에등록~
    public MemberDto getUserData(Object username) {
        return memberDao.sequsername(username);

    }

    // 암호화 로그인
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

    // 존재하는 이메일인지 아닌지 ?
    public MemberDto findByEmail(String email) {
        return memberDao.getMemberInfo(email);
    }

    public void updatePassword(MemberDto memberDto) {
        memberDao.updatePassword(memberDto);
    }

    // 회원탈퇴
    public Boolean withdraw(String email, String password) {
        MemberDto memberDto = memberDao.getMemberInfo(email);

        if (memberDto == null || !memberDto.getPassword().equals(password)) {
            return false;
        }

        memberDto.setDeleteYn(true);

        return true;
    }

    public static String generateTempPassword() {
        Random random = new Random();
        StringBuilder tempPassword = new StringBuilder();
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 8; i++) {
            if (i < 6) {
                tempPassword.append(random.nextInt(10));
            } else {
                tempPassword.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
            }
        }

        return tempPassword.toString();
    }

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // 내프로필 사진변경
    public boolean updateProfile(ProfileDto profileDto, HttpSession session) {
        boolean profileUpdateResult = memberDao.updateProfile(profileDto);
        if (!profileUpdateResult) {
            System.out.println("프로필 업데이트 실패");
            return false;
        }

        MultipartFile attachments = profileDto.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            boolean fileUploadResult = fileManager.updateProfile(attachments, session, profileDto.getUsername());
            if (!fileUploadResult) {
                System.out.println("파일첨부 실패");
                return false;
            }
        } else {
            System.out.println("파일이 올바르지 않습니다");
            return false;
        }
        return true;
    }

    public boolean sendpwdCode(String email) {

        MemberDto memberDto = memberDao.getMemberInfo(email);

        if (memberDto != null) {
            // 임시 비밀번호 생성
            String tempPassword = generateTempPassword();

            // 회원 정보에 임시 비밀번호 저장
            memberDto.setPassword(tempPassword);
            memberDao.updatePassword(memberDto);

            // 이메일로 임시 비밀번호 전송
            boolean emailSent = mailService.sendpwdCode(email, tempPassword);

            return emailSent;
        } else {
            // 해당 이메일로 등록된 회원이 없는 경우
            return false;
        }
    }

    public boolean selectusername(String username) {
        // TODO Auto-generated method stub
        return false;
    }
}
