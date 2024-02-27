package com.gg.gop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gop.dao.BoardDao;
import com.gg.gop.dto.BoardDto;

import jakarta.servlet.http.HttpSession;


@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 게시글 전체가져오기
	public List<BoardDto> getAllBoards() {
		return boardDao.getAllBoards();
	}

	public boolean boardwrite(BoardDto board, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	// 게시글작성
	
	

}
