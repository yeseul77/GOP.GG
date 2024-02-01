package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gg.gop.dto.MemberDto;
import com.gg.gop.service.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	// spring 3.2부터 RequestParam으로 값넘겨줘야함 생략 하면 값안넘어감!
	// 폴더에 넣을경우 JSP경로 잘설정했는지, DTO필드값 mapper랑 잘맞는지 확인하기
	// 주석을 생활하하자
	// 세션으로 먼저 구현하고 시큐리티 구현

	@Autowired
	private MemberService memberService;

	// 회원가입==============================================
	@GetMapping("/register")
	public String registreForm(Model model) {
		System.out.println("회원가입 폼URL");
		return "member/register";
	}

	@PostMapping("/register")
	public String registre(MemberDto memberDto, RedirectAttributes rttr, Model model) {
		try {
			memberService.register(memberDto);// 중복이 없으면 로그인 페이지로
			rttr.addFlashAttribute("msg", "가입성공");
			return "redirect:/login";
		} catch (DuplicateKeyException e) {
			return "redirect:/register?error_code=-1"; // 중복이 발생하면 회원가입 페이지로
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "가입실패 재입력해주세요!");
			return "redirect:/register?error_code=-99";
			// 그 외의 예외가 발생하면 일반적인 오류로 처리하고 회원가입 페이지로
		}
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
	public String login(@RequestParam String m_id, @RequestParam String m_pw, Model model, HttpSession session,
			RedirectAttributes rttr) {
		// 서비스를 통해 로그인 여부 확인
		MemberDto loginUser = memberService.login(m_id, m_pw);

		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			return "redirect:/index";
		} else {
			// 로그인 실패 시 다시 로그인페이지로
			rttr.addFlashAttribute("msg", "로그인실패ㅠㅠ");
			return "redirect:/iogin";

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
	public String viewProfile(Model model, HttpSession session) {
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
	@GetMapping("/mypage")
	public String changeProfileForm(Model model, HttpSession session) {
		String m_id = (String) session.getAttribute("m_id");

		if (m_id != null) {
			MemberDto memberDto = memberService.getMemberById(m_id);

			if (memberDto != null) {
				model.addAttribute("memberDto", memberDto);
				return "member/editProfile";
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
