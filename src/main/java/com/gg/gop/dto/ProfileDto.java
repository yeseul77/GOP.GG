package com.gg.gop.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProfileDto {

	private String email;
	private String username;
	private String profile;
	private String currentpwd;// 현재비번
	private String newpwd;// 바꿀비번
	private MultipartFile attachments;
	private String oriFileName;
	private String sysFileName;
	private String filePath;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurrentpwd() {
		return currentpwd;
	}

	public void setCurrentpwd(String currentpwd) {
		this.currentpwd = currentpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

// Getter and Setter methods for 'oriFileName'
	public String getOriFileName() {
		return oriFileName;
	}

	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}

	public String getSysFileName() {
		return sysFileName;
	}

	public void setSysFileName(String sysFileName) {
		this.sysFileName = sysFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public MultipartFile getAttachments() {
		return attachments;
	}

	public void setAttachments(MultipartFile attachments) {
		this.attachments = attachments;
	}

	public String getEmaile() {
		return email;
	}

	public void setEmaile(String emaile) {
		this.email = emaile;
	}
}
