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
//		memberDto.setProfile(fileName); // 업로드된 파일명
		// memberDao.update("MemberMapper.updateMemberProfile", memberDto);
		// dao가서 메서드만들기..
		return fileName; // DB에 저장할 파일명 반환
	}
}
