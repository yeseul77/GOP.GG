package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.dto.AramDto;
import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.RecordDto;
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
	
	@GetMapping("/aram/lineChampionList")
	public List<RecordDto> getlineChampionList(@RequestParam String line) {
		List<RecordDto> lineList = aSer.lineChampionList(line);
		
		log.info("cList : {}", lineList);
		log.info("line : {}", line);
		
		return lineList;
	}
	
	@PostMapping("/aram/championallinfo")
	public List<AramDto> getChampionallInfo() {
	    // 적절한 값을 전달하여 쿼리 실행
	    List<AramDto> rList = aSer.lineRecord();
//	    log.info("rlist: {}",rList);
	    return rList;
	}
	
	@GetMapping("/aram/championlineinfo")
	public List<RecordDto> getChampionInfo(@RequestParam String line) {
		List<RecordDto> lList = aSer.lineInfo(line);
		
		log.info(line);
		return lList;
	}
}
