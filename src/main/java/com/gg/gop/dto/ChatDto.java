package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
	private String title;
	private String userId;
	private int chatroomId;
	private String champion;
	private String position;
	private String memo;
	private int display;
}
