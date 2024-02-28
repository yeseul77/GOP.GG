package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gg.gop.dto.MatchDto;
@Mapper
public interface MatchDao {

	List<MatchDto> getMatchData(String championName, String champteamPosition, String myteamPosition);

}
