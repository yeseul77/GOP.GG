package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.BoardDto;

@Mapper
public interface BoardDao {

<<<<<<< HEAD
	// 전체 게시글 정보 조회
	List<BoardDto> getAllBoards();
	//게시글작성
	void insertwrite(BoardDto board);



	

=======
	//전체 게시글 정보 조회
	  List<BoardDto> getAllBoards();
	  //글저장insert 메서드
	    void insertBoard(BoardDto boardDto);
	
	
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
}
