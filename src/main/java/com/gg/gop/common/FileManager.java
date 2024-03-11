package com.gg.gop.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gg.gop.dao.MemberDao;
import jakarta.servlet.http.HttpSession;

@Service
public class FileManager {

	@Autowired
	private MemberDao memberDao;
	public boolean updateProfile(MultipartFile attachments, HttpSession session, String username) {

		System.out.println("File Manager class");
		// 프로젝트의 upload 경로 찾기
		String uploadDirectory = session.getServletContext().getRealPath("/") + "upload/";
		System.out.println("test2");
		// 폴더 생성
		File dir = new File(uploadDirectory);
		if (!dir.exists()) { // upload 폴더가 없다면
			System.out.println("test");
			dir.mkdirs(); // upload 폴더 생성 (mkdirs로 사용 시 다단 폴더까지 생성)
		}

		// 파일 정보 저장을 위한 Map
		Map<String, String> fMap = new HashMap<>();
		fMap.put("username", username);

		if (attachments == null || attachments.isEmpty()) {
			System.out.println("Attachments가 null 또는 비어 있습니다.");
			return false;
		}

		// 파일 메모리에 저장
		String oriFileName = attachments.getOriginalFilename();
		if (oriFileName == null || oriFileName.isEmpty()) {
			System.out.println("원본 파일명이 존재하지 않습니다.");
			return false;
		}
		System.out.println("원본 파일명 : " + oriFileName);
		fMap.put("oriFileName", oriFileName);

		String sysFileName = generateUniqueFileName(oriFileName);
		System.out.println("서버 파일명 : " + sysFileName);
		fMap.put("sysFileName", sysFileName);

		try {
			// 상대 경로로 파일 저장
			Path filePath = Paths.get(uploadDirectory, sysFileName);
			Files.copy(attachments.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			// 파일 경로 저장
			fMap.put("filePath", filePath.toString());

			// 파일 정보 DB에 저장
			boolean result = memberDao.profileInsertMap(fMap);
			if (!result) {
				System.out.println("File insertion failed.");
			}
			return result;
		} catch (IOException e) {
			System.out.println("파일 업로드 예외 발생: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	private String generateUniqueFileName(String fileName) {
		String uuid = UUID.randomUUID().toString();
		return uuid + "_" + fileName;
	}

}
