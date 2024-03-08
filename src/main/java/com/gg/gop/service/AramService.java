package com.gg.gop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.AramDao;
import com.gg.gop.dao.ChampionDao;
import com.gg.gop.dto.AramDto;
import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.RecordDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AramService {

	@Autowired
	private AramDao aDao;

	public List<ChampionDto> allChampion() {
		List<ChampionDto> championList = aDao.allChampion();
		return championList;
	}

	public ChampionDto findChampion(String championName) {
		ChampionDto champion = aDao.findChampion(championName);
		return champion;
	}

	public List<ChampionDto> searchcList(String search) {
		List<ChampionDto> cList = aDao.searchList(search);
		return cList;
	}

	public List<RecordDto> lineChampionList(String line) {
		List<RecordDto> lineList = aDao.lineChampionList(line);
		return lineList;
	}

	public List<AramDto> lineRecord() {
		List<AramDto> lineRList = aDao.lineRecode();
		log.info("test : {}", lineRList);
		return lineRList;
	}

	public List<RecordDto> lineInfo(String line) {
		List<RecordDto> lineList = aDao.lineInfo(line);
		log.info("line : {}", line);
		return lineList;
	}
}
