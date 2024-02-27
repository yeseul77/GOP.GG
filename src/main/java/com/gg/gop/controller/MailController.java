package com.gg.gop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.service.MailService;

import jakarta.servlet.http.HttpSession;

//이메일 인증번호 하는거 맹글어야함.. 
@RestController   
public class MailController {

	 @Autowired
	    private MailService mailService;
	          
	 @RequestMapping("/sendEmail")
	    public String sendEmail(HttpSession session, String email) {
	        String verificationCode = generateVerificationCode(); 
	        mailService.sendEmail(email, "인증번호", "인증번호는 " + verificationCode + " 입니다.");
	        session.setAttribute("verificationCode", verificationCode);
	        return "인증번호가 전송되었습니다.";
	    }

	    // 인증번호 생성 메소드 구현
	    private String generateVerificationCode() {
			return null;
	    
	    }
	}


