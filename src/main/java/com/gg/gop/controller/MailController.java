package com.gg.gop.controller;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gg.gop.service.MailService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class MailController {


    @Autowired
    private MailService mailService;

    //인증번호
    @GetMapping("/email")
    public ResponseEntity<?> sendAuthCode(@RequestParam("mail") String mail, HttpSession session) {
  //     log.info("email: {}", mail); 

        String authCode = mailService.sendAuthEmail(mail);
        if (authCode != null) {
            session.setAttribute("authCode", authCode); 
            return ResponseEntity.ok().body("인증번호 발송 성공");
        } else {
            return ResponseEntity.badRequest().body("인증번호 발송 실패");
        }
    }
    
    //이메일인증 
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> payload, HttpSession session) {
        String userCode = payload.get("code");
        String sessionCode = (String) session.getAttribute("authCode");

        if (sessionCode != null && sessionCode.equals(userCode)) {
            session.removeAttribute("authCode"); 
            // 인증 성공 후 세션에서 코드 제거
            Map<String, Boolean> response = new HashMap<>();
            response.put("valid", true);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Boolean> response = new HashMap<>();
            response.put("valid", false);
            return ResponseEntity.ok(response);
        }
    }
   
}

