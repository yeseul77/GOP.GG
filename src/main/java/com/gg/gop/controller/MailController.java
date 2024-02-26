package com.gg.gop.controller;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gg.gop.service.MailService;

@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	// 이메일 인증
	@PostMapping("login/mailConfirm")
	@ResponseBody
	String mailConfirm(@RequestParam("email") String email) throws Exception {

	   String code = mailService.sendSimpleMessage(email);
	   System.out.println("인증코드 : " + code);
	   return code;
	}

}
=======

import org.springframework.web.bind.annotation.RestController;

//이메일 인증번호 하는거 맹글어야함.. 
@RestController     ///("/sendEmailVerification")
public class MailController {
	


	          
	
	   

	}


>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
