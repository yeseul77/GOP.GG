package com.gg.gop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gg.gop.dto.SummonerDto;
import com.gg.gop.service.SummonerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SummonerRestController {
	@Autowired
	private SummonerService summonerService;
	@PostMapping("/summonerSearch")
	public List<Map> summonerSearch(SummonerDto gameName, SummonerDto tagLine) throws JsonProcessingException {
		List<Map> dbGameInfoList = summonerService.getGameInfoFromDB(gameName.getGameName(), tagLine.getTagLine());
		System.out.println("/summonerSearch"+dbGameInfoList);
		return dbGameInfoList;
	}

	@GetMapping("/summonerSaveData")
	public int saveData(@RequestBody List<Map<String, Object>> mapList)throws JsonProcessingException {
	    int savedCount = 0; // 저장된 데이터의 수를 세기 위한 변수
	    log.info("savedCount : "+savedCount);
	    

	    try {
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
	            }
	        }
	    } catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        // 클라이언트에게 적절한 오류 메시지를 반환하거나 로깅하여 문제를 식별할 수 있도록 합니다.
	    }
	    log.info("savedCount : "+savedCount);
	    return savedCount; // 저장된 데이터의 수 반환
	}

	@PostMapping("/summonerUpdate")
	public List<Map> summonerUpdate(SummonerDto gameName, SummonerDto tagLine) throws JsonProcessingException {
		String puuid = summonerService.puuid(gameName.getGameName(), tagLine.getTagLine());
		List<String> matchIdList = summonerService.matchIdList(puuid);
		List<Map> gameInfoList = summonerService.gameInfoList(matchIdList);
		log.info("업데이트 ㅆㅂ");
		// 중복 여부 확인
		boolean hasDuplicate = gameInfoList.stream()
				.anyMatch(gameInfo -> gameName.getGameName().equals(gameInfo.get("riotIdGameName"))
						&& tagLine.getTagLine().equals(gameInfo.get("riotIdTagline")));

		if (!hasDuplicate) {
			List<Map> dbGameInfoList = summonerService.getGameInfoFromDB(gameName.getGameName(), tagLine.getTagLine());
			gameInfoList.addAll(dbGameInfoList);
			summonerService.saveAndRetrieveGamedata(gameInfoList);
		}
		return gameInfoList;
	}
}
