package com.gg.gop.dto;

import lombok.Data;

@Data
public class AdminDto {
	//권한 1,2,3을 주는식으로 
	private Long a_id;                
	private Long a_pw;                
    private String auth;			//권한

}
