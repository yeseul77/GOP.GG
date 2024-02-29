package com.gg.gop.dto;


import lombok.Data;


@Data
public class MailDto {


    private String title;   // 이메일 제목
    private String sendto; // 수신자 이메일 주소
    private String message; // 이메일 내용
	
    
    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendto() {
		return sendto;
	}
	public void setSendto(String sendto) {
		this.sendto = sendto;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
