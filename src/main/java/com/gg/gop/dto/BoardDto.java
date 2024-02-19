package com.gg.gop.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	
	//회원제게시판 ,비회원은 읽기만가능 
	//글쓰기한 회원은 닉네임이 보여짐 가입한 이메일 X
	private int b_bno; //PK
	private String b_title;
	private String b_contents; 
	private String b_writer; //글쓰기
	private String username;//작성자 닉네임
	private int b_views; //조회수
	private LocalDateTime b_date; 

}
