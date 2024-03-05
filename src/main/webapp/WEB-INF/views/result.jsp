<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/tiles/header.jsp" %>
<meta charset="UTF-8">
<title>${param.gameName} - 전적 검색</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>
$(document).ready(function () {
	var gameName = "${param.gameName}";
    var tagLine = "${param.tagLine}";

    <%
        List<Map<String, Object>> gameInfoList = (List<Map<String, Object>>) request.getAttribute("gameInfoList");

        // Java 객체를 JSON으로 직렬화
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> gameInfo : gameInfoList) {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> entry : gameInfo.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }
        String gameInfoListJson = jsonArray.toJSONString();
    %>
	
    var gameInfoList2 = <%= gameInfoListJson %>;
    console.log("gameInfoList2:", gameInfoList2);
    if (gameInfoList2.length > 0) {
    	try{
            console.log("Received gameData in result page:", gameInfoList2);
            displayGameInfo(gameInfoList2);
        	}catch{
        		updateGameData();
        	}
    } else {
        console.log("No gameInfoList parameter found.");
    }
   	function displayGameInfo(gameInfoList) {
   		$("#gameInfoTable tbody").empty();
   		$("#additionalTable").empty();
   	   //최근 플레이챔피언 이미지 배열 초기화
   		var pictures = [];
   		var gamesDisplayed = 0;
   		var championStats = {};
   		if (gameInfoList.length > 0) {
   		 			  			
   		 	for (var index = 0; index < gameInfoList.length && gamesDisplayed < 10; index++) {
   	        	var gameInfo = gameInfoList[index];
   	        	
   	        // gameInfo.info 배열에서 해당 플레이어의 정보를 찾음
   	        	for (var j = 0; j < gameInfo.info.length; j++) {
   	            	var playerInfo = gameInfo.info[j];
   	            	var matchingTeam = gameInfo.teams.find(function(team) {
   	            	    return team.teamwin === playerInfo.win;
   	            	});
   	            	
   	            // 플레이어의 게임 이름과 태그 라인이 일치할 경우에만 표시
   	            	if (playerInfo.riotIdGameName === gameName && playerInfo.riotIdTagline === tagLine) {
   	            		var leagueInfoForSummoner = gameInfo.leagueInfo.find(function(info) {
   	   	                    return info.summonerId === playerInfo.summonerId;
   	   	                });
   	            		
   	   	                // 동일한 summonerId를 가진 정보가 있는 경우에만 표시
   	   	                if (leagueInfoForSummoner) {
   	   	              var tierColor = '';
	   	                	switch(leagueInfoForSummoner.tier){
	   	                	case 'CHALLENGER' : 
	   	                		tierColor = '#00b5e3';
	   	                		break;
	   	                    case 'GRANDMASTER' : 
 	                		tierColor = '#dd534a';
 	                		break;
	   	                    case 'MASTER' : 
 	                		tierColor = '#9400d3';
 	                		break;
	   	                    case 'DIAMOND' : 
 	                		tierColor = '#5c6eb4';
 	                		break;
	   	                    case 'EMERALD' : 
 	                		tierColor = '#008000';
 	                		break;
	   	                    case 'PLATINUM' : 
 	                		tierColor = '#008b8b';
 	                		break;
	   	                    case 'GOLD' : 
 	                		tierColor = '#b8860b';
 	                		break;
	   	                    case 'SILVER' : 
 	                		tierColor = '#808080';
 	                		break;
	   	                    case 'BRONZE' : 
 	                		tierColor = '#935b55';
 	                		break;
	   	                    case 'IRON' : 
 	                		tierColor = '#544546';
 	                		break;	   	                		
	   	                	default :
	   	                	    tierColor = '#333333';
	   	                	}
	   	                	if(!(leagueInfoForSummoner.tier==='UNRANK')){
	   	                		console.log("rank")
		   	                	var winLose = ((leagueInfoForSummoner.wins / (leagueInfoForSummoner.wins + leagueInfoForSummoner.losses)) * 100).toFixed(1); 
	   	   	                    var summonerLeagueInfo = "<div class='rank'>" +
	   	   	                        "<div class='rankImage'><img src='/images/" + leagueInfoForSummoner.tier + ".webp' alt='tier'></div>" +
	   	   	              			"<div class='rankTier'><div class='tier' style='color:"+ tierColor +";'>" + leagueInfoForSummoner.tier + "&nbsp;&nbsp;"+ leagueInfoForSummoner.ranked + "</div><div class='lp'>" + leagueInfoForSummoner.leaguePoints + "&nbsp;LP</div></div></div>" +
	   	   	              			"</div>" +
	   	   	              			"<div class='chart-box'>"+
	   	   	              			"<div class='chart'><div class='chart-mo'>" + leagueInfoForSummoner.losses + "&nbsp;&nbsp;<div class='chart-child' style=width:"+ winLose +"%>&nbsp;&nbsp;" + leagueInfoForSummoner.wins + "</div></div></div>" +
	   	   	              			"<div class='winRate'><div class='winlose'>" + leagueInfoForSummoner.wins + "승&nbsp;" + leagueInfoForSummoner.losses + "패</div><div class='rate'>승률&nbsp;" + (leagueInfoForSummoner.wins/(leagueInfoForSummoner.wins+leagueInfoForSummoner.losses)*100).toFixed(0) + "%</div></div>" +
	   	   	              			"</div>";
   	   	                	}else{
   	   	                		console.log("unrank");
   	   	                		var summonerLeagueInfo = "<div class='rank'>" +
   	   	                        						 "<div class='rankImage'><img src='/images/" + leagueInfoForSummoner.tier + ".webp' alt='tier'></div>" +
						   	   	              		 	 "<div class='rankTier'><div class='tier' style='color:"+ tierColor +";'>" + leagueInfoForSummoner.tier + "&nbsp;&nbsp;"+ leagueInfoForSummoner.ranked + "</div><div class='lp'>" + leagueInfoForSummoner.leaguePoints + "&nbsp;LP</div></div></div>" +
						   	   	              			 "</div>";
   	   	              		}
   	   	                }
   	   	                
            
   	   	                
   	            		var kdaDisplay;
   	            		if (playerInfo.kda === 0) {
   	            		    kdaDisplay = "perfect";
   	            		} else {
   	            		    kdaDisplay = "KDA&nbsp;" +playerInfo.kda + "&nbsp;평점";
   	            		}
   	            		var gameDisplay;
   	            		if (playerInfo.gameDuration < 240){
   	            			gameDisplay = "다시하기";
   	            		} else{
   	            			gameDisplay = playerInfo.gameMode;
   	            		}
   	            		
   	            		switch(gameDisplay) {
  	            		 case 'ARAM':
  	            		   gameMode = '무작위 총력전';
  	            		   break;
  	            		 case 'CLASSIC':
    	            	   gameMode = '솔로랭크';
    	            	   break; 
  	            		 case 'URF':
    	            	   gameMode = 'U.R.F';
    	            	   break;
    	            	 default :
    	            	   gameMode = 'None'; 
  	            		}
   	            		playerInfo.championName === 'FiddleSticks' ? 'Fiddlesticks' : playerInfo.championName;
   	            		
   	            		var newIndex = index+3;
   	            		
   	            		
   	            		var backColor = (playerInfo.win ? "승리" : "패배") === "승리" ? "#e8f0fd" : "#fbece9";
   	            		var fontColor = (playerInfo.win ? "승리" : "패배") === "승리" ? "#5d9ceb" : "#e57474";
   	                	var gameRow = "<div class='summary' style='background-color:"+backColor+"; border-left: 8px solid "+fontColor+";'>" +
   	                	    "<div class='gameInfomation'>" +
   	                	    "<div class='gameMode' style='color:"+fontColor+"'>" + gameMode + "</div>" +
   	                	    "<div class='summonerName'>" + playerInfo.championName + "</div>" +
   	                	    "<div class='victoryLose' style='color:"+fontColor+"'>" + (playerInfo.win ? "승리" : "패배") + "</div>" +
   	                	    "<div class='time'>" + Math.floor(playerInfo.gameDuration / 60) + "분" + ((Math.floor(playerInfo.gameDuration % 60)) < 10 ? "0" : "") + Math.floor(playerInfo.gameDuration % 60) + "초 </div>" +
	                    	"</div>" +
	                    	"<div class='summonerBox'>" +
   	                    	"<div class='summonerPhoto'><a href=''><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/" + playerInfo.championName + ".png' alt='" + playerInfo.championName + "'></a></div>" +
   	                    	"<div class='summonerTier'>" + leagueInfoForSummoner.tier + "&nbsp;" + leagueInfoForSummoner.ranked + "</div>" +
   	                    	"</div>" +
   	                    	"<div class='kdaRecode'>" +
   	                    	"<div class='kda'>" + playerInfo.kills + " / " + playerInfo.deaths + " / " + playerInfo.assists + "</div>" +
   	                    	"<div class='kdaRate'>" + kdaDisplay + "</div>" +
   	                    	"</div>" +
   	                    	"<div class='killChat'><div id='g"+newIndex+"' class='gauge'></div></div>" +
   	                    	"<div class='killData'>" +
   	                    	"<span>킬&nbsp;관여</span>" +
   	                    	"<div class='data'>" + parseFloat(((playerInfo.kills + playerInfo.assists) / matchingTeam.teamchampionkills)* 100).toFixed(0) +"%</div>" +
   	                    	"</div>" +
   	                    	"<div class='showMore'><button class='btn_toggle' data-index='" + index + "'><span class='material-symbols-outlined' style='color:"+fontColor+"'>expand_more</span></button></div>" +
   	                    	"</div>";
   	                    
                 	  		   	                    	 	                    	
   	                	$("#gameInfoTable").append(gameRow);
   	                	
   	                	var gagchart = new JustGage({
      	                     id: "g" + newIndex + "",
      	                     value: parseFloat(((playerInfo.kills + playerInfo.assists) / matchingTeam.teamchampionkills)* 100).toFixed(0),
      	                     min: 0,
      	                     max: 100,
      	                     width: 180,
      	                     height: 135,            
      	                     symbol: '%',
      	                     pointer: true,
      	                     pointerOptions: {
      	                         toplength: -15,
      	                         bottomlength: 13,
      	                         bottomwidth: 9,
      	                         color: '#8e8e93',
      	                         stroke: '#ffffff',
      	                         stroke_width: 3,
      	                         stroke_linecap: 'round'
      	                     },
      	                     gaugeWidthScale: 0.6,
      	                     counter: true
      	                 });
   	                	 	  	               	  	                	
   	                    //플레이챔피언 이미지 담기
                        const championName = playerInfo.championName === 'FiddleSticks' ? 'Fiddlesticks' : playerInfo.championName;
   	                	pictures.push(championName);
   	                    
   	                  	// 챔피언이 이미 등록되어 있는지 확인하고, 등록되어 있지 않으면 초기값으로 설정
   		        	}
   		        }
               	
   		        var showMore = "<table class='Toggle" + index + "' style='display:none'>" +
   		        	"<colgroup>" +
   		        	"<col width='23%'>" +
		        	"<col width='15%'>" +
		        	"<col width='25%'>" +
		        	"<col width='25%'>" +
		        	"<col width='12%'>" +
   		        	"</colgroup>" +
   		        	"<thead>" +
   		        	"<tr class='tableRow'>" +
   		            "<th class='tableHead-first'><span class='winSpan'>승리</span></th>" +
   		            "<th class='tableHead'>KDA</th>" +
   		            "<th class='tableHead'>입힌 피해량</th>" +
   		            "<th class='tableHead'>받은 피해량</th>" +
   		            "<th class='tableHead'>CS</th>" +
   		            "</tr>" +
   		            "</thead>"+
   		            "<tbody class='winTeam' style='background-color: #e8f0fd'>";
   		            
   		        for (var k = 0; k < gameInfo.info.length; k++) {
   		       	    var winTeamPlayerInfo = gameInfo.info[k];
   		       		var kdaDisplay;
           			if (winTeamPlayerInfo.kda === 0) {
           		    	kdaDisplay = "perfect";
           			} else {
           		    	kdaDisplay = winTeamPlayerInfo.kda.toFixed(2) + "&nbsp;평점";
           			}
   		       	    if(winTeamPlayerInfo.win === true){
   		       	    	
   		       	    winTeamPlayerInfo.championName === 'FiddleSticks' ? 'Fiddlesticks' : winTeamPlayerInfo.championName;	
   		       	    var takeDamage = winTeamPlayerInfo.totalDamageTaken.toLocaleString();
   		       	    var giveDamage = winTeamPlayerInfo.totalDamageDealtToChampions.toLocaleString();
   		       	    var giveDamageChart = ((winTeamPlayerInfo.totalDamageDealtToChampions / 30000) * 100).toFixed(1);
   		       	    var takeDamageChart = ((winTeamPlayerInfo.totalDamageTaken / 30000) * 100).toFixed(1);
   		            showMore += 
   		                "<tr class='win'>" +
   		                "<td class='win-body summoner'>" +
       		            "<div class='win-image'><a href=''><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/" + winTeamPlayerInfo.championName + ".png' alt='" + winTeamPlayerInfo.championName + "'href='/champion/detail?championName="+winTeamPlayerInfo.championName+"'></a></div>" +
       		            "<div class='win-user'><div class='riotIdGameName'><a href='/summonerSearch?gameName="+winTeamPlayerInfo.riotIdGameName+"&tagLine="+winTeamPlayerInfo.riotIdTagline+"'>" + winTeamPlayerInfo.riotIdGameName + "</a></div><div class='championName'>" + winTeamPlayerInfo.championName + "</div></div>" +
       		            		
   		                "</td>" +
       		            "<td class='win-body'>" + kdaDisplay + "</td>" +
   		                "<td class='win-bodyChart1'><div class='chartMain'><span>" + giveDamage + "</span><div class='chart-mo'><div class='chart-ch' style='width:" + giveDamageChart + "%'></div></div></div></td>" +
   		                "<td class='win-bodyChart2'><div class='chartMain'><span>" + takeDamage + "</span><div class='chart-mo'><div class='chart-ch' style='width:" + takeDamageChart + "%'></div></div></div></td>" +
   		                "<td class='win-body'>" + winTeamPlayerInfo.totalMinionsKilled + "&nbsp;개</td>" +
   		                "</tr>";
   		                
   		                   		                
   		       	    }
   		        }
   		     	showMore += "<table class='Toggle" + index + "' style='display:none'>" +
   		            "<colgroup>" +
   		            "<col width='23%'>" +
		        	"<col width='15%'>" +
		        	"<col width='25%'>" +
		        	"<col width='25%'>" +
		        	"<col width='12%'>" +
	        	    "</colgroup>" +
	        	    "<thead>" +
	        	    "<tr class='tableRow'>" +
   		            "<th class='tableHead-first'><span class='loseSpan'>패배</span></th>" +
   		            "<th class='tableHead'>KDA</th>" +
   		            "<th class='tableHead'>입힌 피해량</th>" +
   		            "<th class='tableHead'>받은 피해량</th>" +
   		            "<th class='tableHead'>CS</th>" +
   		            "</tr>" +
   		            "</thead>"+
   		            "<tbody class='loseTeam' style='background-color: #fbece9'>";
   		            
	            for (var k = 0; k < gameInfo.info.length; k++) {
       		       	var loseTeamPlayerInfo = gameInfo.info[k];
       		       	if(loseTeamPlayerInfo.win === false){
       		     		var kdaDisplay;
        				if (loseTeamPlayerInfo.kda === 0) {
        		    		kdaDisplay = "perfect";
        				} else {
	        		    	kdaDisplay = loseTeamPlayerInfo.kda.toFixed(2) + "&nbsp;평점";
        				}
        				winTeamPlayerInfo.championName === 'FiddleSticks' ? 'Fiddlesticks' : winTeamPlayerInfo.championName;	
       		       	    var takeDamage = loseTeamPlayerInfo.totalDamageTaken.toLocaleString();
       		       	    var giveDamage = loseTeamPlayerInfo.totalDamageDealtToChampions.toLocaleString();
       		       	    var giveDamageChart = ((loseTeamPlayerInfo.totalDamageDealtToChampions / 30000) * 100).toFixed(1);
   		       	        var takeDamageChart = ((loseTeamPlayerInfo.totalDamageTaken / 30000) * 100).toFixed(1);
        				
       		        	showMore += 
	       		             "<tr class='lose'>" +
	 		                "<td class='lose-body summoner'>" +
	     		            "<div class='lose-image'><a href=''><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/" + loseTeamPlayerInfo.championName + ".png' alt='" + loseTeamPlayerInfo.championName + "'></a></div>" +
	     		            "<div class='lose-user'><div class='riotIdGameName'><a href='/summonerSearch?gameName="+loseTeamPlayerInfo.riotIdGameName+"&tagLine="+loseTeamPlayerInfo.riotIdTagline+"'> " + loseTeamPlayerInfo.riotIdGameName + "</div><div class='championName'>" + loseTeamPlayerInfo.championName + "</div></div>" +
	     		            "</td>" +
	     		            "<td class='lose-body'>" + kdaDisplay + "</td>" +
	 		                "<td class='lose-bodyChart1'><div class='chartMain'><span>" + giveDamage + "</span><div class='chart-mo'><div class='chart-ch' style='width:" + giveDamageChart + "%'></div></div></div></td>" +
	 		                "<td class='lose-bodyChart2'><div class='chartMain'><span>" + takeDamage + "</span><div class='chart-mo'><div class='chart-ch' style='width:" + takeDamageChart + "%'></div></div></div></td>" +
	 		                "<td class='lose-body'>" + loseTeamPlayerInfo.totalMinionsKilled + "&nbsp;개</td>" +
	 		                "</tr>";      		                      
        		                      		            	
       		       	}
       		    }
   		        $("#gameInfoTable").append(showMore);
   		     	gamesDisplayed++;
   		    }
   		 var championPlayCounts = {};
         for (var index = 0; index < gameInfoList.length; index++) {
             var gameInfo = gameInfoList[index];
             for (var j = 0; j < gameInfo.info.length; j++) {
                 var playerInfo = gameInfo.info[j];
                 var champion = playerInfo.championName;
                 // 플레이어의 게임 이름과 태그 라인이 일치할 경우에만 플레이 횟수를 누적
                 if (playerInfo.riotIdGameName === gameName && playerInfo.riotIdTagline === tagLine) {
                	 if (!championStats[champion]) {
                         championStats[champion] = {
                             gamesPlayed: 0,
                             totalKills: 0,
                             totalDeaths: 0,
                             totalAssists: 0,
                             totalWins: 0
                         };
                     }
                     championStats[champion].gamesPlayed++;
                     championStats[champion].totalKills += playerInfo.kills;
                     championStats[champion].totalDeaths += playerInfo.deaths;
                     championStats[champion].totalAssists += playerInfo.assists;
                     if (playerInfo.win) {
                         championStats[champion].totalWins++;
                     }
                     if (!championPlayCounts[champion]) {
                         championPlayCounts[champion] = 1;
                     } else {
                         championPlayCounts[champion]++;
                     }
                 }
             }
         }
      // 챔피언별 플레이 횟수를 테이블에 추가
         var sortedChampionPlayCounts = Object.entries(championPlayCounts).sort((a, b) => b[1] - a[1]);
         var playerRow = "";
         for (var i = 0; i < sortedChampionPlayCounts.length; i++) {
        	    var champion = sortedChampionPlayCounts[i][0];
        	    var playCount = sortedChampionPlayCounts[i][1];
        	    const championName = champion === 'FiddleSticks' ? 'Fiddlesticks' : champion;
               	
        	    playerRow += "<div class='champion-box'>" +
        	        "<div class='face'><a href=''><img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/" + championName + ".png' alt='" + championName + "'></a></div>" +
        	        "<div class='info'>" + championName + "</div>";

        	    // 챔피언별 평균 KDA와 승률 추가
        	    if (championStats[champion]) {
        	    	var avgKills = parseFloat((championStats[champion].totalKills / championStats[champion].gamesPlayed).toFixed(1));
        	    	var avgDeaths = parseFloat((championStats[champion].totalDeaths / championStats[champion].gamesPlayed).toFixed(1));
        	    	var avgAssists = parseFloat((championStats[champion].totalAssists / championStats[champion].gamesPlayed).toFixed(1));
        	        var avgKDA = ((avgKills + avgAssists) / avgDeaths).toFixed(2);
        	        var avgWinRate = ((championStats[champion].totalWins / championStats[champion].gamesPlayed) * 100).toFixed(0) + "&nbsp;%";
        	        playerRow += "<div class='kda'>" +
        	        	"<div class='kdaRate'>" + avgKills.toFixed(1) + "&nbsp;/&nbsp;" + avgDeaths.toFixed(1) + "&nbsp;/&nbsp;" + avgAssists.toFixed(1) + "</div>" +
        	            "<div class='kdaPoint'>" + avgKDA + "&nbsp;&nbsp;평점</div>" +
        	            "</div>" +
        	            "<div class='game'>" +
        	            "<div class='winRate'>" + avgWinRate + "</div>";
        	    } else {
        	        // 챔피언별 평균 값이 없는 경우 처리
        	        playerRow += "<td colspan='3'>평균 값 없음</td>";
        	    }
        	    playerRow += "<div class='count'>" + playCount + "게임</div></div>" +
        	            "</div>";
         }
         //승률 차트 추가
         $("#summonerLeagueInfo").append(summonerLeagueInfo);
         
         $("#additionalTable").append(playerRow);
       		
  		} else {
   			$("#gameInfoTable").append("<tr><td colspan='3'>게임 정보가 없습니다.</td></tr>");
   		}
   	    //최근 플레이챔피언 이미지 프로필로 가져오기 
   		var picture = "<img src='https://ddragon.leagueoflegends.com/cdn/14.3.1/img/champion/" + pictures[0] + ".png' alt='" + pictures[0] + "' class='pictureImage'>";
   		$("#summonerPicture").empty().append(picture);
   		
   		
	};
	

    $("#searchForm2").submit(function (event) {
        event.preventDefault();
        const pattern = /^(.+?)\s*(K?R?\d*)$/;
        var result = pattern.exec($("#fullgameName").val().trim());
        var gameName = result[1];
        var tagLine = result[2];
        updateURL(gameName, tagLine);
        location.reload(true);
        
        if (gameName.trim() === "") {
            alert("gameName cannot be empty.");
            return;
        }
        function updateURL(gameName, tagLine) {
            var newURL = window.location.pathname + "?gameName=" + encodeURIComponent(gameName) + "&tagLine=" + encodeURIComponent(tagLine);
            window.history.pushState({ path: newURL }, '', newURL);
        }
        $("#gameNameDisplay").text(gameName);
        $("#tagLineDisplay").text(tagLine);

        $.ajax({
            type: "POST",
            url: "summonerSearch2",
            data: { gameName: gameName, tagLine: tagLine },
            success: function (data) {
                console.log("Received data:", data);
                displayGameInfo(data);
            },
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown);
            }
        });
    });
    
    $("#updateButton").click(function () {
        updateGameData();
    });
 
    function saveGameDataToServer(data) {
    	gamedata = [];
        for (var i = 0; i < data.length; i++) {
        	var dataList = {};
        	var teamdata = [];
            var infodata = [];
            var leagueInfodata = [];
	        try{
	            var LL = {
		           	summonerId: data[i].leagueInfo[0].summonerId,
		           	queueType: data[i].leagueInfo[0].queueType,
		           	tier: data[i].leagueInfo[0].tier,
		           	ranked: data[i].leagueInfo[0].rank,
		           	leaguePoints: data[i].leagueInfo[0].leaguePoints,
		           	wins: data[i].leagueInfo[0].wins,
		           	losses: data[i].leagueInfo[0].losses
	        };
	        }catch{
	        	var LL = {
	    	           	summonerId: data[i].summonerId,
	    	           	queueType: 'RANKED_SOLO_5x5',
	    	           	tier: 'unRank',
	    	           	ranked: 'unRank',
	    	           	leaguePoints: 0,
	    	           	wins: 0,
	    	           	losses: 0
	    	        };
	        }
            leagueInfodata.push(LL);
            dataList.leagueInfo = leagueInfodata;
            
            
            for (var k = 0; k < data[i].info.teams.length; k++) {
                var tt = {
                    matchId: data[i].metadata.matchId,
                    teamchampionkills: data[i].info.teams[k].objectives.champion.kills,
                    teambaronkills: data[i].info.teams[k].objectives.baron.kills,
                    teamdragonkills: data[i].info.teams[k].objectives.dragon.kills,
                    teamhordekills: data[i].info.teams[k].objectives.horde.kills,
                    teaminhibitorkills: data[i].info.teams[k].objectives.inhibitor.kills,
                    teamriftHeraldkills: data[i].info.teams[k].objectives.riftHerald.kills,
                    teamtowerkills: data[i].info.teams[k].objectives.tower.kills,
                    teamwin: data[i].info.teams[k].win,
//                     bans: []  // Initialize bans array
                };

                // Populate bans array
//                 for (var z = 0; z < data[i].info.teams[k].bans.length; z++) {
//                     var bb = {
//                     	matchId: data[i].metadata.matchId,
//                         championId: data[i].info.teams[k].bans[z].championId,
//                         pickTurn: data[i].info.teams[k].bans[z].pickTurn
//                     };
//                     tt.bans.push(bb);
//                 }

                teamdata.push(tt);
                dataList.teams = teamdata;
            }
            for (var j = 0; j < data[i].info.participants.length; j++) {
                var gg = {
                    matchId: data[i].metadata.matchId,
                    gameDuration: data[i].info.gameDuration,
                    gameVersion: data[i].info.gameVersion,
                    gameStartTimestamp: data[i].info.gameStartTimestamp,
                    queueId: data[i].info.queueId,
                    riotIdGameName: data[i].info.participants[j].riotIdGameName,
                    riotIdTagline: data[i].info.participants[j].riotIdTagline,
                    summonerId: data[i].info.participants[j].summonerId,
                    summonerLevel: data[i].info.participants[j].summonerLevel,
                    gameMode: data[i].info.gameMode,
                    teamId: data[i].info.participants[j].teamId,
                    win: data[i].info.participants[j].win,
                    championName: data[i].info.participants[j].championName,
                    kills: data[i].info.participants[j].kills,
                    deaths: data[i].info.participants[j].deaths,
                    assists: data[i].info.participants[j].assists,
                    kda: ((data[i].info.participants[j].kills + data[i].info.participants[j].assists)/data[i].info.participants[j].deaths).toFixed(2) ,
                    lane: data[i].info.participants[j].lane,
                    summoner1Id: data[i].info.participants[j].summoner1Id,
                    summoner2Id: data[i].info.participants[j].summoner2Id,
                    item0: data[i].info.participants[j].item0,
                    item1: data[i].info.participants[j].item1,
                    item2: data[i].info.participants[j].item2,
                    item3: data[i].info.participants[j].item3,
                    item4: data[i].info.participants[j].item4,
                    item5: data[i].info.participants[j].item5,
                    item6: data[i].info.participants[j].item6,
                    perks1: data[i].info.participants[j].perks.styles[0].selections[0].perk,
                    perks2: data[i].info.participants[j].perks.styles[1].style,
                    totalDamageDealtToChampions: data[i].info.participants[j].totalDamageDealtToChampions,
                    totalDamageTaken: data[i].info.participants[j].totalDamageTaken,
                    totalMinionsKilled: data[i].info.participants[j].totalMinionsKilled
                };
                infodata.push(gg);
                dataList.info = infodata;
            }
            gamedata.push(dataList);
        }
        console.log(gamedata);
		let data2 = JSON.stringify(gamedata);
		console.log("Sending data:", data2);
        $.ajax({
            type: "POST",
            url: "/summonerSaveData",
            data: { encodedData: encodeURIComponent(data2),
            		gameName:gameName,
            		tagLine:tagLine},
            success: function (res) {
                console.log(res);
                
            },
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown);
            }
        });
    }

    function updateGameData() {
        $.ajax({
            type: "POST",
            url: "summonerUpdate",
            data: { gameName: gameName, tagLine: tagLine },
            success: function (data) {
                console.log("Received updated data:", data);
                saveGameDataToServer(data);
//                 displayGameInfo(data);
                
            },
            error: function (xhr, textStatus, errorThrown) {
                handleAjaxError(xhr, textStatus, errorThrown);
            }
        });
    }
    $(document).on("click", ".btn_toggle", function () {
        var currentIndex = $(this).data('index');
        console.log("Clicked on button with index:", currentIndex);
        $('.Toggle' + currentIndex).toggle();
    });

    function handleAjaxError(xhr, textStatus, errorThrown) {
        console.error("Error occurred during AJAX request:", textStatus, errorThrown);
        alert("Error occurred during AJAX request. See the console for details.");
    }
    

});



</script>
<link rel="stylesheet" href="/css/result.css">
<script src="/js/chart/raphael-2.1.4.min.js"></script>
<script src="/js/chart/justgage.js"></script>
<script defer src="/js/result.js"></script>

</head>
<body>
<div class="back"></div>

<div id="wrap">

 <div class="search-bar">
 
    <div class="inner">   
     <form id="searchForm2"class="searchBox-input">    
      <div class="searchBox">      
           <input type="text" id="fullgameName" name="fullgameName" class="nameInfo" placeholder="소환사 이름 + KRI" required />          
           <button type="submit" class="searchBtn"><span class="material-symbols-outlined">search</span></button>              
      </div>   
     </form>       
    </div>
 </div>
 
 <div class="summoner-result">
 
  <div class="inner">
  
  
    <div class="summonerSide">
   
      <div class="summonerInfo">
       
        <div id="summonerPicture"></div>      
        <div class="text">
          <div class="text-nickname">
            <span id="gameNameDisplay">${param.gameName}</span>&nbsp;<span id="tagLineDisplay">#${param.tagLine}</span>
          </div>               
          <button type="button" id="updateButton"><span class="material-symbols-outlined">refresh</span>&nbsp;전적 갱신</button>
        </div>
         
      </div>
     
      <div id="summonerLeagueInfo"></div>
     
      <div class="summonerRecent">
        <div id="additionalTable"></div>
      </div>
   
   </div>
   
   <div class="summonerMain">
   
     <div class="search-result">
     
       <div id="gameInfoTable"></div>
     
     </div>
   
   </div>
   
     
  </div>
   
 </div>
   
</div> 
 
 

	
<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>