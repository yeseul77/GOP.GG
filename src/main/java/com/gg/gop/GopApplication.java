package com.gg.gop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//(exclude = {SecurityAutoConfiguration.class})
//시큐리티 로그인화면제어
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GopApplication.class, args);
	}

}
