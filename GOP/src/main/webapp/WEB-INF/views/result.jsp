<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        console.log("Received gameData in result page:", gameInfoList2);
        displayGameInfo(gameInfoList2);
    } else {
        console.log("No gameInfoList parameter found.");
    }
   	function displayGameInfo(gameInfoList) {
   		$("#gameInfoTable tbody").empty();
   		$("#additionalTable tbody").empty();
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
   	   	                    var summonerLeagueInfo = "<div><strong>League Info:</strong><br>" +
   	   	              			"<span>Tier: " + leagueInfoForSummoner.tier + "</span><br>" +
   	   	              			"<span>ranked: " + leagueInfoForSummoner.ranked + "</span><br>" +
   	   	              			"<span>" + leagueInfoForSummoner.leaguePoints + "LP</span><br>" +
   	   	              			"<span>win: " + leagueInfoForSummoner.wins + "승</span><br>" +
   	   	              			"<span>lose: " + leagueInfoForSummoner.losses + "패</span><br>" +
   	   	              			"<span>승률: " + (leagueInfoForSummoner.wins/(leagueInfoForSummoner.wins+leagueInfoForSummoner.losses)*100).toFixed(0) + "%</span><br>";
   	   	                }
   	            		var kdaDisplay;
   	            		if (playerInfo.kda === 0) {
   	            		    kdaDisplay = "perfect";
   	            		} else {
   	            		    kdaDisplay = playerInfo.kda + "평점";
   	            		}
   	            		var gameDisplay;
   	            		if (playerInfo.gameDuration < 240){
   	            			gameDisplay = "다시하기";
   	            		} else{
   	            			gameDisplay = playerInfo.gameMode;
   	            		}
   	                	var gameRow = "<tr>" +
   	                    	"<th rowspan='2'>" + gameDisplay + "</th>" +
   	                    	"<th rowspan='3' colspan='2'>" + playerInfo.championName + "</th>" +
   	                    	"<th rowspan='2'>" + playerInfo.kills + " / " + playerInfo.deaths + " / " + playerInfo.assists + "</th>" +
   	                    	"<th>킬관여율</th>" +
   	                    	"<th rowspan='3'>" + Math.floor(playerInfo.gameDuration / 60) + "분" + ((Math.floor(playerInfo.gameDuration % 60)) < 10 ? "0" : "") + Math.floor(playerInfo.gameDuration % 60) + "초 </th>" +
   	                    	"<td rowspan='3' class='showMore'><button class='btn_toggle' data-index='" + index + "'>더보기</button></td>" +
   	                    	"</tr>" +
   	                    	"<tr>" +
   	                    	"<th>"+ parseFloat(((playerInfo.kills + playerInfo.assists) / matchingTeam.teamchampionkills)* 100).toFixed(0) +"%</th>" +
   	                    	"</tr>" +
   	                    	"<tr>" +
   	                    	"<th>" + (playerInfo.win ? "승리" : "패배") + "</th>" +
       	                    "<th>" + kdaDisplay + "</th>" +
	   	                    "<th>골드</th>" +
   	                    	"</tr>";
   	                	$("#gameInfoTable tbody").append(gameRow);
   	                    
   	                  	// 챔피언이 이미 등록되어 있는지 확인하고, 등록되어 있지 않으면 초기값으로 설정
   		        	}
   		        }
               	

   		        var showMore =  "<tr class='Toggle" + index + "' style='display:none'>" +
       		        "<th colspan='2'>" + gameInfo.info[0].gameMode + "</th>" +
   		            "<th>승리(팀컬러)</th>" +
   		            "<th>KDA</th>" +
   		            "<th>입힌피해량</th>" +
   		            "<th>받은피해량</th>" +
   		            "<th>cs</th>" +
   		            "</tr>";
   		        for (var k = 0; k < gameInfo.info.length; k++) {
   		       	    var winTeamPlayerInfo = gameInfo.info[k];
   		       		var kdaDisplay;
           			if (winTeamPlayerInfo.kda === 0) {
           		    	kdaDisplay = "perfect";
           			} else {
           		    	kdaDisplay = winTeamPlayerInfo.kda.toFixed(2) + "평점";
           			}
   		       	    if(winTeamPlayerInfo.win === true){
   		            showMore += "<tr class='Toggle" + index + "' style='display:none'>" +
       		            "<td> 챔피언 이미지 </td>" +
   		                "<td>" + winTeamPlayerInfo.riotIdGameName + "</td>" +
   		                "<td>" + winTeamPlayerInfo.championName + "</td>" +
   		                "<td>" + kdaDisplay + "</td>" +
   		                "<td>" + winTeamPlayerInfo.totalDamageDealtToChampions + "</td>" +
   		                "<td>" + winTeamPlayerInfo.totalDamageTaken + "</td>" +
   		                "<td>" + winTeamPlayerInfo.totalMinionsKilled + "개</td>" +
   		                "</tr>";
   		       	    }
   		        }
   		     	showMore +=  "<tr class='Toggle" + index + "' style='display:none'>" +
		        	"<th colspan='2'>" + gameInfo.info[0].gameMode + "</th>" +
	            	"<th>패배(팀컬러)</th>" +
	            	"<th>KDA</th>" +
	            	"<th>입힌피해량</th>" +
	            	"<th>받은피해량</th>" +
	            	"<th>cs</th>" +
	            	"</tr>";
	            for (var k = 0; k < gameInfo.info.length; k++) {
       		       	var loseTeamPlayerInfo = gameInfo.info[k];
       		       	if(loseTeamPlayerInfo.win === false){
       		     		var kdaDisplay;
        				if (loseTeamPlayerInfo.kda === 0) {
        		    		kdaDisplay = "perfect";
        				} else {
	        		    	kdaDisplay = loseTeamPlayerInfo.kda.toFixed(2) + "평점";
        				}
       		        	showMore += "<tr class='Toggle" + index + "' style='display:none'>" +
		       		        "<td> 챔피언 이미지 </td>" +
       		            	"<td>" + loseTeamPlayerInfo.riotIdGameName + "</td>" +
       		            	"<td>" + loseTeamPlayerInfo.championName + "</td>" +
       		            	"<td>" + kdaDisplay + "</td>" +
       		            	"<td>" + loseTeamPlayerInfo.totalDamageDealtToChampions + "</td>" +
       		            	"<td>" + loseTeamPlayerInfo.totalDamageTaken + "</td>" +
       		            	"<td>" + loseTeamPlayerInfo.totalMinionsKilled + "개</td>" +
       		            	"</tr>";
       		       	}
       		    }
   		        $("#gameInfoTable tbody").append(showMore);
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
        	    playerRow += "<tr>" + 
        	        "<td>" + champion + "</td>";

        	    // 챔피언별 평균 KDA와 승률 추가
        	    if (championStats[champion]) {
        	    	var avgKills = parseFloat((championStats[champion].totalKills / championStats[champion].gamesPlayed).toFixed(1));
        	    	var avgDeaths = parseFloat((championStats[champion].totalDeaths / championStats[champion].gamesPlayed).toFixed(1));
        	    	var avgAssists = parseFloat((championStats[champion].totalAssists / championStats[champion].gamesPlayed).toFixed(1));
        	        var avgKDA = ((avgKills + avgAssists) / avgDeaths).toFixed(2);
        	        var avgWinRate = ((championStats[champion].totalWins / championStats[champion].gamesPlayed) * 100).toFixed(0) + "%";
        	        playerRow += "<td>" + avgKills.toFixed(1) + "/" + avgDeaths.toFixed(1) + "/" + avgAssists.toFixed(1) + "</td>" +
        	            "<td>" + avgKDA + "평점</td>" +
        	            "<td>" + avgWinRate + "</td>";
        	    } else {
        	        // 챔피언별 평균 값이 없는 경우 처리
        	        playerRow += "<td colspan='3'>평균 값 없음</td>";
        	    }
        	    playerRow += "<td>" + playCount + "게임</td>" +
        	    "</tr>";
         }

         $("#additionalTable tbody").append(summonerLeagueInfo);
         $("#additionalTable tbody").append(playerRow);
       		
  		} else {
   			$("#gameInfoTable tbody").append("<tr><td colspan='3'>게임 정보가 없습니다.</td></tr>");
   		}
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
	        var LL = {
	           	summonerId: data[i].leagueInfo[0].summonerId,
	           	queueType: data[i].leagueInfo[0].queueType,
	           	tier: data[i].leagueInfo[0].tier,
	           	ranked: data[i].leagueInfo[0].rank,
	           	leaguePoints: data[i].leagueInfo[0].leaguePoints,
	           	wins: data[i].leagueInfo[0].wins,
	           	losses: data[i].leagueInfo[0].losses
	        };
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
            data: { encodedData: encodeURIComponent(data2)},
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
 
 <div class="summonerInfo">
   <div class="inner">
   
     <p>Game Name: <span id="gameNameDisplay">${param.gameName}</span></p>
     <p>Tag Line: <span id="tagLineDisplay">${param.tagLine}</span></p>
     <button type="button" id="updateButton"><span class="material-symbols-outlined">refresh</span>&nbsp;전적 갱신</button>
   
   </div>
 </div>
 
 
 <div class="search-result">
 
   <div class="inner">
   
      <table align="center" border="1" width="800">
        <tr>
            <td>
                <div>
                    <table id="additionalTable" align="center" border="1" width="200">
                        <tr>
                            <!-- 추가 테이블 내용을 여기에 추가하세요 -->
                            <td></td>
                        </tr>
                        <tr>
                            <!-- 필요한 만큼 행/열을 추가하세요 -->
                            <td></td>
                        </tr>
                    </table>
                </div>
            </td>
            <td>
                <div>
                    <table id="gameInfoTable" align="center" border="1" width="600">
                        <tr>
                            <!-- 게임 정보 테이블 내용을 여기에 추가하세요 -->
                            <td></td>
                        </tr>
                        <tr>
                            <!-- 필요한 만큼 행/열을 추가하세요 -->
                            <td></td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
   
   </div>
		
 </div>
 

</div>
	
<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>