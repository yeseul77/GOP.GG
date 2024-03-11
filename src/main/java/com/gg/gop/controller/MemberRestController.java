package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.service.MemberService;

@RestController
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    

    @PostMapping("/confirmusername")
    public ResponseEntity<Boolean> confirmUsername(@RequestParam("username") String username) {
        boolean result;

        if (username.trim().isEmpty()) {
            result = false;
        } else {
            result = memberService.selectusername(username);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    
    
    }
    
}