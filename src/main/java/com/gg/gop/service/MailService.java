package com.gg.gop.service;

<<<<<<< HEAD
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender emailSender;

	private String authCode; // 인증번호

	public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = emailSender.createMimeMessage();
		// System.out.println("보내는 대상 : " + to);
		// System.out.println("인증 번호 : " + authcode);
		message.addRecipient(RecipientType.TO, new InternetAddress(to)); // 보내는 대상
		message.setSubject("GOP.GG 회원가입 이메일 인증"); // 제목

		String msgBody = "<div style='margin:100px;'>";
		msgBody += "<h1>안녕하세요</h1>";
		msgBody += "<h1>전적검색 최전방 GOP.GG입니다</h1>";
		msgBody += "<br>";
		msgBody += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
		msgBody += "<br>";
		msgBody += "<p>감사합니다!<p>";
		msgBody += "<br>";
		msgBody += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgBody += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
		msgBody += "<div style='font-size:130%'>";
		msgBody += "CODE : <strong>";
		msgBody += authCode + "</strong><div><br/> "; // 메일에 인증번호 넣기
		msgBody += "</div>";

		message.setText(msgBody, "utf-8", "html"); // 내용, charset 타입, subtype
		message.setFrom(new InternetAddress("보내는사람이메일주소", "GOP_Admin")); // 보내는 사람

		return message;
	}

	// 랜덤 인증 코드 생성 (6자리)
	public String createCode() {
		StringBuilder code = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) { // 인증코드 6자리
			int type = random.nextInt(3); // 0~2 까지 랜덤

			switch (type) {
			case 0:
				code.append((char) (random.nextInt(26) + 65)); // 대문자 알파벳 (A-Z)
				break;
			case 1:
				code.append((char) (random.nextInt(26) + 97)); // 소문자 알파벳 (a-z)
				break;
			case 2:
				code.append(random.nextInt(10)); // 숫자 (0-9)
				break;
			}
		}

		return code.toString();
	}

	// 메일 발송
	// sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
	// MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
	// 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send
	public String sendSimpleMessage(String to) throws MessagingException, UnsupportedEncodingException {
		authCode = createCode(); // 랜덤 인증번호 생성

		MimeMessage message = createMessage(to); // 메일 생성
		try {
			emailSender.send(message); // 이메일 발송
		} catch (MailException e) {
			e.printStackTrace();
			throw new RuntimeException("이메일 전송 중 오류가 발생했습니다.");
		}

		return authCode; 
	}
}
=======
public class MailService {

}
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
