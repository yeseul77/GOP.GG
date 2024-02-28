package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.dto.MatchDto;
import com.gg.gop.service.MatchService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MatchRestController {
	@Autowired
	MatchService mSer;
	
	@GetMapping("/getData")
	public List<String> getData(MatchDto data){
		log.info(data.toString());
		List<String> result=mSer.getData(data.getChamptionName(),data.getTeamPosition(),data.getMyteamPosition());
		return result;
	}
}
