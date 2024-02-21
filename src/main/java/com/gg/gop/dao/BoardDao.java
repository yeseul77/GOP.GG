<<<<<<< HEAD
package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.BoardDto;

@Mapper
public interface BoardDao {

	//전체 게시글 정보 조회
	  List<BoardDto> getAllBoards();
	  //글저장insert 메서드
	    void insertBoard(BoardDto boardDto);
	
	
}
=======
package com.gg.gop.dao;
<<<<<<< HEAD

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.BoardDto;

@Mapper
public interface BoardDao {

	// 전체 게시글 정보 조회
	List<BoardDto> getAllBoards();

	// 글저장insert 메서드
	void insertBoard(BoardDto boardDto);

}
>>>>>>> YS
=======
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import com.gg.gop.dto.BoardDto;
//
//@Mapper
//public interface BoardDao {
//
//	// 전체 게시글 정보 조회
//	List<BoardDto> getAllBoards();
//
//	// 글저장insert 메서드
//	void insertBoard(BoardDto boardDto);
//
//}
>>>>>>> YS
