package com.gg.gop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SummonerDao { // 소환사 전적 검색

	int saveinfodata(Map<String, Object> gamedata);

	int saveteamsdata(Map<String, Object> gamedata);

	List<Map> getGameInfoFromDB(Map<String, String> paramMap);
	
	int checkDuplicateKey(@Param("matchId") String matchId, @Param("riotIdGameName") String riotIdGameName);

	List<Map> retrieveAllDataFromDB();

	Map getGameDataFromDB(Map gamedata);

	
}
