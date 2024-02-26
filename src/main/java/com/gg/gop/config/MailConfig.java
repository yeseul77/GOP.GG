package com.gg.gop.config;

import java.util.Properties;

<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost("smtp.naver.com"); // 메인 도메인 서버 주소 => 정확히는 smtp 서버 주소
		javaMailSender.setUsername("네이버 아이디"); // 네이버 아이디
		javaMailSender.setPassword("네이버 비밀번호"); // 네이버 비밀번호
		javaMailSender.setPort(465); 

		javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져오기

		return javaMailSender;
	}

	private Properties getMailProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
	        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
	        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
	        properties.setProperty("mail.debug", "true"); // 디버그 사용
	        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com"); // ssl 인증 서버는 smtp.naver.com
	        properties.setProperty("mail.smtp.ssl.enable","true"); // ssl 사용
	        return properties;
	    }

	
}
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@PropertySource("classpath:application.properties")
@Configuration
public class MailConfig {
    @Value("${spring.mail.username}")
    private String id;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host); // smtp 서버 주소
        javaMailSender.setUsername(id); // 설정(발신) 메일 아이디
        javaMailSender.setPassword(password); // 설정(발신) 메일 패스워드
        javaMailSender.setPort(port); //smtp port
        javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보 가져온다.
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp starttls 사용
        properties.setProperty("mail.debug", "true"); // 디버그 사용
        properties.setProperty("mail.smtp.ssl.trust", "smtp.mailplug.co.kr"); // ssl 인증 서버 주소
        properties.setProperty("mail.smtp.ssl.enable", "true"); // ssl 사용
        return properties;
    }
}
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
