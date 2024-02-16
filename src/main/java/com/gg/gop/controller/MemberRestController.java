package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gg.gop.service.MemberService;

@Controller
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	
	//아이디 중복체크 
    @GetMapping("/checkId")
    public String CheckId(String email) {
		String res=memberService.checkid(email);
		return res;
    }
	
    
    
    

}
