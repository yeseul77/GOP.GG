package com.gg.gop.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.gop.dao.MatchDao;
import com.gg.gop.dto.MatchDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MatchService {
	@Autowired
	MatchDao mDao;
	
	public List<MatchDto> getData(String championName, String champPosition, String myPosition){
	    if(!(Pattern.matches("^[a-zA-Z]*$", championName))) {
	    	String cheng=mDao.getengName(championName);
	    	log.info(cheng);
			List<MatchDto> result=mDao.getMatchData(cheng, champPosition, myPosition);
			return result;
		}
	    else {
	    	List<MatchDto> result=mDao.getMatchData(championName, champPosition, myPosition);	
	    	return result;
	    }
	}
}
