package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.gg.gop.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	
	//아이디 중복체크 
    @GetMapping("/checkId")
    public String CheckId(String m_id) {
		log.info("m_id", m_id);
		String res=memberService.checkid(m_id);
		return res;
    }
	
    
    
    

}
