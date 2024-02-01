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
<<<<<<< HEAD
	// spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 X
=======
	// spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 하면 값안넘어감!
	//폴더에 넣을경우 JSP경로 잘설정했는지, DTO필드값 mapper랑 잘맞는지 확인하기
	//주석을 생활하하자 
	
	//세션으로 먼저 구현하고 시큐리티 구현
>>>>>>> origin/YS

	@Autowired
	private MemberService memberService;

	// 회원가입==============================================
	@GetMapping("/register")
	public String registreForm(Model model) {
		System.out.println("회원가입 폼URL");
		return "member/register";
	}

<<<<<<< HEAD
	@PostMapping("/register")
	public String registre(MemberDto memberDto) {// 회원가입 MemberDto전체들고옴
		try {
			memberService.register(memberDto);
		} catch (DuplicateKeyException e) {
			return "redirect:/register?error_code=-1"; // 중복회원 예외처리
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/register?error_code=-99";
		}
		return "redirect:/login";

=======

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
>>>>>>> origin/YS
	}

	// 로그인====================================================
	@GetMapping("/login")
	public String loginForm(Model model, HttpSession session) {
		String m_id = (String) session.getAttribute("m_id");
		if (m_id != null) {
			return "redirect:/"; // 로그인 됐을때
		}
		System.out.println("로그인 폼 URL");
<<<<<<< HEAD
		return "login"; // 로그인 안됐을때 login.jsp로
=======
		return "member/login"; // 로그인 안됐을때 login.jsp로
>>>>>>> origin/YS
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
<<<<<<< HEAD
=======
		System.out.println("로그아웃");
>>>>>>> origin/YS
		return "redirect:/";

	}

	// 회원정보수정 ========================================
	@GetMapping("/mypage") // 회원정보 수정페이지
	public String mypageForm(HttpSession session, Model model) {
		String m_id = (String) session.getAttribute("m_id");
		//MemberDto memberDto = memberService.getm_id(m_id);
	//	model.addAttribute("MemberDto", memberDto);
<<<<<<< HEAD

		return "mypage";
=======
		System.out.println("회원정보수정페이지URL요청");
		return "member/mypage";
>>>>>>> origin/YS

	}

	@PostMapping("/mypage") // 회원정보 수정
	public String changeInfo(HttpSession session, MemberDto memberDto) {
		String m_id = (String) session.getAttribute("m_id");
		memberService.changeinfo(memberDto);
<<<<<<< HEAD
=======
		System.out.println("회원정보수정상공!");
>>>>>>> origin/YS
		return "redirect:/";
	}

	// 회원탈퇴===================================================

	@PostMapping("/delete")
<<<<<<< HEAD
	public String withdraw(HttpSession session) {// 탈퇴
=======
	public String withdraw(HttpSession session) {
>>>>>>> origin/YS
		String m_id = (String) session.getAttribute("u_id");
		if (m_id != null) {
			memberService.withdraw(m_id);
		}
		session.invalidate();
<<<<<<< HEAD
=======
		System.out.println("회원탈퇴..ㅠㅠ");
>>>>>>> origin/YS
		return "redirect:/";
	}

}
