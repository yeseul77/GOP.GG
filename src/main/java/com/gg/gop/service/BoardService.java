package com.gg.gop.service;

import java.util.List;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gop.dao.BoardDao;
import com.gg.gop.dto.BoardDto;

import jakarta.servlet.http.HttpSession;


=======

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.BoardDao;
import com.gg.gop.dto.BoardDto;

>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
<<<<<<< HEAD

	// 게시글 전체가져오기
	public List<BoardDto> getAllBoards() {
		return boardDao.getAllBoards();
	}

	public boolean boardwrite(BoardDto board, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	// 게시글작성
	
	
=======
	
	 public List<BoardDto> getAllBoards() {
	        return boardDao.getAllBoards();
	    }

	    public void writeBoard(BoardDto boardDto) {
	        boardDao.insertBoard(boardDto);
	    }

>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37

}
