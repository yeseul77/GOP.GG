package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.RankDto;

@Mapper
public interface RankDao {

	List<RankDto> getWinRate();

	List<RankDto> getGameRate();

	List<RankDto> getKdaRate();

}
