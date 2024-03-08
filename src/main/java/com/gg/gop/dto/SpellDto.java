package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellDto {
	private String spell1Id;
	private String spell2Id;
	private int playCount;
	private double pickRate;
	private double winRate;
}
