//package com.gg.gop.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.gg.gop.dto.SummonerDto;
//import com.gg.gop.service.SummonerService;
//import com.google.gson.Gson;
//
//@RestController
//public class SummonerRestController {
//	@Autowired
//	private SummonerService summonerService;
//
//	@PostMapping("/summonerSearch")
//	public List<Map> matchId(SummonerDto gameName, SummonerDto tagLine) {
//		String puuid = summonerService.puuid(gameName.getGameName(), tagLine.getTagLine());
//		List<String> matchIdList = summonerService.matchIdList(puuid);
//		List<Map> gameInfoList = summonerService.gameInfoList(matchIdList);
//
//		// 중복 여부 확인
////		boolean hasDuplicate = gameInfoList.stream()
//				.anyMatch(gameInfo -> gameName.getGameName().equals(gameInfo.get("riotIdGameName"))
//						&& tagLine.getTagLine().equals(gameInfo.get("riotIdTagline")));
//
//		if (!hasDuplicate) {
//			List<Map> dbGameInfoList = summonerService.getGameInfoFromDB(gameName.getGameName(), tagLine.getTagLine());
//			gameInfoList.addAll(dbGameInfoList);
//		}
//
//		return gameInfoList;
//	}
//
//	@PostMapping("/summonerSaveData")
//	public int saveData(String gamedataList) throws JsonMappingException, JsonProcessingException {
//		Gson gson = new Gson();
//		List<Map> map1 = gson.fromJson(gamedataList, ArrayList.class);
//		System.out.println(map1);
//		int data = summonerService.saveGamedata(map1);
//		System.out.println("rest옴" + data);
//		return data;
//	}
//
//}
