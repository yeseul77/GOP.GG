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
		
   		if (gameInfoList.length > 0) {
   		 	for (var index = 0; index < gameInfoList.length; index++) {
   	        	var gameInfo = gameInfoList[index];
   	        
   	        // gameInfo.info 배열에서 해당 플레이어의 정보를 찾음
   	        	for (var j = 0; j < gameInfo.info.length; j++) {
   	            	var playerInfo = gameInfo.info[j];
   	            	var matchingTeam = gameInfo.teams.find(function(team) {
   	            	    return team.teamwin === playerInfo.win;
   	            	});
   	            // 플레이어의 게임 이름과 태그 라인이 일치할 경우에만 표시
   	            	if (playerInfo.riotIdGameName === gameName && playerInfo.riotIdTagline === tagLine) {
   	            		var kdaDisplay;
   	            		if (playerInfo.kda === 0) {
   	            		    kdaDisplay = "perfect";
   	            		} else {
   	            		    kdaDisplay = playerInfo.kda + "점";
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
   		       	    if(winTeamPlayerInfo.win === true){
   		            showMore += "<tr class='Toggle" + index + "' style='display:none'>" +
       		            "<td> 챔피언 이미지 </td>" +
   		                "<td>" + winTeamPlayerInfo.riotIdGameName + "</td>" +
   		                "<td>" + winTeamPlayerInfo.championName + "</td>" +
   		                "<td>" + winTeamPlayerInfo.kda + "점</td>" +
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
       		        showMore += "<tr class='Toggle" + index + "' style='display:none'>" +
	       		        "<td> 챔피언 이미지 </td>" +
       		            "<td>" + loseTeamPlayerInfo.riotIdGameName + "</td>" +
       		            "<td>" + loseTeamPlayerInfo.championName + "</td>" +
       		            "<td>" + loseTeamPlayerInfo.kda + "점</td>" +
       		            "<td>" + loseTeamPlayerInfo.totalDamageDealtToChampions + "</td>" +
       		            "<td>" + loseTeamPlayerInfo.totalDamageTaken + "</td>" +
       		            "<td>" + loseTeamPlayerInfo.totalMinionsKilled + "개</td>" +
       		            "</tr>";
       		       	}
       		    }
   		        $("#gameInfoTable tbody").append(showMore);
   		    }
       		
  		} else {
   			$("#gameInfoTable tbody").append("<tr><td colspan='3'>게임 정보가 없습니다.</td></tr>");
   		}
	};

    $("#searchForm2").submit(function (event) {
        event.preventDefault();
        gameName = $("#gameName").val();
        tagLine = $("#tagLine").val();
        updateURL(gameName, tagLine);

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
            for (var k = 0; k < data[i].info.teams.length; k++) {
                var tt = {
                    matchId: data[i].metadata.matchId,
                    teamchampionkills: data[i].info.teams[k].objectives.champion.kills,
                    teamwin: data[i].info.teams[k].win,
                    bans: []  // Initialize bans array
                };

                // Populate bans array
                for (var z = 0; z < data[i].info.teams[k].bans.length; z++) {
                    var bb = {
                    	matchId: data[i].metadata.matchId,
                        championId: data[i].info.teams[k].bans[z].championId,
                        pickTurn: data[i].info.teams[k].bans[z].pickTurn
                    };
                    tt.bans.push(bb);
                }

                teamdata.push(tt);
                dataList.teams = teamdata;
            }
            for (var j = 0; j < data[i].info.participants.length; j++) {
                var gg = {
                    matchId: data[i].metadata.matchId,
                    gameDuration: data[i].info.gameDuration,
                    gameStartTimestamp: data[i].info.gameStartTimestamp,
                    queueId: data[i].info.queueId,
                    riotIdGameName: data[i].info.participants[j].riotIdGameName,
                    riotIdTagline: data[i].info.participants[j].riotIdTagline,
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
 	<p>Game Name: <span id="gameNameDisplay">${param.gameName}</span></p>
	<p>Tag Line: <span id="tagLineDisplay">${param.tagLine}</span></p>
	<form id="searchForm2">
		<label for="gameName">gameName:</label> <input type="text"
			id="gameName" name="gameName" required> <label for="tagLine">Tag
			Line:</label> <input type="text" id="tagLine" name="tagLine">
		<!-- 태그라인 입력 필드 추가 -->
		<input type="submit" value="summonerSearch2">
		<button type="button" id="updateButton">전적 갱신</button>
	</form>
	<div>
		<table id="gameInfoTable" align="center" border="1" width="600">
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
		</table>
	</div>
</body>
</html>