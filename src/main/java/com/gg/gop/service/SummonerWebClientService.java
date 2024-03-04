package com.gg.gop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gg.gop.dto.SummonerDto;

@Service
public class SummonerWebClientService {
	private static final String RIOT_API_KEY = "RGAPI-953215fc-9845-49e6-988f-07a6f4e122cb";
	private static final String RIOT_API_URL = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
	private static final String RIOT_API_URL2 = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/";
	private Map<String, Long> lastRequestTimes = new HashMap<>();
	@Value("${riot.api.requestLimitPerSecond}")
    private int requestLimitPerSecond;

    // 다른 메서드들...

    private void checkRequestLimit() {
        String key = "default";
        long currentTime = System.currentTimeMillis();
        long lastRequestTime = lastRequestTimes.getOrDefault(key, 0L);
        long timeElapsedSinceLastRequest = currentTime - lastRequestTime;

        if (timeElapsedSinceLastRequest < 1000L / requestLimitPerSecond) {
            long waitTime = 1000L / requestLimitPerSecond - timeElapsedSinceLastRequest;
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while waiting to respect rate limit", e);
            }
        }
        lastRequestTimes.put(key, System.currentTimeMillis());
    }

	public String getPuuidInfo(String gameName, String tagLine) {
		checkRequestLimit();
		String url = RIOT_API_URL + gameName + "/" + tagLine + "?api_key=" + RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		SummonerDto response = webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
				.bodyToMono(SummonerDto.class).block();
		return response.getPuuid();
	}

	public List<String> getMatchIdInfo(String puuid) {
		checkRequestLimit();
		String url = RIOT_API_URL2 + puuid + "/ids?start=0&count=10&api_key=" + RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		List<String> matchIdList = webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<String>>() {
				}).block();
		return matchIdList;

	}

	public Map getGameInfo(String matchId) { // 매치id를 통해서 데이터를 받음
		checkRequestLimit();
		String url = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		Map response = webClient.get() // 맵으로 받으면 됨
				.uri(uriBuilder -> uriBuilder.build()).retrieve().bodyToMono(Map.class).block();
		return response;
	}

	public String getSummonerId(String puuid) {
		checkRequestLimit();
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key="
				+ RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		SummonerDto response = webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
				.bodyToMono(SummonerDto.class).block();
		return response.getId();
	}

	public List<Map<String, Object>> getSummonerLeagueInfo(String summonerId) {
		checkRequestLimit();
	    String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId + "?api_key=" + RIOT_API_KEY;
	    WebClient webClient = WebClient.builder().baseUrl(url).build();
	    return webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
	            }).block();
	}
}