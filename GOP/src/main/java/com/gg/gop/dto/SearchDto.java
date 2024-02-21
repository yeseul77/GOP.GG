package com.gg.gop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchDto {
	private String colname;
	private String keyword;
	private Integer pageNum;  //1,2,3 ,보여질 페이지 번호
	private Integer listCnt; //10,페이지 당 출력할 게시글 개수
	private Integer startIdx;  //listCnt:10일때, 1page idx:0~, 2page idx:10~
}	
