package com.gg.gop.config;

import java.util.Properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "mail")
@PropertySource("classpath:mailAuth.properties")
@Configuration
public class MailConfig {
	
	  private String username;
	  private String password;
	
	  @Bean
	  public JavaMailSender javaMailSender() {
	      JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	      javaMailSender.setHost("smtp.naver.com");
	      javaMailSender.setUsername("elim0427@naver.com");
	      javaMailSender.setPassword("icialol6683!");
	      javaMailSender.setPort(465);
	      javaMailSender.setJavaMailProperties(getMailProperties());
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
