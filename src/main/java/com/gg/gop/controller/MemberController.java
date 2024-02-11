package com.gg.gop.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String registreForm(HttpSession session) {
		System.out.println("회원가입 폼");
		return "member/register";
	}
	
	@PostMapping("/register")
	public String register(MemberDto memberDto, RedirectAttributes rttr,Model model) {
	   boolean result = memberService.register(memberDto);
		if (result) {
	    	rttr.addFlashAttribute("message", "회원가입축하드립니다!");
	        return "redirect:/login"; // 회원가입 성공 시 로그인 페이지로
	    } else {
	    	model.addAttribute("message", "회원가입에가입에 실패했습니다");
	        return "redirect:/register";
	    }
	}
	
	// 로그인====================================================
	@GetMapping("/login")
	public String loginForm(HttpSession session) {
		System.out.println("로그인FORM");
		return "member/login"; 
		
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, 
	                    @RequestParam String password,
	                    RedirectAttributes rttr, HttpSession session) {
	    // HashMap을 사용하여 사용자 정보를 저장
	    HashMap<String, String> member = new HashMap<>();
	    member.put("email", email);
	    member.put("password", password);

	    // 사용자가 존재하는지 확인
	    MemberDto memberDto = memberService.login(member);
	    if (memberDto.getEmail()==null ||
	    		memberDto.getPassword() .equals("")){
	    rttr.addFlashAttribute("msgType", "실패메세지");
	    rttr.addFlashAttribute("message", "모든  항목을 입력해 주세요");
	return "redirect:/register";
	    }
	    if (memberDto != null) {   // 로그인 성공
	    	   rttr.addFlashAttribute("msgType", "성공^^");
	   	    rttr.addFlashAttribute("message", "로그인에 성공@");
	        session.setAttribute("email", memberDto.getEmail()); // 사용자 이메일을 세션에 저장
	        session.setAttribute("Loginstate", true); // 로그인 상태를 세션에 저장 
	        System.out.println("email: " + session.getAttribute("email"));
	        System.out.println("Loginstate:" + session.getAttribute("Loginstate")); 
	        return "redirect:/";
	    } else {
	        // 로그인 실패
	    	  rttr.addFlashAttribute("msgType", "실패메세지");
	  	    rttr.addFlashAttribute("message", "다시 로그인 해주세요");
	        return "redirect:/login";
	    }
	}



	// 로그아웃===========================================
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate(); //세션message무효화처리 
		rttr.addFlashAttribute("", "로그아웃되었습니다.");
		return "redirect:/";

	}
	

	//=================================================================
	@GetMapping("/member/mypage")
	public String mypageFomr(HttpSession session) {
		return "/member/mypage";
	}
	
	
	@PostMapping("/member/mypage")
	public String updateMemberInfo(@ModelAttribute MemberDto memberDto,
	                               HttpSession session,
	                               RedirectAttributes rttr) {
	    String email = (String) session.getAttribute("email");
	    Boolean Loginstate = (Boolean) session.getAttribute("Loginstate");

	    // 로그인 상태가 아니라면 로그인 페이지로 리다이렉트
	    if (email == null || !Loginstate) {
	        rttr.addFlashAttribute("message", "로그인이 필요한 서비스입니다.");
	        return "redirect:/login";
	    }

	    // 세션의 memberID와 수정 요청된 DTO의 email이 일치하는지 확인
	    if (!memberDto.getEmail().equals(email)) {
	        rttr.addFlashAttribute("message", "권한이 없습니다.");
	        return "redirect:/mypage";
	    }

	    // 정보 수정 로직 수행
	    boolean updateResult = memberService.updateMemberInfo(memberDto);
	    if (updateResult) {
	        rttr.addFlashAttribute("message", "정보 수정 완료.");
	    } else {
	        rttr.addFlashAttribute("message", "정보 수정 실패.");
	    }
	    return "redirect:/mypage";
	}
	
	
	//회원 프로필 사진등록
	@GetMapping("/member/Imageform")
	public String UserImageForm() {
		return "member/Imageform";
	}
	
	
	
	
	
	
	
	
	
	
	// 조회후 > 수정 MemberDto member = memberService.getMemberById(m_id);
	// 회원 정보 조회=======================================
//	@GetMapping("/mypage")
//	public String serchProfile(Model model, HttpSession session) {
//		String m_id = (String) session.getAttribute("m_id");
//
//		if (m_id != null) {
//			MemberDto memberDto = memberService.getMemberById(m_id);
//
//			if (memberDto != null) {
//				model.addAttribute("memberDto", memberDto);
//				return "member/mypage";
//			} else {
//				// 회원 정보가 없을 경우 예외 처리
//				model.addAttribute("error", "회원 정보를 찾을 수 없습니다.");
//				return "redirect:/";
//			}
//		} else {
//			// 로그인 되어 있지 않을 경우 로그인 페이지로 이동
//			return "redirect:/login";
//		}
//	}
//
//	// 회원 정보 수정,탈퇴===============================================
//
//	@PostMapping("/mypage")
//	public String changeProfile(MemberDto memberDto, HttpSession session, RedirectAttributes rttr, Model model) {
//		try {
//			memberService.updateMemberInfo(memberDto);
//			rttr.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
//			return "redirect:/profile";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "회원 정보 수정에 실패했습니다. 다시 시도해주세요.");
//			return "redirect:/editProfile";
//		}
//	}
//

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
			rttr.addFlashAttribute("message", "회원 탈퇴가 성공적으로 이루어졌습니다.");
			return "redirect:/";
		} else {
			rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.");
			return "redirect:/mypage";
		}
	}

}
