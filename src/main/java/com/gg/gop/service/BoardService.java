package com.gg.gop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.BoardDao;
import com.gg.gop.dto.BoardDto;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//게시글 리스트 전체 가져오기 
	 public List<BoardDto> getAllBoards() {
	        return boardDao.getAllBoards();
	    }
	 //
	    public void writeBoard(BoardDto boardDto) {
	        boardDao.insertBoard(boardDto);
	    }


}
