package com.gg.gop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity // 시큐리티6 활성화 및 웹보안설정 부트3.0이상에서 생략가능
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
//환경설정 클래스 정의하면 시큐리티 로그인창 안뜸
<<<<<<< HEAD
public class SecurityConfig{
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
=======
public class SecurityConfig {
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
	//권한없을 때 예외처리 핸들러
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	     
		http.csrf(csrf -> csrf.disable());//csrf를 비활성화
<<<<<<< HEAD
		http.formLogin(form -> form.loginPage("/member/login").loginProcessingUrl("/member/login")
				.defaultSuccessUrl("/").failureUrl("/member/login/error")
			
		);
		http.logout(logout -> logout.logoutUrl("/member/logout").logoutSuccessUrl("/"));
=======
		http.formLogin(form -> form.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
//				.failureUrl("/member/login/error")
				.usernameParameter("email")
		);
		http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
		http.exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler));
		return http.build();		
	}
	
	
//	폼 기반 로그인을 활성화하고, 사용자 정의 로그인 페이지의 경로를 /member/login으로 설정합니다.
//	로그인 처리 URL도 /member/login으로 설정하며, 로그인 성공 시 사용자를 루트 URL(/)로 리다이렉션하고, 실패 시 /member/login/error로 리다이렉션합니다.
//
//	http.logout(logout -> logout.logoutUrl("/member/logout").logoutSuccessUrl("/"));: 로그아웃 기능을 활성화하고,
//	로그아웃 처리 URL을 /member/logout으로 설정합니다. 로그아웃 성공 시 사용자를 루트 URL(/)로 리다이렉션합니다.
//
//	http.exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler));:
//	접근 거부(권한이 없는 리소스에 접근하려고 할 때) 발생 시 사용할 AccessDeniedHandler를 구성합니다. 이 핸들러는 사용자 정의 예외 처리 로직을 수행할 수 있도록 해줍니다.
//
//	return http.build();:
	//설정된 HttpSecurity 객체를 기반으로 SecurityFilterChain을 빌드하고 반환
	
<<<<<<< HEAD
	
=======
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
}
