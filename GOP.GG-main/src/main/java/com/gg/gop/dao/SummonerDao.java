package com.gg.gop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SummonerDao { // 소환사 전적 검색

	int saveGamedata(Map gamedata);

	List<Map> getGameInfoFromDB(Map<String, String> paramMap);

	int checkDuplicateKey(@Param("matchId") String matchId, @Param("riotIdGameName") String riotIdGameName);

	Map getGameDataFromDB(Map gamedata);
	
	List<Map> retrieveAllDataFromDB();
}
