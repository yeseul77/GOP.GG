package com.gg.gop.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class MemberDto {
	
	
	private Long m_no; //회원 고유 번호
	private String m_id; // 로그인 ID (PK) =  이메일형식
	private String m_pw; // 비밀번호
	private String m_name; // 소환사(게임명)
	private Boolean deleteYn; // 삭제 여부
	private LocalDateTime modifiedDate; // 최종 수정일시

}
