package com.gg.gop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Getter
@Setter
public class MemberDto {
	
	
	private String m_id; // 로그인 ID  =  이메일형식
	private String m_pw; // 비밀번호
	private String m_name; // 소환사(게임명)
	private String m_tier; // 소환사 티어 
	
	// 관리자 권한
	private String role; //권한  
	private Boolean deleteYn; // 탈퇴 여부 0이면가입 탈퇴면1
    //private LocalDateTime modifiedDate; // 최종 수정일시

	}

