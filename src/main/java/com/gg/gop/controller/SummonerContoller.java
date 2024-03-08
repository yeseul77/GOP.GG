package com.gg.gop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gg.gop.service.SummonerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SummonerContoller {
	@Autowired
	private SummonerService summonerService;

	@GetMapping("/")
	public String summoner() {
		return "index";
	}

	@GetMapping("/championSearchForm")
	public String championSearchForm(Model model) {
//		List<ChampionRanking> rankingList = summonerService.getChampionRanking();
//	    model.addAttribute("rankingList", rankingList);
		return "championSearchForm"; // 챔피언 정보 페이지로 이동하는 뷰 이름을 반환합니다.
	}

	@GetMapping("/getChampionInfo")
	public String getChampionInfo(@RequestParam("championName") String championName, Model model) {
		return "championInfo"; // 챔피언 정보를 보여줄 뷰
	}

	@GetMapping("/summonerSearch")
	public String summonerSearch(@RequestParam String gameName, @RequestParam String tagLine, Model model) {
	    List<Map<String, Object>> combinedGameData = summonerService.getCombinedGameData(gameName, tagLine);
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
	            if (playerName.equals(gameName) && playerTagLine.equals(tagLine)) {
	                filteredGameData.add(gameData);
	                gameCount++;
	                break; // 하나의 게임에는 동일한 플레이어가 여러 번 나올 수 있으므로 한 번 추가하면 더 이상 처리하지 않음
	            }
	        }
	    }
	    model.addAttribute("gameInfoList", filteredGameData);

	    return "result";
	}
}