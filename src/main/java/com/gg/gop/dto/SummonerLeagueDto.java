package com.gg.gop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummonerLeagueDto {

	private String id;
	private String summonerId;
	private String queueType;
	private String tier;
	private String rank;
	private int leaguePoints;
	private int wins;
	private int losses;
}
