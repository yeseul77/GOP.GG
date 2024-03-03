package com.gg.gop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.RankDao;
import com.gg.gop.dto.RankDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RankService {

	@Autowired
	RankDao rDao;
	
	public List<RankDto> getWinList() {
		List<RankDto> winList=rDao.getWinRate();
		log.info("winList{}",winList);
		return winList;
	}

	public List<RankDto> getGameList() {
		List<RankDto> gameList=rDao.getGameRate();
		log.info("gameList{}",gameList);
		return gameList;
	}

	public List<RankDto> getKdaList() {
		List<RankDto> kdaList=rDao.getKdaRate();
		log.info("kdaList{}",kdaList);
		return kdaList;
	}

}
