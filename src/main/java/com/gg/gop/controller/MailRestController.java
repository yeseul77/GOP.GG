package com.gg.gop.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gg.gop.service.MailService;
import jakarta.servlet.http.HttpSession;

@RestController
public class MailRestController {

	@Autowired
	private MailService mailService;
	

	// 인증번호
	@GetMapping("/email")
	public ResponseEntity<Map<String, String>> sendAuthCode(@RequestParam("mail") String mail, HttpSession session) {
	    String authCode = mailService.sendAuthEmail(mail);
	    if (authCode != null) {
	        session.setAttribute("authCode", authCode);
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "인증번호 발송 성공");
	        return ResponseEntity.ok(response);
	    } else {
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "인증번호 발송 실패");
	        return ResponseEntity.badRequest().body(response);
	    }
	}

	// 이메일인증
	@PostMapping("/verify-code")
	public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> payload, HttpSession session) {
		String userCode = payload.get("code");
		String sessionCode = (String) session.getAttribute("authCode");

		if (sessionCode != null && sessionCode.equals(userCode)) {
			session.removeAttribute("authCode");
			// 인증 성공 후 세션에서 코드 제거
			Map<String, Boolean> response = new HashMap<>();
			response.put("valid", true);
			return ResponseEntity.ok(response);
		} else {
			Map<String, Boolean> response = new HashMap<>();
			response.put("valid", false);
			return ResponseEntity.ok(response);
		}
	}
	
	// 임시 비밀번호 발송
	 @GetMapping("/send-pwd")
	    public ResponseEntity<Map<String, String>> sendTempPassword(@RequestParam("mail") String mail, HttpSession session) {
	        String tempPasswordSent = mailService.sendAndSetTempPassword(mail);  
	        if (tempPasswordSent!=null) {
	        	session.setAttribute("tempPassword", tempPasswordSent);
	            String tempPassword = (String) session.getAttribute("tempPassword");
	            if (tempPassword != null) {
	                Map<String, String> response = new HashMap<>();
	                response.put("message", "임시 비밀번호 발송 성공");
	                return ResponseEntity.ok(response);
	            } else {
		        	System.out.println("test2");
	                Map<String, String> response = new HashMap<>();
	                response.put("message", "임시 비밀번호를 찾을 수 없습니다.");
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	            }
	        } else {

	            Map<String, String> response = new HashMap<>();
	            response.put("message", "임시 비밀번호 발송 실패");
	            return ResponseEntity.badRequest().body(response);
	        }
	    }
}
