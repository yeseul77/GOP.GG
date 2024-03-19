package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.dto.AramDto;
import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.ItemDto;
import com.gg.gop.dto.RecordDto;
import com.gg.gop.dto.RuneDto;
import com.gg.gop.dto.SpellDto;
import com.gg.gop.service.AramService;
import com.gg.gop.service.ChampionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class AramRestController {

	@Autowired   
	private AramService aSer;
	
	@GetMapping("/aram/searchChampionList")
	public List<ChampionDto> getSearchChampionList(@RequestParam String search) {
		List<ChampionDto> searchCList = aSer.searchcList(search);
		
		return searchCList;
	}
	
	
	@PostMapping("/aram/championallinfo")
	public List<AramDto> getChampionallInfo() {
	    // 적절한 값을 전달하여 쿼리 실행
	    List<AramDto> rList = aSer.lineRecord();
//	    log.info("rlist: {}",rList);
	    return rList;
	}
	
	@GetMapping("/aram/{championName}/rune")
	public List<RuneDto> getChampionRune(@PathVariable String championName) {
		// 챔피언 이름을 사용하여 룬 정보를 가져오는 서비스 메서드 호출
		List<RuneDto> rDto = aSer.getChampionRune(championName);
		return rDto;
	}
	
	
	@GetMapping("/aram/{championName}/rates")
	public AramDto getRates(@PathVariable String championName) {
		AramDto rateList = aSer.getRates(championName);
//		log.info("rate : {}", rateList);
//		log.info("champion : {}", championName);

		
		return rateList;
	}
	
	@GetMapping("/aram/{championName}/spell")
	public List<SpellDto> getSpells(@PathVariable String championName) {
		List<SpellDto> sList = aSer.getSpells(championName);
		log.info("{}",sList);
		return sList;
	}
	
	
	@GetMapping("/aram/{championName}/item")
	public List<ItemDto> getChampionItem(@PathVariable String championName) {
		List<ItemDto> iList = aSer.getChampionItem(championName);
		return iList;
	}
}
