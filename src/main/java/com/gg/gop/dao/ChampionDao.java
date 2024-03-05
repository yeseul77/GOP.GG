package com.gg.gop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.RecordDto;
import com.gg.gop.dto.RuneDto;

@Mapper
public interface ChampionDao {

	public List<ChampionDto> allChampion();

	public ChampionDto findChampion(String championName);

	public List<ChampionDto> searchList(String search);

	public List<RecordDto> lineChampionList(String line);

	public List<RecordDto> lineRecode();

	public List<RecordDto> lineInfo(String line);

	public List<RuneDto> getChampionRune(String championName, String position);

	public List<String> getChampionPosition(String championName);

	public RecordDto getRates(String championName, String firstLine);

	public List<String> getSpells(String championName, String position);
	
}