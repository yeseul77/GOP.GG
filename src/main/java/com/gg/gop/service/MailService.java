package com.gg.gop.service;

import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class MailService {
	
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 @Value("${spring.mail.username}")
	    private String from;

	    public String sendAuthEmail(String toEmail) {
	    	String authCode = generateAuthCode(); 
	    	String subject = "GOP.GG 회원가입 인증코드입니다.";
	    	String content = "인증번호: \u25A1" + authCode + 
	    			"\u25A1\n회원가입 화면으로 돌아가\n인증코드를 입력해 주세요";
	        			
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
	       
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

  
}
