package com.gg.gop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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

	public int saveGamedata(List<Map> map1) { // 최근전적 정보 db에 저장하기
		int data = 0;
		for (Map gamedata : map1) {
			try {
				if (!isDuplicateKey(gamedata)) {
					data += sDao.saveGamedata(gamedata);
				}
			} catch (DuplicateKeyException e) {
				System.out.println(e.getMessage());
				// 중복된 키 예외에 대한 추가적인 처리를 수행할 수 있습니다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public List<Map> saveAndRetrieveGamedata(List<Map> mapList) {
	    List<Map> savedDataList = new ArrayList<>();
	    boolean hasDuplicateKey = false;

	    for (Map gamedata : mapList) {
	        try {
	            if (!isDuplicateKey(gamedata)) {
	                // 중복된 키가 없으면 데이터를 저장
	                int savedCount = sDao.saveGamedata(gamedata);
	                System.out.println("Saved count: " + savedCount);

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

	public List<Map> getGameInfoFromDB(String gameName, String tagLine) {
		try {
			// MyBatis를 이용하여 SQL 쿼리 실행
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("riotIdGameName", gameName);
			paramMap.put("riotIdTagline", tagLine);
			return sDao.getGameInfoFromDB(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // 빈 리스트 반환 또는 적절한 방식으로 처리
		}
	}

	private boolean isDuplicateKey(Map gamedata) throws DuplicateKeyException {
		String matchId = (String) gamedata.get("matchId");
		String riotIdGameName = (String) gamedata.get("riotIdGameName");
		int count = sDao.checkDuplicateKey(matchId, riotIdGameName);
		if (count > 0) {
			throw new DuplicateKeyException(
					"Duplicate key found for matchId: " + matchId + " and riotIdGameName: " + riotIdGameName);
		}
		return false;
	}

	private List<Map> retrieveAllDataFromDB() {
		// 모든 데이터를 DB에서 가져와서 반환하는 메소드
		try {
			return sDao.retrieveAllDataFromDB();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // 빈 리스트 반환 또는 적절한 방식으로 처리
		}
	}
}
