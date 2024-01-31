package com.gg.gop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {
	//spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 X
	
	
	@GetMapping("/register")
	public String registreForm(Model model) {
		System.out.println("회원가입 폼URL");
		return "register";
	}
	
	@PostMapping("/register")
	public String registre() {
		System.out.println("회원가입POST로 전송");
		return "register";
			
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		System.out.println("로그인 폼 URL");
		return "login";
	}
	
	@PostMapping("/login")
	public String login() {
		System.out.println("로그인POST성공!");
		return"login";
	}

}
