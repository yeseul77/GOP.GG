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
	// spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 하면 값안넘어감!
	//폴더에 넣을경우 JSP경로 잘설정했는지, DTO필드값 mapper랑 잘맞는지 확인하기
	//주석을 생활하하자 
	
	//세션으로 먼저 구현하고 시큐리티 구현

	@Autowired
	private MemberService memberService;

	// 회원가입==============================================
	@GetMapping("/register")
	public String registreForm(Model model) {
		System.out.println("회원가입 폼URL");
		return "member/register";
	}


	@PostMapping("/register")
	public String registre(MemberDto memberDto) {
	    try {
	        memberService.register(memberDto);// 중복이 없으면 로그인 페이지로
	        return "redirect:/login";
	    } catch (DuplicateKeyException e) {
	        return "redirect:/register?error_code=-1"; // 중복이 발생하면 회원가입 페이지로
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/register?error_code=-99";  
	        // 그 외의 예외가 발생하면 일반적인 오류로 처리하고 회원가입 페이지로
	    }
	}

	// 로그인====================================================
	@GetMapping("/login")
	public String loginForm(Model model, HttpSession session) {
		String m_id = (String) session.getAttribute("m_id");
		if (m_id != null) {
			return "redirect:/"; // 로그인 됐을때
		}
		System.out.println("로그인 폼 URL");
		return "member/login"; // 로그인 안됐을때 login.jsp로
	}

	@PostMapping("/login")
	public String login(String m_id, String m_pw, HttpSession session) {
		System.out.println("로그인POST성공!");
		String loginid = memberService.login(m_id, m_pw);
		if (m_id == null) {
			return "redirect:/login";
		}
		session.setAttribute("m_id", m_id);
		return "redirect:/";
	}

	// 로그아웃===========================================
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("로그아웃");
		return "redirect:/";

	}

	// 회원정보수정 ========================================
	@GetMapping("/mypage") // 회원정보 수정페이지
	public String mypageForm(HttpSession session, Model model) {
		String m_id = (String) session.getAttribute("m_id");
		//MemberDto memberDto = memberService.getm_id(m_id);
	//	model.addAttribute("MemberDto", memberDto);
		System.out.println("회원정보수정페이지URL요청");
		return "member/mypage";

	}

	@PostMapping("/mypage") // 회원정보 수정
	public String changeInfo(HttpSession session, MemberDto memberDto) {
		String m_id = (String) session.getAttribute("m_id");
		memberService.changeinfo(memberDto);
		System.out.println("회원정보수정상공!");
		return "redirect:/";
	}

	// 회원탈퇴===================================================

	@PostMapping("/delete")
	public String withdraw(HttpSession session) {
		String m_id = (String) session.getAttribute("u_id");
		if (m_id != null) {
			memberService.withdraw(m_id);
		}
		session.invalidate();
		System.out.println("회원탈퇴..ㅠㅠ");
		return "redirect:/";
	}

}
