
package com.gg.gop.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gop.dto.MemberDto;
import com.gg.gop.dto.ProfileDto;
import com.gg.gop.service.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

//회원가입
	@GetMapping("/register")
	public String registerForm() {

		System.out.println("회원가입 폼");
		return "member/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute MemberDto memberDto, RedirectAttributes rttr) {
		boolean result = memberService.register(memberDto);
		if (result) {
			rttr.addFlashAttribute("message", "회원가입 축하드립니다!");
			return "redirect:/login"; 
		} else {
			rttr.addFlashAttribute("message", "회원가입에 실패했습니다.");
			return "redirect:/register"; // 회원가입 실패 시 회원가입 폼으로 리다이렉트
		}
	}

	// 세션 로그인====================================================
	@GetMapping("/login")
	public String loginForm(HttpSession session) {
		System.out.println("로그인FORM");
		return "member/login";

	}

	// 시큐리티 합친 세션로그인
	@GetMapping("/logincom")
	public String loginSuccess(HttpSession session, ModelAndView model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		Object username = userDetails.getUsername();
		System.out.println("로그인성공 메인으로 ㄱㄱ!");
		MemberDto memberDto = memberService.getUserData(username);
		session.setAttribute("memberDto", memberDto); //baord글저장
		session.setAttribute("email", memberDto.getEmail());
		session.setAttribute("username", memberDto.getUsername());
		session.setAttribute("loginState", true);
		String email=(String) session.getAttribute("email");
		MemberDto member = memberService.selectMemberInfo(email);
		System.out.println(member);
		session.setAttribute("profile", member.getProfile());
		return "index";
	}

	@PostMapping("/loginresult")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			RedirectAttributes rttr, HttpSession session) {
		System.out.println("test");
		// HashMap을 사용하여 사용자 정보를 저장
		HashMap<String, String> memberData = new HashMap<>();
		memberData.put("email", email);
		memberData.put("password", password);

		System.out.println("email: " + email + ", password: " + password);

		try {

			MemberDto memberDto = memberService.login(memberData);
			rttr.addFlashAttribute("msgType", "성공");
			rttr.addFlashAttribute("message", "로그인에 성공하였습니다.");
			session.setAttribute("email", memberDto.getEmail());
			session.setAttribute("loginState", true);
			return "redirect:/";   
		} catch (Exception e) {
			// 로그인 실패 처리
			rttr.addFlashAttribute("msgType", "실패");
			rttr.addFlashAttribute("message", "로그인에 실패하였습니다. 다시 시도해주세요.");
			return "redirect:/login";
		}
	}

	// 로그아웃===========================================
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		if (session != null && !session.isNew()) {
			session.invalidate();
			rttr.addFlashAttribute("message", "로그아웃되었습니다.");
		}
		return "redirect:/";
	}

	// 비밀번호찾기페이지
	@GetMapping("/findpw")
	public String findpwForm() {
		System.out.println("비번찾기페이지로!");
		return "member/findpw";
	}

	// 임시비밀번호 전송
	@PostMapping("/findpw")
	public String findPassword(@RequestParam("email") String email, RedirectAttributes rttr) {
		boolean result = memberService.sendpwdCode(email);
		if (result) {
			rttr.addFlashAttribute("message", "임시 비밀번호를 이메일로 발송하였습니다.");
		} else {
			rttr.addFlashAttribute("message", "이메일을 확인해주세요. 회원 정보를 찾을 수 없습니다.");
		}
		return "redirect:/findpw";
	}

	// 내정보보기
	@GetMapping("/member/memberinfo")
	public ModelAndView infoform(HttpSession session, ModelAndView model) {
		System.out.println("내프로필보기");
		String email = (String) session.getAttribute("email");
		MemberDto member = memberService.selectMemberInfo(email);
		System.out.println(member);
		model.addObject("member", member);
		return model;
	}
	
	//edit MemberInfo
	@PostMapping("/member/memberinfo/updateMemberInfo")
	public String updateMember(HttpSession session,MemberDto memberDto) {
		boolean result = memberService.updateProfileInfo(memberDto);
		MemberDto mDto=memberService.getUserData(memberDto.getUsername());
		System.out.println(result);
		session.setAttribute("username", memberDto.getUsername());
		return "redirect:/member/memberinfo";
	}
	
	//edit Photo
	@PostMapping("/member/memberinfo/updateimg")
	public String updateProfile(ProfileDto profileDto, @RequestParam("attachments") MultipartFile attachments, HttpSession session,
	        RedirectAttributes rttr) {
	    if (attachments != null && !attachments.isEmpty()) {
	        try {
	            String filename = attachments.getOriginalFilename();
	            System.out.println("filename" + filename);
	            String realPath = session.getServletContext().getRealPath("/");
	            System.out.println("realPath=" + realPath);
	            String path = "\\upload/";
	            realPath += "upload/";
	            System.out.println("2");
	            String filePath = realPath + filename;
	            System.out.println("3");
	            File dest = new File(filePath);
	            System.out.println("4");
	            attachments.transferTo(dest);
	            System.out.println("5");
	            String profileImageUrl = path + filename;
	            profileDto.setProfile(profileImageUrl);
	    		String email=(String) session.getAttribute("email");
	    		MemberDto member = memberService.selectMemberInfo(email);
	    		System.out.println(member);
	    		session.setAttribute("profile", member.getProfile());
	            System.out.println("6");
	            boolean result = memberService.updateProfile(profileDto,session);

	            if (result) {
	                System.out.println("프로필 업데이트 성공");
	            } else {
	                System.out.println("프로필 업데이트 실패");
	            }
	        } catch (IOException e) {
	            System.out.println("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
	        }
	    } else {
	        System.out.println("파일이 올바르지 않습니다");
	    }

	    return "redirect:/member/memberinfo";
	}

	// 회원 탈퇴 페이지
	@GetMapping("/member/mypage/withdraw")
	public String withdrawCheck(Model model, HttpSession session) {
		System.out.println("회원탈퇴페이지");
		return "member/withdraw";
	}

	@PostMapping("/member/mypage/withdraw")
	public String withdraw(@RequestParam String email, @RequestParam String password,
	                       HttpSession session, RedirectAttributes rttr) {
	    Boolean result = memberService.withdraw(email, password);

	    if (result) {
	        session.invalidate(); // 세션 무효화

	        // 세션에서 사용자 정보 삭제
	        session.removeAttribute("email");
	        session.removeAttribute("username");
	        session.removeAttribute("loginState");

	        rttr.addFlashAttribute("message", "회원 탈퇴가 성공적으로 이루어졌습니다.");
	    } else {
	        // 회원 탈퇴 실패
	        rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.");
	    }

	    return "redirect:/";
	}
}
