package com.gg.gop.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummonerGameDataDto {
	private String matchId;
	private int gameDuration;
	private int queueId;
	private String riotIdGameName;
	private List<Map> gamedataList;
}
