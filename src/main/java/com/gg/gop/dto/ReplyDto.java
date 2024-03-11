package com.gg.gop.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReplyDto {
	
	private int rnum;  //pk
	private int rbnum; //fk(원글 번호)
	private String username;
	private String r_contents; //댓글 내용
	private String r_writer;   //작성자id
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp r_date;

}
