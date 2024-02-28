package com.gg.gop.dao;

import java.util.List;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Mapper;
=======

import org.apache.ibatis.annotations.Mapper;

>>>>>>> 460304782af17c227702360b2e841954ad0105a1
import com.gg.gop.dto.BoardDto;

@Mapper
public interface BoardDao {

<<<<<<< HEAD
	// 전체 게시글 정보 조회
	List<BoardDto> getAllBoards();
<<<<<<< HEAD

	// 글저장insert 메서드
	void insertBoard(BoardDto boardDto);
=======
	//게시글작성
	void insertwrite(BoardDto board);



	
>>>>>>> 460304782af17c227702360b2e841954ad0105a1

=======
	//전체 게시글 정보 조회
	  List<BoardDto> getAllBoards();
	  //글저장insert 메서드
	  void insertBoard(BoardDto boardDto);
	
	
>>>>>>> YS
}
