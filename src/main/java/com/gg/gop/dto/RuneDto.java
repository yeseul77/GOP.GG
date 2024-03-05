package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuneDto {
	// 챔피언들의 룬 정보
	private String championName;
	private String championName_KR;
	private String teamPosition;
	private String tier1;
	private String main_name;
	private String main_perks1;
	private String main_perks2;
	private String main_perks3;
	private String main_perks4;
	private String sub_name;
	private String sub_perks1;
	private String sub_perks2;
	private String stat_perks1;
	private String stat_perks2;
	private String stat_perks3;
}