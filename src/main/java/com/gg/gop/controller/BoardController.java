package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gop.dto.BoardDto;
import com.gg.gop.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/member/boardlist")
	public String boardList(Model model,HttpSession session) {
		return "board/boardList";
	}

	@GetMapping("/member/boardwrite")
	public String boardWriteForm(HttpSession session, Model model) {
	if (session.getAttribute("loginState") == null) {
		// 로그인하지 않은 사용자는 로그인 페이지로 리디렉션
			return "redirect:/login";
		}
		return "board/boardWrite";
	}

	// 게시글 작성 요청 처리
	@PostMapping("/member/boardwrite")
	public String boardWrite(HttpSession session, BoardDto board,RedirectAttributes rttr) {
		boolean result =boardService.boardwrite(board,session);
		//파일업로드 프로젝트경로를 얻으려면 로그인한 회원의session정보 가져와야함
		if(result) {
			rttr.addFlashAttribute("massage","글쓰기 성공");
			return "redurect:/boardlist";
		}else {
			rttr.addFlashAttribute("massage","글쓰기 실패");
			return "redirect:/boardwrite";
		}
		}
		
		
		
		//		if (session.getAttribute("loginState") == null) {
//			// 로그인하지 않은 사용자는 로그인 페이지로 리디렉션
//			return "redirect:/login";
//		}
//		boardService.writeBoard(boardDto);
//		return "redirect:/boardList";
	}


