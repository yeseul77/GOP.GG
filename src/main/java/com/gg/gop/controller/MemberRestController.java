<<<<<<< HEAD
package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.gg.gop.service.MemberService;
import lombok.extern.slf4j.Slf4j;

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
=======
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
>>>>>>> YS
