package com.gg.gop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@Getter
public class MemberDto {

	private Long u_no; // 회원 번호 (PK)
	private String u_id; // 로그인 ID
	private String u_pw; // 비밀번호
	private String u_name; // 이름
	private Boolean deleteYn; // 삭제 여부
	private LocalDateTime modifiedDate; // 최종 수정일시

	public void clearPassword() {
		this.u_pw = "";
	}

	public void encodingPassword(PasswordEncoder passwordEncoder) {
		if (StringUtils.isEmpty(u_pw)) {
			return;
		}
		u_pw = passwordEncoder.encode(u_pw);
	}

}
