package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gop.dto.ChampionDto;
import com.gg.gop.service.ChampionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChampionController {

	@Autowired
	private ChampionService cSer;

	@GetMapping("/champion/list")
	public String championList(Model model) throws Exception {
		List<ChampionDto> championList = cSer.allChampion();
		
		// JSON 형태로 변환하여 모델에 추가
		ObjectMapper objectMapper = new ObjectMapper();
		String championListJson = objectMapper.writeValueAsString(championList);
		model.addAttribute("championNames", championListJson);
		
		log.info("{}", championListJson);
		return "champion/championAnalyze";
	}
	
	@GetMapping("/champion/detail")
	public String championInfo(@RequestParam("championName") String championName, Model model) throws Exception {
		log.info("name : {}", championName);
		ChampionDto champion = cSer.findChampion(championName);
		
		log.info("info : {}", champion);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String championNames = objectMapper.writeValueAsString(champion);
		
		model.addAttribute("cName", championNames);
		
		return "champion/championInfo";
	}

}
