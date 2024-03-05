package com.gg.gop.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gop.dao.SummonerDao;
import com.gg.gop.dto.SummonerDto;
import com.gg.gop.service.SummonerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SummonerRestController {
	@Autowired
	private SummonerService summonerService;
	@Autowired
	SummonerDao sDao;

	@PostMapping("/summonerSearch2")
	public List<Map<String, Object>> summonerSearch2(SummonerDto gameName, SummonerDto tagLine) {
		List<Map<String, Object>> combinedGameData = summonerService.getCombinedGameData(gameName.getGameName(),
				tagLine.getTagLine());
		List<Map<String, Object>> filteredGameData = new ArrayList<>();
		

		// 최근 10게임만 추출하고, 검색한 소환사 이름과 태그 라인이 일치하는 게임 정보만 필터링
		int gameCount = 0;
		for (Map<String, Object> gameData : combinedGameData) {
			if (gameCount >= 100) {
				break; // 최대 10게임까지만 처리
			}

			// 게임 데이터에서 플레이어 정보 추출
			List<Map<String, Object>> playerInfoList = (List<Map<String, Object>>) gameData.get("info");
			for (Map<String, Object> playerInfo : playerInfoList) {
				String playerName = (String) playerInfo.get("riotIdGameName");
				String playerTagLine = (String) playerInfo.get("riotIdTagline");
				
				// 검색한 소환사 이름과 태그 라인이 일치하는 플레이어 정보인 경우에만 추가
				if (playerName.equals(gameName.getGameName()) && playerTagLine.equals(tagLine.getTagLine())) {
					filteredGameData.add(gameData);
					gameCount++;
					break; // 하나의 게임에는 동일한 플레이어가 여러 번 나올 수 있으므로 한 번 추가하면 더 이상 처리하지 않음
				}
			}
		}

		return filteredGameData;
	}

	@PostMapping("/summonerSaveData")
	public int saveData(@RequestParam("encodedData") String encodedData,String gameName, String tagLine) throws JsonProcessingException {
		int savedCount = 0; // 저장된 데이터의 수를 세기 위한 변수
		ObjectMapper objectMapper = new ObjectMapper();
		log.info("savedCount : " + savedCount);

		try {
			// URL 디코딩하여 JSON 문자열을 다시 복원
			String decodedData = URLDecoder.decode(encodedData, "UTF-8");
			// JSON 문자열을 List<Map<String, Object>>으로 변환
			List<Map<String, Object>> mapList = objectMapper.readValue(decodedData,
					new TypeReference<List<Map<String, Object>>>() {
					});

			for (Map<String, Object> response : mapList) {
				List<Map<String, Object>> infoList = (List<Map<String, Object>>) response.get("info");
				for (Map<String, Object> gameinfo : infoList) {
					if (summonerService.saveinfodata(gameinfo) > 0) { // saveinfodata 메소드가 성공하면
						savedCount++; // 저장된 데이터 수 증가
					}
				}

				List<Map<String, Object>> teamsList = (List<Map<String, Object>>) response.get("teams");
				for (Map<String, Object> gameteams : teamsList) {
					if (summonerService.saveteamsdata(gameteams) > 0) { // saveteamsdata 메소드가 성공하면
						savedCount++; // 저장된 데이터 수 증가
					}

					// 팀에 대한 bans 정보 가져와서 처리
//					List<Map<String, Object>> bansList = (List<Map<String, Object>>) gameteams.get("bans");
//					for (Map<String, Object> bans : bansList) {
//						if (summonerService.savebansdata(bans) > 0) { // savebansdata 메소드가 성공하면
//							savedCount++; // 저장된 데이터 수 증가
//						}
//					}
				}
				List<Map<String, Object>> leagueInfoList = (List<Map<String, Object>>) response.get("leagueInfo");
				for (Map<String, Object> leagueInfo : leagueInfoList) {
					if (leagueInfo != null) {
						if (summonerService.saveLeagueInfo(leagueInfo,gameName, tagLine) > 0) {
							savedCount++; // 저장된 데이터 수 증가
						}

					}
				}
			}
		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
			// 클라이언트에게 적절한 오류 메시지를 반환하거나 로깅하여 문제를 식별할 수 있도록 합니다.
		}
		log.info("savedCount : " + savedCount);
		return savedCount; // 저장된 데이터의 수 반환
	}

	@PostMapping("/summonerUpdate")
	public List<Map<String, Object>> summonerUpdate(@RequestParam String gameName, @RequestParam String tagLine)
			throws JsonProcessingException {
		String puuid = summonerService.puuid(gameName, tagLine);
		List<String> matchIdList = summonerService.matchIdList(puuid);
		List<Map<String, Object>> newGameDataList = summonerService.gameInfoList(matchIdList);
		log.info("Updating game data...");

		String summonerId = summonerService.SummonerId(puuid);
		List<Map<String, Object>> leagueInfo = summonerService.SummonerLeagueInfo(summonerId);

		// 데이터베이스에 저장된 최신 게임 데이터 가져오기
		List<Map<String, Object>> dbGameDataList = summonerService.getCombinedGameData(gameName, tagLine);

		// 중복 여부 확인
		boolean hasDuplicate = dbGameDataList.stream()
				.anyMatch(gameInfo -> gameName.equals(gameInfo.get("riotIdGameName"))
						&& tagLine.equals(gameInfo.get("riotIdTagline")));

		log.info("hasDuplicate:" + hasDuplicate);
		if (!hasDuplicate) {
			for (Map<String, Object> gameData : newGameDataList) {
				gameData.put("leagueInfo", leagueInfo);
			}

			// 최신 게임 데이터 저장 및 반환
			newGameDataList.addAll(dbGameDataList);
			newGameDataList = summonerService.saveAndRetrieveGameData(newGameDataList);
		}
//	    log.info("newGameDataList:" + newGameDataList);
		return newGameDataList;
	}
}