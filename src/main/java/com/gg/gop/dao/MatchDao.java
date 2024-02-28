package com.gg.gop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MatchDao {

	List<String> getMatchData(String championName, String champteamPosition, String myteamPosition);

}
