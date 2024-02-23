package com.gg.gop.dto;

import lombok.Data;

@Data
public class MailDto {
	
	

    private String title;   // 이메일 제목
    private String sendto; // 수신자 이메일 주소
    private String message; // 이메일 내용

}
