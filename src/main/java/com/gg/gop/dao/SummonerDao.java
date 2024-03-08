package com.gg.gop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SummonerDao { // 소환사 전적 검색

	int saveinfodata(Map<String, Object> gamedata);

	int saveteamsdata(Map<String, Object> gamedata);
	
//	int savebansdata(Map<String, Object> bans);
	
	List<Map<String, Object>> getGameDataFromDB();

	List<Map<String, Object>> getGameInfoFromDB(String gameName, String tagLine);

	List<Map<String, Object>> getGameTeamsFromDB(String gameName, String tagLine);

	List<Map<String, Object>> getLeagueInfoFromDB(String gameName, String tagLine);

	int checkDuplicateKey(String matchId, String riotIdGameName);
  
	List<Map> retrieveAllDataFromDB();

	int saveLeagueInfo(Map<String, Object> leagueInfo);
	
	int saveLeagueNull(String summonerId);
	
	int updateLeagueNull(String summonerId);

	int updateLeagueInfo(Map<String, Object> leagueInfo);


//	List<ChampionRanking> getChampionRankingFromDB();

}