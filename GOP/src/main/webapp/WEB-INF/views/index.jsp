<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Summoner Search</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>
    $(document).ready(function () {
        var gameName;
        var tagLine;

        $("#searchForm").submit(function (event) {
            event.preventDefault();
            gameName = $("#gameName").val();
            tagLine = $("#tagLine").val();

            if (gameName.trim() === "") {
                alert("gameName cannot be empty.");
                return;
            }

            $.ajax({
                type: "POST",
                url: "summonerSearch",
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
                    	teamwin: data[i].info.teams[k].win
            		}
            		teamdata.push(tt);
            		dataList.teams = teamdata
            	};
                for (var j = 0; j < data[i].info.participants.length; j++) {
                    var gg = {
                        matchId: data[i].metadata.matchId,
                        gameDuration: data[i].info.gameDuration,
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
//                     displayGameInfo(data);
                },
                error: function (xhr, textStatus, errorThrown) {
                    handleAjaxError(xhr, textStatus, errorThrown);
                }
            });
        }
       	function displayGameInfo(gameInfoList) {
       		$("#gameInfoTable tbody").empty();
			
       		if (gameInfoList.length > 0) {
       		 	for (var index = 0; index < gameInfoList.length; index++) {
       	        	var gameInfo = gameInfoList[index];
       	        
       	        // gameInfo.info 배열에서 해당 플레이어의 정보를 찾음
       	        	for (var j = 0; j < gameInfo.info.length; j++) {
       	            	var playerInfo = gameInfo.info[j];
       	            
       	            // 플레이어의 게임 이름과 태그 라인이 일치할 경우에만 표시
       	            	if (playerInfo.riotIdGameName === gameName && playerInfo.riotIdTagline === tagLine) {
       	                	var gameRow = "<tr>" +
       	                    	"<th rowspan='2'>" + playerInfo.gameMode + "</th>" +
       	                    	"<th rowspan='3' colspan='2'>" + playerInfo.championName + "</th>" +
       	                    	"<th rowspan='2'>" + playerInfo.kills + " / " + playerInfo.deaths + " / " + playerInfo.assists + "</th>" +
       	                    	"<th>킬관여율</th>" +
       	                    	"<th rowspan='3'>" + Math.floor(playerInfo.gameDuration / 60) + "분" + ((Math.floor(playerInfo.gameDuration % 60)) < 10 ? "0" : "") + Math.floor(playerInfo.gameDuration % 60) + "초 </th>" +
       	                    	"<td rowspan='3' class='showMore'><button class='btn_toggle' data-index='" + index + "'>더보기</button></td>" +
       	                    	"</tr>" +
       	                    	"<tr>" +
       	                    	"<th>"+ parseFloat(((playerInfo.kills + playerInfo.assists) / gameInfo.teams[0].teamchampionkills)* 100).toFixed(0) +"%</th>" +
       	                    	"</tr>" +
       	                    	"<tr>" +
       	                    	"<th>" + (playerInfo.win ? "승리" : "패배") + "</th>" +
	       	                    "<th>" + playerInfo.kda + "점 </th>" +
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
	<h1>Summoner Search</h1>
	<form id="searchForm">
		<label for="gameName">gameName:</label> <input type="text"
			id="gameName" name="gameName" required> <label for="tagLine">Tag
			Line:</label> <input type="text" id="tagLine" name="tagLine">
		<!-- 태그라인 입력 필드 추가 -->
		<input type="submit" value="summonerSearch">
		<button type="button" id="updateButton">전적 갱신</button>
	</form>

	<h2>Game Information</h2>
	<div>
		<table id="gameInfoTable" align="center" border="1" width="600">
			<tr>
				<th rowspan="2">게임 모드</th>
				<th rowspan="3" colspan="2">챔피언 사진</th>
				<th rowspan="2">킬/데스/어시</th>
				<th>킬관여율</th>
				<th rowspan="3">게임시간</th>
				<td rowspan="3" id="showMore"><button>더보기</button></td>

			</tr>
			<tr>
				<th>777</th>
			</tr>
			<tr>
				<th>승패</th>
				<th>평균KDa</th>
				<th>골드</th>
			</tr>
		</table>
	</div>

</body>
</html>