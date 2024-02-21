package com.gg.gop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gg.gop.service.MemberService;

@Controller
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	
	 @PostMapping("/checkEmail")
	    @ResponseBody
	    public Map<String, Boolean> checkEmailDuplication(@RequestParam("email") String email) {
	        Map<String, Boolean> response = new HashMap<>();
	        boolean isDuplicated = memberService.isEmailDuplicated(email); // 이메일 중복 검사

	        response.put("isDuplicated", isDuplicated);
	        return response;
	    }

}
