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
    private String summonerId;
    private int summonerLevel;
    private String gameMode;
    private int teamId;
    private boolean win;
    private int teamchampionkills;
    private int teambaronkills;
    private int teamdragonkills;
    private int teamhordekills;
    private int teaminhibitorkills;
    private int teamriftHeraldkills;
    private int teamtowerkills;
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
    private String gameVersion;
    private String summoner1Id;
    private String summoner2Id;
    private String item0;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String perks1;
    private String perks2;
	private List<Map> gamedataList;
}