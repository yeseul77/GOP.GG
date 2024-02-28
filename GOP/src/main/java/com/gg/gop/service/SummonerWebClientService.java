package com.gg.gop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gg.gop.dto.SummonerDto;
import com.gg.gop.dto.SummonerLeagueDto;

@Service
public class SummonerWebClientService {
	private static final String RIOT_API_KEY = "RGAPI-839c783f-7df0-462c-8b80-1c7ea25cf990";
	private static final String RIOT_API_URL = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
	private static final String RIOT_API_URL2 = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/";

	public String getPuuidInfo(String gameName, String tagLine) {
		String url = RIOT_API_URL + gameName + "/" + tagLine + "?api_key=" + RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		SummonerDto response = webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
				.bodyToMono(SummonerDto.class).block();
		return response.getPuuid();
	}

	public List<String> getMatchIdInfo(String puuid) {
		String url = RIOT_API_URL2 + puuid + "/ids?start=0&count=10&api_key=" + RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		List<String> matchIdList = webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<String>>() {
				}).block();
		return matchIdList;

	}

	public Map getGameInfo(String matchId) { // 매치id를 통해서 데이터를 받음
		String url = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		Map response = webClient.get() // 맵으로 받으면 됨
				.uri(uriBuilder -> uriBuilder.build()).retrieve().bodyToMono(Map.class).block();
		return response;
	}

	public String getSummonerId(String puuid) {
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + puuid + "?api_key="
				+ RIOT_API_KEY;
		WebClient webClient = WebClient.builder().baseUrl(url).build();
		SummonerDto response = webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
				.bodyToMono(SummonerDto.class).block();
		return response.getId();
	}

	public List<Map<String, Object>> getSummonerLeagueInfo(String summonerId) {
	    String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId + "?api_key=" + RIOT_API_KEY;
	    WebClient webClient = WebClient.builder().baseUrl(url).build();
	    return webClient.get().uri(uriBuilder -> uriBuilder.build()).retrieve()
	            .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
	            }).block();
	}
}