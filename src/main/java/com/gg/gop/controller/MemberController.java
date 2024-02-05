package com.gg.gop.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gop.dao.MemberDao;
import com.gg.gop.dto.MemberDto;
import com.gg.gop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	// spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 하면 값안넘어감!
	// 폴더에 넣을경우 JSP경로 잘설정했는지, DTO필드값 mapper랑 잘맞는지 확인하기
	// 주석을 생활하하자
	// 세션으로 먼저 구현하고 시큐리티 구현

	@Autowired
	private MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MemberDao memberDao;
	

	// 회원가입==============================================
	@GetMapping("/register")
	public String registreForm(HttpSession session) {
		System.out.println("회원가입 폼");
		return "member/register";
	}


	
	 @PostMapping("/register")
	    public String register(MemberDto memberDto) {
	       
	        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// 로그인====================================================
	@GetMapping("/login")
	public String loginForm(Model model, HttpSession session, RedirectAttributes rttr) {
		String m_id = (String) session.getAttribute("m_id");
		if (m_id != null) {
			return "redirect:/"; // 로그인 됐을때
		}
		System.out.println("로그인 폼 URL");
		return "member/login"; // 로그인 안됐을때 login.jsp로

	}

	@PostMapping("/login")
	public String login(@RequestParam HashMap<String, String> member, 
	                    Model model, HttpSession session, RedirectAttributes rttr) {
	    // memberService.login 메서드의 파라미터를 수정해야 합니다.
	    MemberDto memberDto = memberService.login(member);

	    if (memberDto != null) {
	        session.setAttribute("member", memberDto);  // 로그인 성공 후 회원정보를 출력하기 위해
	        log.info("ID={}, PW={}");
	        Object url = session.getAttribute("urlPrior_login");
	  
	        if (url != null) {
	            session.removeAttribute("urlPrior_login");
	            return "redirect:" + url.toString();
	        } else {
	            return "redirect:/";
	        }
	    } else {
	        rttr.addFlashAttribute("msg", "로그인 실패");
	        return "redirect:/member/login"; 
	    }
	}
		


	// 로그아웃===========================================
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();

		System.out.println("로그아웃");
		rttr.addFlashAttribute("msg", "로그아웃되었습니다.");
		return "redirect:/";

	}

	// 조회후 > 수정 MemberDto member = memberService.getMemberById(m_id);
	// 회원 정보 조회=======================================
	@GetMapping("/mypage")
	public String serchProfile(Model model, HttpSession session) {
		String m_id = (String) session.getAttribute("m_id");

		if (m_id != null) {
			MemberDto memberDto = memberService.getMemberById(m_id);

			if (memberDto != null) {
				model.addAttribute("memberDto", memberDto);
				return "member/mypage";
			} else {
				// 회원 정보가 없을 경우 예외 처리
				model.addAttribute("error", "회원 정보를 찾을 수 없습니다.");
				return "redirect:/";
			}
		} else {
			// 로그인 되어 있지 않을 경우 로그인 페이지로 이동
			return "redirect:/login";
		}
	}

	// 회원 정보 수정,탈퇴=============================

	


	@PostMapping("/mypage")
	public String changeProfile(MemberDto memberDto, HttpSession session, RedirectAttributes rttr, Model model) {
		try {
			memberService.updatemyInfo(memberDto);
			rttr.addFlashAttribute("msg", "회원 정보가 수정되었습니다.");
			return "redirect:/profile";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 정보 수정에 실패했습니다. 다시 시도해주세요.");
			return "redirect:/editProfile";
		}
	}

	
	
	
	@GetMapping("/mypage/withdraw")
	public String withrawCheck(Model model, HttpSession session) {
		return "member/withdraw";
		}
	
	
	
	@PostMapping("/mypage/withdraw") // 탈퇴
	public String withdraw(@RequestParam String m_id, @RequestParam String m_pw, HttpSession session,
			RedirectAttributes rttr) {

		Boolean result = memberService.withdraw(m_id, m_pw);

		if (result) {
			session.invalidate();
			rttr.addFlashAttribute("msg", "회원 탈퇴가 성공적으로 이루어졌습니다.");
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("msg", "회원 탈퇴에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.");
			return "redirect:/mypage";
		}
	}

}
