package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
	// --------------- 탑 ~ 서폿 버튼용 ---------------------
	private String championName;
	private String winRate;
	private String pickRate;
	private String benRate;
	private String tier; // 보류
	private String position;
}
