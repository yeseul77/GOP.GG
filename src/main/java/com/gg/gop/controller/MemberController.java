<<<<<<< HEAD
package com.gg.gop.controller;

import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gop.common.FileService;
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
	 @Autowired
	    private FileService fileService;
	 
	 @PostMapping("/updateProfileImage")
	 public String updateProfileImage(@RequestParam("profileImage") MultipartFile file,
	                                  HttpSession session,
	                                  RedirectAttributes rttr) {
	     String memberEmail = (String) session.getAttribute("email"); // 세션에서 사용자 이메일 가져오기

	     try {
	         String fileName = fileService.uploadFile(file, memberEmail); // 파일 업로드 및 회원 프로필 이미지 정보 업데이트
	         if (fileName == null) {
	             rttr.addFlashAttribute("message", "파일 업로드에 실패했습니다.");
	             return "redirect:/member/profile";
	         }
	         
	         rttr.addFlashAttribute("message", "프로필 이미지가 성공적으로 업데이트되었습니다.");
	     } catch (IOException e) {
	         e.printStackTrace();
	         rttr.addFlashAttribute("message", "파일 업로드 중 오류가 발생했습니다.");
	         return "redirect:/member/profile";
	     }

	     return "redirect:/";
	 }
	 
	 
	
	// 회원가입==============================================
	@GetMapping("/register")
	public String registreForm(HttpSession session) {
		System.out.println("회원가입 폼");
		return "member/register";
	}

	@PostMapping("/register")
	public String register(MemberDto memberDto, RedirectAttributes rttr, Model model) {
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
	public String login(@RequestParam String email, @RequestParam String password, RedirectAttributes rttr,
			HttpSession session) {
		// HashMap을 사용하여 사용자 정보를 저장
		HashMap<String, String> member = new HashMap<>();
		member.put("email", email);
		member.put("password", password);

		try {
			MemberDto memberDto = memberService.login(member);
			rttr.addFlashAttribute("msgType", "성공^^");
			rttr.addFlashAttribute("message", "로그인에 성공@");
			session.setAttribute("email", memberDto.getEmail()); // 사용자 이메일을 세션에 저장
			session.setAttribute("Loginstate", true); // 로그인 상태를 세션에 저장
			session.setAttribute("username", memberDto.getUsername());


			return "redirect:/";
		} catch (Exception e) {
			// 로그인 실패
			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("message", "다시 로그인 해주세요");
			return "redirect:/login";
		}
	}

	// 로그아웃===========================================
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate(); // 세션무효화처리
		rttr.addFlashAttribute("", "로그아웃되었습니다.");
		return "redirect:/";

	}

	// 내정보 =====================================

	
	//이거는 수정해야될부분
	@GetMapping("/member/memberinfo")
	public String infoupdateform() {
		return "member/memberinfo";
	}
	
	@PostMapping("/member/memberinfo")
	public String infoupdate(HttpSession session,Model model,MemberDto memberDto) {
		
		
		
		return"member/memberinfo";
	}
	
	
	
	
	
	
	
	
	// =================================================================

	// 회원 프로필 사진등록
	@RequestMapping("/member/imageform")
	public String ImageForm() {
		return "member/imageform";
	}

	// 회원 사진 업로드(DB저장+업로드)
	@RequestMapping("/member/imageupdate")
	public String memberUpdate() {
		// 파일 업로드 API
		return "";
	}

	

	
	// 회원 탈퇴 확인 페이지 요청
	@GetMapping("/member/mypage/withdraw")
	public String withdrawCheck(Model model, HttpSession session) {
		// 회원 탈퇴 확인 페이지를 보여줍니다.
		return "member/withdraw";
	}

    
	// 회원 탈퇴 처리 요청
    @PostMapping("/member/mypage/withdraw")
    public String withdraw(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes rttr) {
        // 회원 탈퇴 처리를 수행합니다.
        Boolean result = memberService.withdraw(email, password);

        if (result) {
            // 탈퇴 처리 성공
            session.invalidate(); // 세션 무효화
            rttr.addFlashAttribute("message", "회원 탈퇴가 성공적으로 이루어졌습니다.");
            return "redirect:/"; // 홈페이지로 리다이렉트
        } else {
            // 탈퇴 처리 실패
            rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.");
            return "redirect:/mypage"; // 마이페이지로 리다이렉트
        }
    }
}
=======
package com.gg.gop.controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	@Autowired
	//private FileService fileService;

	// 회원가입==============================================
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
			return "redirect:/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
		} else {
			rttr.addFlashAttribute("message", "회원가입에 실패했습니다.");
			return "redirect:/register"; // 회원가입 실패 시 회원가입 폼으로 리다이렉트
		}
	}
	

	// 로그인====================================================
	@GetMapping("/login")
	public String loginForm(HttpSession session) {
		System.out.println("로그인FORM");
		return "member/login";

	}

	 @PostMapping("/login")
	    public String login(@RequestParam("email") String email, @RequestParam("password") String password,
	    		RedirectAttributes rttr, HttpSession session) {
	       
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
	            session.setAttribute("username", memberDto.getUsername());
	            
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
		session.invalidate(); // 세션무효화처리
		rttr.addFlashAttribute("", "로그아웃되었습니다.");
		return "redirect:/";

	}

	// 내정보 =====================================
	@GetMapping("/member/memberinfo")
	public String infoupdateform() {
		return "member/memberinfo";
	}

	// 닉네임변경
	@PostMapping("/member/memberinfo")
	public String updateProfile(@RequestParam("profileImage") MultipartFile file,
			@RequestParam("username") String username, HttpSession session, RedirectAttributes rttr) {
		String email = (String) session.getAttribute("email"); // 세션에서 이메일 가져오기

		try {
			// 프로필 이미지와 사용자 이름 업데이트
			boolean result = memberService.updateMemberInfo(email, username, file, session);
			if (result) {
				rttr.addFlashAttribute("message", "프로필 업데이트 성공!");
				session.setAttribute("username", username);
			} else {
				rttr.addFlashAttribute("message", "프로필 업데이트 실패.");
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("message", "업데이트 중 오류 발생.");
		}
		return "redirect:/member/memberinfo";
	}

	// =================================================================

	// 회원 탈퇴 페이지
	@GetMapping("/member/mypage/withdraw")
	public String withdrawCheck(Model model, HttpSession session) {
		return "member/withdraw";
	}

	// 회원 탈퇴 post처리
	@PostMapping("/member/mypage/withdraw")
	public String withdraw(@RequestParam String email, @RequestParam String password, HttpSession session,
			RedirectAttributes rttr) {

		Boolean result = memberService.withdraw(email, password);
		if (result) {
			// 탈퇴 처리 성공
			session.invalidate(); // 세션 무효화
			rttr.addFlashAttribute("message", "회원 탈퇴가 성공적으로 이루어졌습니다.");
			return "redirect:/"; // 홈페이지로 리다이렉트
		} else {
			// 탈퇴 처리 실패
			rttr.addFlashAttribute("message", "회원 탈퇴에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.");
			return "redirect:/mypage"; // 마이페이지로 리다이렉트
		}
	}
}
>>>>>>> YS
