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
    private long gameStartTimestamp;
    private int queueId;
    private String riotIdGameName;
    private String riotIdTagline;
    private int summonerLevel;
    private String gameMode;
    private int teamId;
    private boolean win;
    private int teamchampionkills;
    private boolean teamwin;
    private int championId;
    private int pickTurn;
    private String championName;
    private int kills;
    private int deaths;
    private int assists;
    private double kda;
    private String lane;
    private int totalDamageDealtToChampions;
    private int totalDamageTaken;
    private int totalMinionsKilled;
	private List<Map> gamedataList;
}