package com.gg.gop.controller;

import org.springframework.stereotype.Controller;
<<<<<<< HEAD
=======
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> 5bf9997d194ad8ecf7044b88bfa0659dec6a0a96

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

<<<<<<< HEAD
//	@GetMapping("/")
//	public String Main() {
//		return "index";
//	}
=======
	@GetMapping("/")
	public String Main(HttpSession session, Model model) {
		session.removeAttribute("");
		return "index";
		
		//이전 사용했던 세션들을 끊어줌 어떤거 끊어줘야 될까나~~
	}
>>>>>>> 5bf9997d194ad8ecf7044b88bfa0659dec6a0a96

}
