package com.gg.gop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.ChampionDao;
import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.RecordDto;

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

	public ChampionDto findChampion(String championName) {
		ChampionDto champion = cDao.findChampion(championName);
		return champion;
	}

	public List<ChampionDto> searchcList(String search) {
		List<ChampionDto> cList = cDao.searchList(search);
		return cList;
	}

	public List<RecordDto> lineChampionList(String line) {
		List<RecordDto> lineList = cDao.lineChampionList(line);
		return lineList;
	}

	public List<RecordDto> lineRecord() {
		List<RecordDto> lineRList = cDao.lineRecode();
		log.info("test : {}", lineRList);
		return lineRList;
	}

	public List<RecordDto> lineInfo(String line) {
		List<RecordDto> lineList = cDao.lineInfo(line);
		log.info("line : {}", line);
		return lineList;
	}

}
