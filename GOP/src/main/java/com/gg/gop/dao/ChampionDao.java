package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.ChampionDto;

@Mapper
public interface ChampionDao {

	public List<ChampionDto> allChampion();

}
