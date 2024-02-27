package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.BoardDto;

@Mapper
public interface BoardDao {

	// 전체 게시글 정보 조회
	List<BoardDto> getAllBoards();
	//게시글작성
	void insertwrite(BoardDto board);



	

}
