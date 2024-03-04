package com.gg.gop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gg.gop.dto.RankDto;
import com.gg.gop.service.RankService;

@RestController
public class RankRestController {

	@Autowired
	RankService rSer;
	
	@GetMapping("/rank/winlist")
	public List<RankDto> winlist(){
		List<RankDto> winRank= rSer.getWinList();
		return winRank;
	}
	@GetMapping("/rank/gamelist")
	public List<RankDto> gamelist(){
		List<RankDto> gameRank=rSer.getGameList();
		return gameRank;
	}
	@GetMapping("/rank/kdalist")
	public List<RankDto> kdalist(){
		List<RankDto> kdaRank=rSer.getKdaList();
		return kdaRank;
	}
}