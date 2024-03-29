package com.gg.gop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {


	private String email; //로그인할 아이디
	private String username;  //웹페이지내에 보여지는 아이디
	private String password;
	private String role; 
	private Boolean deleteYn; // 탈퇴 여부 0이면가입 탈퇴면1
	private String profile;//사진정보
	

	
	  public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }


	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }


	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }


	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }

	    public Boolean getDeleteYn() {
	        return deleteYn;
	    }

	    public void setDeleteYn(Boolean deleteYn) {
	        this.deleteYn = deleteYn;
	    }

		public String getProfile() {
			return profile;
		}

		public void setProfile(String profile) {
			this.profile = profile;
		}

	
}

