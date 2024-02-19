package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gg.gop.dto.SummonerDto;
import com.gg.gop.dto.SummonerGameDataDto;
import com.gg.gop.service.SummonerService;

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
        return "championInfo"; // 챔피언 정보를 보여줄 뷰의 이름으로 변경해주세요.
    }
}