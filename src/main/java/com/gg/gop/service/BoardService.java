package com.gg.gop.service;

import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> 460304782af17c227702360b2e841954ad0105a1
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gop.dao.BoardDao;
import com.gg.gop.dto.BoardDto;

<<<<<<< HEAD
=======
import jakarta.servlet.http.HttpSession;


>>>>>>> 460304782af17c227702360b2e841954ad0105a1
@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
<<<<<<< HEAD
	
	 public List<BoardDto> getAllBoards() {
	        return boardDao.getAllBoards();
	    }

	    public void writeBoard(BoardDto boardDto) {
	        boardDao.insertBoard(boardDto);
	    }


}

=======

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
>>>>>>> 460304782af17c227702360b2e841954ad0105a1
