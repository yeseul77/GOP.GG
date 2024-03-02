package com.gg.gop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.RecordDto;
import com.gg.gop.service.ChampionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChampionRestController {
	@Autowired
	private ChampionService cSer;
	
	@GetMapping("/champion/searchChampionList")
	public List<ChampionDto> getSearchChampionList(@RequestParam String search) {
		List<ChampionDto> searchCList = cSer.searchcList(search);
		
		return searchCList;
	}
	
	@GetMapping("/champion/lineChampionList")
	public List<RecordDto> getlineChampionList(@RequestParam String line) {
		List<RecordDto> lineList = cSer.lineChampionList(line);
		
		log.info("cList : {}", lineList);
		log.info("line : {}", line);
		
		return lineList;
	}
	
	@PostMapping("/champion/championallinfo")
	public List<RecordDto> getChampionallInfo() {
	    // 적절한 값을 전달하여 쿼리 실행
	    List<RecordDto> rList = cSer.lineRecord();
	    return rList;
	}
	
	@GetMapping("/champion/championlineinfo")
	public List<RecordDto> getChampionInfo(@RequestParam String line) {
		List<RecordDto> lList = cSer.lineInfo(line);
		
		log.info(line);
		return lList;
	}
}
