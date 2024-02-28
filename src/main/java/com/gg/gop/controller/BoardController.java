package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gg.gop.dto.BoardDto;
import com.gg.gop.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
    @Autowired
    private BoardService boardService; 
    
    @GetMapping("/boardlist")
    public String boardList() {
    	System.out.println("커뮤니티 리스트로");
        return "board/boardList"; 
    }
    
    @GetMapping("/boardlist/boardwrite")
    public String boardWriteForm() {
        System.out.println("글쓰기창 열기!");
        return "board/boardWrite";
    }

    // 게시글 작성 요청 처리
    @PostMapping("/boardlist/writer")
    public String boardWrite(HttpSession session, BoardDto boardDto) {
        if (session.getAttribute("Loginstate") == null) {
            return "redirect:/login";
        }
        boardService.writeBoard(boardDto);
        return "redirect:/boardList";
    }
    
    
}

