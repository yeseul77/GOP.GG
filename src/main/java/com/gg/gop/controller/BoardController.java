package com.gg.gop.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gg.gop.dto.BoardDto;
import com.gg.gop.dto.MemberDto;

import com.gg.gop.service.BoardService;
import com.gg.gop.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;

	@GetMapping("/boardlist")
	public String selecBoardList(Model model) {
	    List<BoardDto> bList = boardService.selectBoardlist();
	    model.addAttribute("bList", bList);
	    
	    List<MemberDto> memberList = new ArrayList<>();
	    for (BoardDto boardDto : bList) {
	        String username = boardDto.getUsername();
	        MemberDto memberDto = memberService.getUserData(username);
	        memberList.add(memberDto);
	    }
	    model.addAttribute("memberList", memberList);

	    return "board/boardList";
	}

	// 글쓰기 처리

	@GetMapping("/board/boardwrite")
	public String WriteForm() {
		System.out.println("커뮤니티 글쓰기폼");
		return "board/boardWrite";
	}
	
	@PostMapping("/board/boardwrite")
	public String boardWrite(BoardDto boardDto, HttpSession session, RedirectAttributes rttr) {
	 
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) principal;
	        String username = userDetails.getUsername();
	        
	        MemberDto memberDto = memberService.getUserData(username);
	        if (memberDto != null) {
	            boardDto.setUsername(memberDto.getUsername());

	            boolean result = boardService.boardWrite(boardDto, session);
	            if (result) {
	                rttr.addFlashAttribute("msg", "글쓰기 성공");
	                System.out.println("글쓰기성공!");
	                return "redirect:/boardlist";
	            }
	        }
	    }
	    rttr.addFlashAttribute("msg", "로그인 후에 글을 작성할 수 있습니다.");
	    System.out.println("글쓰기실패");
	    return "redirect:/login";
	}
	


	@GetMapping("/test")
	public String Test() {
		System.out.println("상세보기");
		return "board/boardDetail";
	}

	// 상세보기
	@GetMapping("/detail")
	public String selectBoardDetail(@RequestParam("idx") int idx, Model model) {

		BoardDto boardDto = boardService.selectBoardDetail(idx, model);
		System.out.println("게시글 상세보기 " + boardDto);
		boardService.updateViewsCnt(idx);
		model.addAttribute("boardDto", boardDto);
		return "board/boardDetail";

	}

	@ResponseBody
	@PostMapping("/likes")
	public ResponseEntity<String> likes(int idx, String username) {
		log.info("======{}", idx);
		
	    boolean result = boardService.likesInsert(idx, username);

	    if (result) {
	        boolean likes = boardService.updateLikes(idx);
	        
	        if (likes) {
	            return ResponseEntity.ok("좋아요 성공");
	        } else {
	            return ResponseEntity.badRequest().body("좋아요 실패");
	        }
	    } else {
	        boolean likecancel = boardService.updateLikes(idx);
	        if (likecancel) {
	            return ResponseEntity.ok("좋아요 취소");
	        } else {
	            return ResponseEntity.badRequest().body("좋아요 취소 실패");
	        }
	    }
	}

	@GetMapping("/delete")
	public String boardDelete(int idx, HttpSession session, RedirectAttributes rttr) {
		boolean result = boardService.boardDelete(idx, session);
		if (result) {
			rttr.addFlashAttribute("msg", idx + "삭제성공");
			return "redirect:/board/list?pageNum=1";
		} else {
			rttr.addFlashAttribute("msg", idx + " 삭제실패");
			return "redirect:/board/detail?bnum=" + idx;
		}
	}

//	@PostMapping("/delete")
//	public String boardDelete(BoardDto boardDto, RedirectAttributes rttr) {
//		boolean replyDelete = boardService.replyDeleteList(boardDto.getIdx());
//		boolean result = boardService.boardDelete(boardDto);
//
//		if (replyDelete && result) {
//			rttr.addFlashAttribute("msg", "삭제 성공");
//		} else {
//			rttr.addFlashAttribute("msg", "삭제 실패");
//		}
//
//		return "redirect:/board/list";
//	}

	@GetMapping("/replydelete")
	public String replyDelete(int rnum, int idx, HttpSession session, RedirectAttributes rttr) {
		boolean result = boardService.replyDelete(rnum, session);

		if (result) {
			rttr.addFlashAttribute("msg", rnum + "삭제성공");
		} else {
			rttr.addFlashAttribute("msg", rnum + "삭제실패");
		}
		return "redirect:/board/detail?idx=" + idx;
	}


//	@PostMapping("/replydelete")
//	public String replyDelete(ReplyDto replyDto, HttpSession session) {
//		boolean result = boardService.ReplyDelete(replyDto);
//
//		if (result) {
//			return "redirect:/board/detail?bnum=" + replyDto.getRbnum();
//		} else {
//			return "redirect:/board/detail?bnum=" + replyDto.getRbnum();
//		}
//	}

}
