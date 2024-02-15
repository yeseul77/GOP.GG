package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.gg.gop.service.BoardService;

@Controller
public class BoardController {
	
    @Autowired
    private BoardService boardService; // Make sure the variable name matches the bean name
    
    @GetMapping("/boardlist")
    public String boardList() {
        return "board/boardList"; 
    }
    
    @GetMapping("/boardWrite")
    public String boardWrite() {
        return "board/boardWrite"; 
    }
}

