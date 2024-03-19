package com.gg.gop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.gg.gop.dao.BoardDao;
import com.gg.gop.dto.BoardDto;
import com.gg.gop.dto.ReplyDto;
import jakarta.servlet.http.HttpSession;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 게시판 전체리스트
	public List<BoardDto> selectBoardlist() {
		return boardDao.selectBoardList();
	}

	// 게시글 한개씩상세보기
	public BoardDto selectBoardDetail(int idx, Model model) {
		System.out.println("index"+idx);
		return boardDao.getBoardDetail(idx);
	}
	// 게시판리스트 보여주기
//	public List<BoardDto> findBoardList(BoardDto boardDto) {
//		List<BoardDto> bList = boardDao.getAllBoardList();
//		return bList;
//	}

	// 글쓰기
	public boolean boardWrite(BoardDto boardDto, HttpSession session) {
		System.out.println("글쓰기 service~~");
		boardDao.saveWrite(boardDto);
		return true;
	}

	public boolean boardDelete(Integer idx, HttpSession session) {
		System.out.println("게시글 삭제하기");
		return boardDao.deleteWrite(idx);
	}

	public boolean likesInsert(int idx, String username) {
		Integer likes = boardDao.selectLikes(idx, username);
		System.out.println("좋아요 bb");

		if (likes ==1) {
			if (boardDao.deleteLikes(idx, username)) {
				System.out.println("좋아요 취소");
				return false;
			} else {

				return false;
			}
		} else {
			if (boardDao.insertLikes(idx, username)) {
				return true;
			} else {

				return false;
			}
		}
	}

	//좋아요기능~
	public boolean updateLikes(int idx) {
		BoardDto boardDto = boardDao.SelectUpdateLikes(idx);

		if (boardDto != null) {
			if (boardDao.updateLikes(boardDto.getLikes(), idx)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// 조회수 1씩증가
	public int updateViewsCnt(int  idx) {
		System.out.println("조회수증가");
	return boardDao.updateViewsCnt(idx);
		
	}
	
	//게시판에저장하는서비스ㅁ
	public void saveBoard() {
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목");
        boardDto.setContent("내용");
        boardDto.setUsername("사용자");
        boardDto.setViewcount(0);
        boardDto.setLikes(0);
        boardDto.setBoardtype("타입");

        boardDao.saveWrite(boardDto); 
    }

	public List<ReplyDto> getReplyList(int idx) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean replyDelete(int rnum, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean ReplyDelete(ReplyDto replyDto) {
		// TODO Auto-generated method stub
		return false;
	}


//	public boolean replyDeleteList(int bnum) {
//		// TODO Auto-generated method stub
//		return boardDao.replyDeleteList(bnum);
//	}

}
