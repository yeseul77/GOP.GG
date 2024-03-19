package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.gg.gop.dto.BoardDto;


@Mapper
public interface BoardDao {

	boolean deletWrit = false;

	// 전체 게시글 정보 조회
	List<BoardDto> selectBoardList();

	// 글쓰기
	void saveWrite(BoardDto boardDto);

	// 게시판 상세보기
	BoardDto getBoardDetail(int idx);

	// 게시글삭제
	@Delete("delete from board_tb where idx=#{idx}")
	boolean deleteWrite(int idx);

	//@Update("UPDATE board_tb SET viewcount = viewcount + 1 WHERE idx = #{idx}")
	int updateViewsCnt(int idx);

	// 좋아요 레코드수 조회
	@Select("SELECT COUNT(*) FROM like_tb WHERE idx = #{idx} AND username = #{username}")
	int selectLikes(int idx, String username);
	//좋아요 회수
	@Delete("Delete from like_tb where idx = #{idx} and username = #{username}")
	boolean deleteLikes(int idx, String username);
	//좋아요 누름!
	@Insert("Insert into like_tb values(#{idx},#{username})")
	boolean insertLikes(int idx, String username);

	@Select("Select count(*) as likes from like_tb where idx = #{idx}")
	BoardDto SelectUpdateLikes(int idx);
	//좋아요수 업데이트
	@Update("Update like_tb set username = #{likes} where idx = #{idx}")
	boolean updateLikes(int likes, int idx);

}
