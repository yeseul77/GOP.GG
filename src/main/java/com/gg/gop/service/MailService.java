package com.gg.gop.service;

import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MailService {

	private final JavaMailSender javaMailSender;
	

	public void sendEmail(String to, String subject, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("잘못된 이메일입니다: " + e.getMessage());
			throw new IllegalArgumentException();
			//잘못된 메소드 인자가 들어왔을때 발생★
		}
	}

	public static final String ePw = createKey();
	private static String createKey() {
		StringBuffer key = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < 6; i++) { // 인증코드 6자리
			int index = random.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				key.append((char) ((int) (random.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (random.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((random.nextInt(10)));
				// 0~9
				break;
			}
		}

		return key.toString();
	}

}