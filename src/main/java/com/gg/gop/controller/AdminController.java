package com.gg.gop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@GetMapping("")
	public String adminLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String adminLogins(HttpSession session) {
		return "login";
	}

}
