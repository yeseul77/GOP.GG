package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "board/boardList";
	}
	
	@GetMapping("/boardWrite")
	public String boardWriteForm(HttpSession session, Model model) {
		if (session.getAttribute("loginState") == null) {
			// 로그인하지 않은 사용자는 로그인 페이지로 리디렉션
			return "redirect:/login";
		}
		return "board/boardWrite";
	}

	// 게시글 작성 요청 처리
	@PostMapping("/boardWrite")
	public String boardWrite(HttpSession session, BoardDto boardDto) {
		if (session.getAttribute("loginState") == null) {
			// 로그인하지 않은 사용자는 로그인 페이지로 리디렉션
			return "redirect:/login";
		}
		boardService.writeBoard(boardDto);
		return "redirect:/boardList";
	}

}

