package com.gg.gop.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegistMailService {

	private final MailService mailService;

	// 회원가입하면 보내는 메소드
	public String send() {
		String to = "elim0427@naver.com";
		String subject = "회원가입 인증이메일 입니다";
		String text = "내용입력해주셈";
		mailService.sendEmail(to, subject, text);

		 return mailService.ePw; 
	}
}
