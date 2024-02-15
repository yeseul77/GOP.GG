package com.gg.gop.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gop.dto.SummonerDto;
import com.gg.gop.service.SummonerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SummonerRestController {
	@Autowired
	private SummonerService summonerService;

	@PostMapping("/summonerSearch")
	public List<Map<String, Object>> summonerSearch(SummonerDto gameName, SummonerDto tagLine) {
		return summonerService.getCombinedGameData(gameName.getGameName(), tagLine.getTagLine());
	}

	@PostMapping("/summonerSaveData")
	public int saveData(@RequestParam("encodedData") String encodedData) throws JsonProcessingException {
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
	public List<Map> summonerUpdate(@RequestParam String gameName,@RequestParam String tagLine) throws JsonProcessingException {
	    String puuid = summonerService.puuid(gameName, tagLine);
	    List<String> matchIdList = summonerService.matchIdList(puuid);
	    List<Map> newGameDataList = summonerService.gameInfoList(matchIdList);
	    log.info("Updating game data...");

	    // 데이터베이스에 저장된 최신 게임 데이터 가져오기
	    List<Map<String, Object>> dbGameDataList = summonerService.getCombinedGameData(gameName, tagLine);

	    // 중복 여부 확인
	    boolean hasDuplicate = dbGameDataList.stream()
	            .anyMatch(gameInfo -> gameName.equals(gameInfo.get("riotIdGameName"))
	                    && tagLine.equals(gameInfo.get("riotIdTagline")));

	    log.info("hasDuplicate:" + hasDuplicate);
	    if (!hasDuplicate) {
	        newGameDataList.addAll(dbGameDataList);

	        // 최신 게임 데이터 저장 및 반환
	        newGameDataList = summonerService.saveAndRetrieveGameData(newGameDataList);
	    }
	    log.info("newGameDataList:" + newGameDataList);
	    return newGameDataList;
	}
}
