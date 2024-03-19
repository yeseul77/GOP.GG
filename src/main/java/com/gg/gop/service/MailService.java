package com.gg.gop.service;

import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MemberDao memberDao;

	@Value("${spring.mail.username}")
	private String from;

	public String sendAuthEmail(String toEmail) {
		String authCode = generateAuthCode();
		String subject = "GOP.GG 회원가입 인증코드입니다.";
		String content = "인증번호: \u25A1" + authCode + "\u25A1\n회원가입 화면으로 돌아가\n인증코드를 입력해 주세요";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

		try {
			helper.setFrom("elim0427@naver.com");
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(content);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}

		mailSender.send(message);

		return authCode;
	}

	private String generateAuthCode() {
		Random random = new Random();
		StringBuilder authCode = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			authCode.append(random.nextInt(10));
		}
		return authCode.toString();
	}

//=====================================================================

	// 임시비밀번호 변경 메일
	public String sendAndSetTempPassword(String email) {
		String tempPassword = generateTempPassword();
		String subject = "GOP.GG 임시비밀번호 보내드립니다.";
		String content = "임시 비밀번호: " + tempPassword + "\n로그인 후 비밀번호를 변경해주세요.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

		try {
			helper.setFrom("elim0427@naver.com");
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(content);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}

		mailSender.send(message);

		MemberDto memberDto = memberDao.getMemberInfo(email);
		if (memberDto != null) {
			//System.out.println("test");
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            String encoPwd=pwEncoder.encode(tempPassword);
			memberDto.setPassword(encoPwd);
			memberDao.updatePassword(memberDto);
			return tempPassword;
		}

		return null;
	}

	private String generateTempPassword() {
		Random random = new Random();
		StringBuilder tempPwd = new StringBuilder();
		String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < 8; i++) {
			if (i < 6) {
				tempPwd.append(random.nextInt(10));
			} else {
				tempPwd.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
			}
		}
		return tempPwd.toString();
	}

	public boolean sendpwdCode(String email, String tempPassword) {
		String subject = "GOP.GG 임시 비밀번호 발송";
		String content = "회원님의 임시 비밀번호는 " + tempPassword + " 입니다.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

		try {
			helper.setFrom("elim0427@naver.com");
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(content);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

		mailSender.send(message);
		return true;

	}
}
