package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AramDto {
	
	private String championName;
	private double win_rate;
	private double pickRate;
	private int DPM;
	private String tier; // 보류
	private int pentakills;
}
