package com.gg.gop.common;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gg.gop.dto.MemberDto;
import jakarta.servlet.http.HttpSession;

@Service
public class FileService {
	 //서버내 업로드될 디렉토리
    private final String uploadDir =   "src/main/resources/static/uploads/";

    public String uploadProfileImage(MultipartFile file, HttpSession session) throws IOException {
        if (file.isEmpty()) {
            return null; // 파일이 비어있으면 null 반환
        }

        String realPath = session.getServletContext().getRealPath(uploadDir); // 실제 업로드 경로
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs(); // 디렉토리가 존재하지 않으면 생성
        }

        String originalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fullPath = realPath + fileName;
       
        file.transferTo(new File(fullPath)); // 파일 저장

        return fileName; // 저장된 파일명 반환
    }

    public void updateMemberProfileImage(MultipartFile file, HttpSession session) throws IOException {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            throw new IllegalStateException("로그인 세션이 만료되었거나 사용자 정보를 찾을 수 없습니다.");
        }

        String fileName = uploadProfileImage(file, session); // 프로필 이미지 업로드
        if (fileName != null) {
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(email);
            memberDto.setProfile(fileName); // 업로드된 파일명을 사용자 프로필 이미지로 설정
            //memberDao.updateMemberProfile(memberDto); // DB 업데이트
        }
    }
    


    public void deleteProfileImage(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            throw new IllegalStateException("로그인 세션이 만료되었거나 사용자 정보를 찾을 수 없습니다.");
        }

        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(email);
        memberDto.setProfile("defaultprofile.png"); // 기본 이미지로 설정
       // memberDao.updateMemberProfile(memberDto); // DB 업데이트

   
    }
}
