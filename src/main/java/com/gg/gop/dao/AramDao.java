package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.AramDto;
import com.gg.gop.dto.ChampionDto;
import com.gg.gop.dto.RecordDto;
@Mapper
public interface AramDao {

	public List<ChampionDto> allChampion();

	public ChampionDto findChampion(String championName);

	public List<ChampionDto> searchList(String search);

	public List<RecordDto> lineChampionList(String line);

	public List<AramDto> lineRecode();

	public List<RecordDto> lineInfo(String line);
}
