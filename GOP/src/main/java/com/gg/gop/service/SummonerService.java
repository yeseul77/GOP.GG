package com.gg.gop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gg.gop.dao.SummonerDao;

@Service
public class SummonerService {

	@Autowired
	SummonerDao sDao;

	@Autowired
	SummonerWebClientService webClient;

	public String puuid(String gameName, String tagLine) { // puuid 가져오기
		String puuid = webClient.getPuuidInfo(gameName, tagLine);
		return puuid;
	}

	public List<String> matchIdList(String puuid) { // matchId 가져오기
		List<String> matchIdList = webClient.getMatchIdInfo(puuid);
		return matchIdList;
	}

	public List<Map> gameInfoList(List<String> matchId) { // 최근전적 정보 가져오기
		List<Map> gameInfoList = new ArrayList<>();
		for (int i = 0; i < matchId.size(); i++) {
			Map gameInfo = webClient.getGameInfo(matchId.get(i));
			gameInfoList.add(gameInfo);
		}
		return gameInfoList;
	}

	public List<Map> saveAndRetrieveGamedata(List<Map> mapList) throws JsonProcessingException {
		List<Map> savedDataList = new ArrayList<>();
		boolean hasDuplicateKey = false;

		for (Map gamedata : mapList) {
			try {
				if (!hasDuplicateKey(gamedata)) {
					// 중복된 키가 없으면 데이터를 저장
					sDao.saveinfodata(gamedata);

					// 저장된 데이터를 가져와서 리스트에 추가
					Map savedData = sDao.getGameDataFromDB(gamedata);
					if (savedData != null) {
						savedDataList.add(savedData);
					}
				} else {
					hasDuplicateKey = true;
				}
			} catch (DuplicateKeyException e) {
				System.out.println(e.getMessage());
				// 중복된 키 예외에 대한 추가적인 처리를 수행할 수 있습니다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 중복된 키가 없으면서 데이터가 저장된 경우에만 웹 클라이언트 호출
		if (!hasDuplicateKey && !savedDataList.isEmpty()) {
			// 웹 클라이언트 호출 코드 추가
			System.out.println("Calling web client...");

			// 서버로부터 저장된 데이터를 다시 불러옴
			savedDataList = retrieveAllDataFromDB();
		}

		return savedDataList;
	}

	public List<Map> getGameInfoFromDB(String riotIdGameName, String riotIdTagline)throws JsonProcessingException {
		try {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("riotIdGameName", riotIdGameName);
			paramMap.put("riotIdTagline", riotIdTagline);
			return sDao.getGameInfoFromDB(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	private boolean hasDuplicateKey(Map<String, Object> gamedata) throws DuplicateKeyException {
		String matchId = (String) gamedata.get("matchId");
		String riotIdGameName = (String) gamedata.get("riotIdGameName");
		int count = sDao.checkDuplicateKey(matchId, riotIdGameName);
		if (count > 0) {
			throw new DuplicateKeyException(
					"Duplicate key found for matchId: " + matchId + " and riotIdGameName: " + riotIdGameName);
		}
		return count > 0; // 중복 키가 발견되었는지 여부를 반환
	}

	private List<Map> retrieveAllDataFromDB()throws JsonProcessingException {
		// 모든 데이터를 DB에서 가져와서 반환하는 메소드
		try {
			return sDao.retrieveAllDataFromDB();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // 빈 리스트 반환 또는 적절한 방식으로 처리
		}
	}

	public int saveinfodata(Map<String, Object> info) {
		int data = 0;
	    String matchId = (String) info.get("matchId");
	    if (matchId != null) {
	    	 data = sDao.saveinfodata(info);
	    } else {
	        System.out.println("matchId is null. Cannot insert data.");
	    }
	    return data;
	}

	public int saveteamsdata(Map<String, Object> teams) {
	    int data = 0;
	    String matchId = (String) teams.get("matchId");
	    if (matchId != null) {
	        data = sDao.saveteamsdata(teams); // DAO 메소드 호출 결과를 저장
	    } else {
	        System.out.println("matchId is null. Cannot insert data.");
	    }
	    return data; // DAO 메소드 호출 결과 반환
	}
}
