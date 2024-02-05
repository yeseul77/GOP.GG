<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        kda: data[i].info.participants[j].challenges.kda,
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
            let temp = JSON.stringify(gamedata);
			data2 = { 'gamedataList': temp };
            $.ajax({
                type: "POST",
                url: "summonerSaveData",
                data: data2,
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
        function findSummonerInfo(gameName, tagLine, serverData) {
            // 대소문자를 무시하고 비교
            function isEqualIgnoreCase(str1, str2) {
                return str1.toLowerCase() === str2.toLowerCase();
            }

            // 서버에서 받은 데이터에서 사용자 정보 찾기
            return serverData.find(function (participant) {
                return isEqualIgnoreCase(participant.riotIdGameName, gameName) &&
                       isEqualIgnoreCase(participant.riotIdTagline, tagLine);
            });
        }
        function displayGameInfo(gameInfoList) {
            $("#gameInfoTable tbody").empty();

            if (gameInfoList.length > 0) {
              	var summonerInfo = findSummonerInfo(gameName, tagLine, gameInfoList);
                $.each(gameInfoList, function (index, gameInfo) {
                	if (summonerInfo) {
                        var gameRow = "<tr>" +
                            "<th rowspan='2'>" + gameInfo.gameMode + "</th>" +
                            "<th rowspan='3' colspan='2'>" + gameInfo.championName + "</th>" +
                            "<th rowspan='2'>" + gameInfo.kills + " / " + gameInfo.deaths + " / " + gameInfo.assists + "</th>" +
                            "<th>킬관여율</th>" +
                            "<th rowspan='3'>" + Math.floor(gameInfo.gameDuration / 60) + "분" + ((Math.floor(gameInfo.gameDuration % 60)) < 10 ? "0" : "") + Math.floor(gameInfo.gameDuration % 60) + "초 </th>" +
                            "<td rowspan='3' id ='showMore'><button class='btn_toggle' data-index='" + index + "'>더보기</button></td>" +
                            "</tr>"+
                        	"<tr>" +
                        	"<th>"+ parseFloat(((gameInfo.kills + gameInfo.assists) / gameInfo.teamchampionkills)* 100).toFixed(0) +"%</th>" +
                        	"</tr>" +
                            "<th>" + (gameInfo.win ? "승리" : "패배") + "</th>" +
                            "<th>" + gameInfo.kda.toFixed(2) + "점 </th>" +
                            "<th>골드</th>" +
                            "</tr>";
                        $("#gameInfoTable tbody").append(gameRow);

                    	var showMore = "<tr class='Toggle" + index + "' style='display:none'>" +
                    		"<th colspan='2'>" + gameInfo.gameMode + "</th>" +
                        	"<th>승리(팀컬러)</th>" +
                        	"<th>KDA</th>" +
                        	"<th>입힌피해량</th>" +
                        	"<th>받은피해량</th>" +
                        	"<th>cs</th>" +
                        	"</tr>";
                    	for (var i = 0; i < gameInfo.length; i++) {
                    		showMore += "<tr class='Toggle" + index + "' style='display:none'>" +
                         		"<td> 챔피언 이미지 </td>" +
                            	"<td>" + gameInfo[i].riotIdGameName + "</td>" +
                            	"<td>" + gameInfo[i].championName + "</td>" +
                            	"<td>" + gameInfo[i].kda.toFixed(2) + "점</td>" +
                            	"<td>" + gameInfo[i].totalDamageDealtToChampions + "</td>" +
                            	"<td>" + gameInfo[i].totalDamageTaken + "</td>" +
                            	"<td>" + gameInfo[i].totalMinionsKilled + "개</td>" +
                            	"</tr>";
                    	};

                $("#gameInfoTable tbody").append(showMore);
                console.log(showMore);
            }
        });
    } else {
        $("#gameInfoTable tbody").append("<tr><td colspan='3'>게임 정보가 없습니다.</td></tr>");
    }
}

        function handleAjaxError(xhr, textStatus, errorThrown) {
            console.error("Error occurred during AJAX request:", textStatus, errorThrown);
            alert("Error occurred during AJAX request. See the console for details.");
        }


        $(document).on("click", ".btn_toggle", function () {
            var currentIndex = $(this).data('index');
            console.log("Clicked on button with index:", currentIndex);
            $('.Toggle' + currentIndex).toggle();
        });
    });

</script>
</head>
<body>
<h1>Summoner Search</h1>
<form id="searchForm">
    <label for="gameName">gameName:</label>
    <input type="text" id="gameName" name="gameName" required>
    <label for="tagLine">Tag Line:</label>
    <input type="text" id="tagLine" name="tagLine"> <!-- 태그라인 입력 필드 추가 -->
    <input type="submit" value="summonerSearch">
    <button type="button" id="updateButton">전적 갱신</button>
</form>

<h2>Game Information</h2>
<div >
    <table id="gameInfoTable" align="center" border="1" width = "600">
       <tr>
        <th rowspan="2">게임 모드</th>
        <th rowspan="3" colspan="2"> 챔피언 사진</th>
        <th rowspan="2">킬/데스/어시</th>
        <th>킬관여율</th>
       <th rowspan="3">게임시간</th>
       <td rowspan="3" id ="showMore"><button>더보기</button></td>

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