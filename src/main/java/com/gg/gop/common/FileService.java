<<<<<<< HEAD
package com.gg.gop.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

@Service
public class FileService {
	@Autowired
	private MemberDao memberDao;
	// 업로드될 서버 내의 디렉토리 ex)"c:/dskjfkdsjfkldsjflksdjfksdjfkls/upload"
	private final String uploadDir = "/uploads/";

	public String uploadFile(MultipartFile file, String userEmail) throws IOException {
		if (file.isEmpty()) {
			return null;
		}
		String originalFilename = file.getOriginalFilename();
		// 업로드된 파일의 원본 가져오기
		String fileName = System.currentTimeMillis() + "."
				+ originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		// 현재시간+확장자조합= 파일명 충돌을 방지
		String fullPath = Paths.get(uploadDir, fileName).toString();
		// 파일의 전체 경로생성

		// 파일 업로드 후, 사용자의 프로필 이미지 정보를 업데이트합니다.
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(userEmail); // 사용자 이메일
		memberDto.setProfile(fileName); // 업로드된 파일명
		// memberDao.update("MemberMapper.updateMemberProfile", memberDto);
		// dao가서 메서드만들기..
		return fileName; // DB에 저장할 파일명 반환
	}
}
=======
package com.gg.gop.common;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;
import jakarta.servlet.http.HttpSession;

@Service
public class FileService {

    @Autowired
    private MemberDao memberDao;

    private final String uploadDir = "/uploads/"; // 서버 내 업로드 될 디렉토리

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
            memberDao.updateMemberProfile(memberDto); // DB 업데이트
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
        memberDao.updateMemberProfile(memberDto); // DB 업데이트

   
    }
}
>>>>>>> YS
