package com.gg.gop.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardDto {
	private int idx; // PK
	private String title;
	private String username; // 글쓴이의 사용자 이름 (닉네임)
	private String content;
	private int viewcount; // 조회수
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime b_date; // 작성 날짜와 시간
	private int likes;
	private String boardtype;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public LocalDateTime getB_date() {
		return b_date;
	}

	public void setB_date(LocalDateTime b_date) {
		this.b_date = b_date;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getBoardtype() {
		return boardtype;
	}

	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

}
