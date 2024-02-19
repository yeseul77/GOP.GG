package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Builder // 빌더후 @AllArgsConstructor을 이용해 객체 생성함.
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {


	private String email; //로그인할 아이디
	private String username;  //웹페이지내에 보여지는 아이디
	private String password;
	private String role; 
	private Boolean deleteYn; // 탈퇴 여부 0이면가입 탈퇴면1
	// private String m_tier; // 소환사 티어
	// private LocalDateTime modifiedDate; // 최종 수정일시
	
}
