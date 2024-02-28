package com.gg.gop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.MatchDao;
import com.gg.gop.dto.MatchDto;

@Service
public class MatchService {
	@Autowired
	MatchDao mDao;
	
	public List<MatchDto> getData(String championName, String champPosition, String myPosition){
		List<MatchDto> result=mDao.getMatchData(championName, champPosition, myPosition);
		return result;
	}
}
