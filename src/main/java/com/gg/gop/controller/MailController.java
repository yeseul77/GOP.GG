package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.service.MailService;

@RestController   
public class MailController {

	
	 @Autowired
	    private 	MailService emailService;

	    @PostMapping("/mail")
	    public String sendEmail(String email) {
	        try {
	            String verificationCode = emailService.sendVerificationEmail(email);
	            return "인증 코드가 발송되었습니다: " + verificationCode;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "이메일 발송 중 오류가 발생했습니다.";
	        }
	    }
	}


