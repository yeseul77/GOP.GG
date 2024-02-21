package com.gg.gop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gg.gop.dao.SummonerDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SummonerService {

	@Autowired
	SummonerDao sDao;

	@Autowired
	SummonerWebClientService webClient;

	public String puuid(String gameName, String tagLine) { // puuid 가져오기
		String puuid = webClient.getPuuidInfo(gameName, tagLine);
		return puuid;
	}

	public List<String> matchIdList(String puuid) { // matchId 가져오기
		List<String> matchIdList = webClient.getMatchIdInfo(puuid);
		return matchIdList;
	}

	public List<Map<String, Object>> gameInfoList(List<String> matchId) { // 최근전적 정보 가져오기
		List<Map<String, Object>> gameInfoList = new ArrayList<>();
		for (int i = 0; i < matchId.size(); i++) {
			Map gameInfo = webClient.getGameInfo(matchId.get(i));
			gameInfoList.add(gameInfo);
		}
		return gameInfoList;
	}

	public List<Map<String, Object>> saveAndRetrieveGameData(List<Map<String, Object>> gameDataList)
			throws JsonProcessingException {
		List<Map<String, Object>> savedDataList = new ArrayList<>();
		boolean hasDuplicateKey = false;

		for (Map<String, Object> gameData : gameDataList) {
			try {
				log.info("Processing game data: {}", gameData);
				if (!hasDuplicateKey(gameData)) {
					// 게임 정보 저장
					sDao.saveinfodata((Map<String, Object>) gameData.get("info"));
					// 팀 정보 저장
					sDao.saveteamsdata((Map<String, Object>) gameData.get("teams"));

					// 저장된 데이터 조회하지 않고, 저장된 데이터를 그대로 사용
					savedDataList.add(gameData);
				} else {
					hasDuplicateKey = true;
				}
			} catch (Exception e) {
				log.error("Exception occurred:", e);
			}
		}

		if (hasDuplicateKey) {
			
		}

		return savedDataList;
	}

	private boolean hasDuplicateKey(Map<String, Object> gameData) {
		String matchId = (String) gameData.get("matchId");
		String riotIdGameName = (String) gameData.get("riotIdGameName");
		int count = sDao.checkDuplicateKey(matchId, riotIdGameName);
		log.info("Duplicate count for matchId {} and riotIdGameName {}: {}", matchId, riotIdGameName, count);
		return count > 0;
	}

	public List<Map<String, Object>> getCombinedGameData(String gameName, String tagLine) {
		List<Map<String, Object>> gameInfoList = sDao.getGameInfoFromDB(gameName, tagLine);
		List<Map<String, Object>> gameTeamsList = sDao.getGameTeamsFromDB(gameName, tagLine);

		Map<String, List<Map<String, Object>>> gameDataByMatchId = new HashMap<>();
		Map<String, List<Map<String, Object>>> gameTeamsByMatchId = new HashMap<>();

		for (Map<String, Object> gameInfo : gameInfoList) {
			String matchId = (String) gameInfo.get("matchId");
			gameDataByMatchId.computeIfAbsent(matchId, k -> new ArrayList<>()).add(gameInfo);
		}
		for (Map<String, Object> gameTeams : gameTeamsList) {
			String matchId = (String) gameTeams.get("matchId");
			gameTeamsByMatchId.computeIfAbsent(matchId, k -> new ArrayList<>()).add(gameTeams);
		}
		List<Map<String, Object>> combinedDataList = new ArrayList<>();

		for (String matchId : gameDataByMatchId.keySet()) {
			Map<String, Object> combinedData = new HashMap<>();
			List<Map<String, Object>> gameInfoData = gameDataByMatchId.get(matchId);
			List<Map<String, Object>> gameTeamsData = gameTeamsByMatchId.getOrDefault(matchId, new ArrayList<>());
			combinedData.put("info", gameInfoData);
			combinedData.put("teams", gameTeamsData);
			combinedDataList.add(combinedData);
		}

		return combinedDataList;
	}

	public int saveinfodata(Map<String, Object> info) {
		String matchId = (String) info.get("matchId");
		if (matchId != null) {
			return sDao.saveinfodata(info);
		} else {
			log.error("matchId is null. Cannot insert data.");
			return 0; // 또는 적절한 오류 코드를 반환하십시오.
		}
	}

	public int saveteamsdata(Map<String, Object> teams) {
		String matchId = (String) teams.get("matchId");
		if (matchId != null) {
			return sDao.saveteamsdata(teams);
		} else {
			log.error("matchId is null. Cannot insert data.");
			return 0; 
		}
	}

	public int savebansdata(Map<String, Object> bans) {
		String matchId = (String) bans.get("matchId");
		if (matchId != null) {
			return sDao.savebansdata(bans);
		} else {
			log.error("matchId is null. Cannot insert data.");
			return 0; 
		}
	}

//	public List<ChampionRanking> getChampionRanking() {
//		return sDao.getChampionRankingFromDB();
//	}
}