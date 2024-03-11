package com.gg.gop.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	public String SummonerId(String puuid) {
		String summonerId = webClient.getSummonerId(puuid);
//		log.info(summonerId);
		return summonerId;
	}

	public List<Map<String, Object>> SummonerLeagueInfo(String summonerId) {
		return webClient.getSummonerLeagueInfo(summonerId);
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
//				log.info("Processing game data: {}", gameData);
				if (!hasDuplicateKey(gameData)) {
					// 게임 정보 저장
					sDao.saveinfodata((Map<String, Object>) gameData.get("info"));
					// 팀 정보 저장
					sDao.saveteamsdata((Map<String, Object>) gameData.get("teams"));
					// 리그 정보 저장
					sDao.saveLeagueInfo((Map<String, Object>) gameData.get("SummonerLeagueInfo"));
					// 저장된 데이터 조회하지 않고, 저장된 데이터를 그대로 사용
					savedDataList.add(gameData);
//					log.info("gameData{}",gameData.get("info"));
				} else {
					hasDuplicateKey = true;
				}
			} catch (Exception e) {
//				log.error("Exception occurred:", e);
			}
		}

		if (hasDuplicateKey) {
			
		}
//		log.info("savedDataList : {}", savedDataList);
		return savedDataList;
	}

	private boolean hasDuplicateKey(Map<String, Object> gameData) {
		String matchId = (String) gameData.get("matchId");
		String riotIdGameName = (String) gameData.get("riotIdGameName");
		int count = sDao.checkDuplicateKey(matchId, riotIdGameName);
//		log.info("Duplicate count for matchId {} and riotIdGameName {}: {}", matchId, riotIdGameName, count);
		return count > 0;
	}

	public List<Map<String, Object>> getCombinedGameData(String gameName, String tagLine) {
		String puuid2=puuid(gameName, tagLine);
		String summonerId2=SummonerId(puuid2);
		List<Map<String, Object>> gameInfoList = sDao.getGameInfoFromDB(gameName, tagLine);
		List<Map<String, Object>> gameTeamsList = sDao.getGameTeamsFromDB(gameName, tagLine);
		List<Map<String, Object>> leagueInfoList = sDao.getLeagueInfoFromDB(gameName, tagLine);
		List<String> setmatchidList = new ArrayList<>();
		List<String> matchidList = new ArrayList<>();
		List<String> summonerIdList = new ArrayList<>();
		Map<String, List<Map<String, Object>>> gameDataByMatchId = new HashMap<>();
		Map<String, List<Map<String, Object>>> gameTeamsByMatchId = new HashMap<>();
		Map<String, List<Map<String, Object>>> leagueInfoBySummonerId = new HashMap<>();
		int j = 0;
		for (Map<String, Object> gameInfo : gameInfoList) {
			String matchId = (String) gameInfo.get("matchId");
			setmatchidList.add(matchId);
			gameDataByMatchId.computeIfAbsent(matchId, k -> new ArrayList<>()).add(gameInfo);
		}
//		log.info("gameDataByMatchId={}",gameDataByMatchId);
		for (int i = 1; i < setmatchidList.size(); i++) {
			if (!(setmatchidList.get(i - 1).equals(setmatchidList.get(i)))) {
				matchidList.add(setmatchidList.get(i - 1));
			}
		}
		for (Map<String, Object> gameTeams : gameTeamsList) {
			String matchId = (String) gameTeams.get("matchId");
			j++;
			gameTeamsByMatchId.computeIfAbsent(matchId, k -> new ArrayList<>()).add(gameTeams);
		}
		if(leagueInfoList!=null) {
			for (Map<String, Object> leagueInfo : leagueInfoList) {
				String summonerId = (String) leagueInfo.get("summonerId");
				summonerIdList.add(summonerId);
				leagueInfoBySummonerId.computeIfAbsent(summonerId, k -> new ArrayList<>()).add(leagueInfo);
//				log.info("leagueInfo{}",leagueInfo);
			}
		}else {
			for (int i=0;i<5;i++){
				String puuid=puuid(gameName, tagLine);
				String summonerId=SummonerId(puuid);
				summonerIdList.add(summonerId);
			}
		}
		List<Map<String, Object>> combinedDataList = new ArrayList<>();
		log.info("{}", gameDataByMatchId.keySet());
		for (int i = 0; i < matchidList.size(); i++) {
			String matchId = matchidList.get(i);
//			String summonerId = summonerIdList.get(i);
			Map<String, Object> combinedData = new HashMap<>();
			List<Map<String, Object>> gameInfoData = gameDataByMatchId.get(matchId);
			List<Map<String, Object>> gameTeamsData = gameTeamsByMatchId.getOrDefault(matchId, new ArrayList<>());
			List<Map<String, Object>> leagueInfoData = new ArrayList<>();
			// 매치에 참여한 소환사들의 리그 정보를 가져옴
			for (String summonerId : leagueInfoBySummonerId.keySet()) {
				List<Map<String, Object>> summonerLeagueInfo = leagueInfoBySummonerId.get(summonerId);
				for (Map<String, Object> leagueInfo : summonerLeagueInfo) {
					if (summonerId.equals(leagueInfo.get("summonerId"))) {
						leagueInfoData.add(leagueInfo);
						break;
					}
				}
			}
//			log.info("gameInfodata={}",gameInfoData);
			combinedData.put("summonerId",summonerId2);
			combinedData.put("info", gameInfoData);
			combinedData.put("teams", gameTeamsData);
			combinedData.put("leagueInfo", leagueInfoData);
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

//	public int savebansdata(Map<String, Object> bans) {
//		String matchId = (String) bans.get("matchId");
//		if (matchId != null) {
//			return sDao.savebansdata(bans);
//		} else {
//			log.error("matchId is null. Cannot insert data.");
//			return 0;
//		}
//	}

	public int saveLeagueInfo(Map<String, Object> leagueInfo, String gameName, String tagLine) {
	    String summonerId = (String) leagueInfo.get("summonerId");
	    if (summonerId != null) {
	        int rowsAffected = sDao.saveLeagueInfo(leagueInfo); // 일단 삽입을 시도합니다.
	        if (rowsAffected == 0) {
	            // 삽입이 실패한 경우, 이미 존재하는 summonerId에 대한 데이터이므로 업데이트를 시도합니다.
	            rowsAffected = sDao.updateLeagueInfo(leagueInfo);
	        }
	        return rowsAffected;
	    } else {
	    	String puuid=puuid(gameName, tagLine);
	    	String nullsummonerId=SummonerId(puuid);
	    	int rowAffected =sDao.saveLeagueNull(nullsummonerId);
	    	if(rowAffected==0) {
	            rowAffected = sDao.updateLeagueInfo(leagueInfo);
	    	}
//	        log.error("summonerId is null. Cannot insert or update data.");
	        return rowAffected;
	    }
	
	}

//	public List<ChampionRanking> getChampionRanking() {
//		return sDao.getChampionRankingFromDB();
//	}
	
	public void processbulider(String tier, String championName, int Damage,int champExp) throws IOException {
		String filePath="src/main/resources/static/python/damagecheck.py";
		ProcessBuilder processBuilder=new ProcessBuilder("python",filePath);
		Process process=processBuilder.start();
		InputStream inputStream=process.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
		    System.out.println(line);
		}
	}
}