package com.gg.gop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.gg.gop.service.BoardService;

@RestController
public class BoardRestController {

	@Autowired
	private BoardService boardService;

//	@PostMapping("/likes")
//	public String likes(int idx, String username, Model model) {
//
//		boolean result = boardService.likesInsert(idx, username);
//
//		if (result) {
//			boolean likes = boardService.updateLikes(idx);
//
//			if (likes) {
//				return "좋아요 성공";
//			} else {
//				return "좋아요 실패";
//			}
//		} else {
//			boolean likecancel = boardService.updateLikes(idx);
//			return "좋아요 취소";
//		}
//	}
//	@PostMapping("/reply2")
//	public ResponseEntity<List<ReplyDto>> boardReply2(ReplyDto rDto,HttpSession session) {
//		List<ReplyDto> rList = boardService.replyInsert2(rDto);
//		return ResponseEntity.ok(rList);
//	}
//	
//	@PostMapping("/reply")
//		@ResponseBody
//	public ReplyDto BoardReply(ReplyDto rDto,HttpSession session) {
//		
//		ReplyDto reply = bSer.replyInsert(rDto);
//		return reply; //jackson => json => boarddetail => .done
//	}

}
