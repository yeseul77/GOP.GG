package com.gg.gop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.ChampionDao;
import com.gg.gop.dto.ChampionDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChampionService {

	@Autowired
	private ChampionDao cDao;

	public List<ChampionDto> allChampion() {
		List<ChampionDto> championList = cDao.allChampion();
		return championList;
	}

}
