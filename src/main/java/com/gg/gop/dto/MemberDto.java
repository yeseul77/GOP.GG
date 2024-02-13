package com.gg.gop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain=true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Data Bean(dto,entity,vo)

public class MemberDto {

	private String m_no;
	private String m_id; 
	private String m_pw; // 비밀번호
	private String m_name; // 소환사(게임명)
	//private String m_tier; // 소환사 티어

	// 관리자 권한
	//private String role; // 권한
	//private Boolean deleteYn; // 탈퇴 여부 0이면가입 탈퇴면1
	// private LocalDateTime modifiedDate; // 최종 수정일시

	// 모든 필드를 포함한 생성자
	public MemberDto(String m_no, String m_id, String m_pw, String m_name) {
		this.m_no = m_no;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
	}

	// m_no에 대한 getter 메서드
	public String getM_no() {
		return m_no;
	}

	// m_no에 대한 setter 메서드
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}

	// m_id에 대한 getter 메서드
	public String getM_id() {
		return m_id;
	}

	// m_id에 대한 setter 메서드
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	// m_pw에 대한 getter 메서드
	public String getM_pw() {
		return m_pw;
	}

	// m_pw에 대한 setter 메서드
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	// m_name에 대한 getter 메서드
	public String getM_name() {
		return m_name;
	}

	// m_name에 대한 setter 메서드
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
}
