package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gop.dto.MemberDto;
import com.gg.gop.service.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
public class MemberController {
	//spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 X
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/register")
	public String registreForm(Model model) {
		System.out.println("회원가입 폼URL");
		return "register";
	}
	
	@PostMapping("/register")
	public String registre(MemberDto memberDto) {//회원가입 MemberDto전체들고옴
		try {
			memberService.register(memberDto);
		}catch(DuplicateKeyException e) {
			return "redirect:/register?error_code=-1"; //중복회원 예외처리
		}catch(Exception e) {
			e.printStackTrace();
			return "redirect:/register?error_code=-99";
		}
		return "redirect:/login";
			
	}
	
	@GetMapping("/login")
	public String loginForm(Model model,HttpSession session) {
		String u_id=(String)session.getAttribute("u_id");
		if(u_id !=null) {
			return "redirect:/";  //로그인 됐을때
		}
		System.out.println("로그인 폼 URL");
		return "login";  //로그인 안됐을때 login.jsp로
	}
	
	@PostMapping("/login")
	public String login(String u_id,String u_pw,HttpSession session) {
		System.out.println("로그인POST성공!");
		String loginid=memberService.login(u_id,u_pw);
		if(u_id==null) {
			return "redirect:/login";
		}
		session.setAttribute("u_id",u_id);
		return"redirect:/";
	}

}
