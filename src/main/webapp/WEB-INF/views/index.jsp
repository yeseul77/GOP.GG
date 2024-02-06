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
                    var gamedata = [];
                    for (var i = 0; i < data.length; i++){
                    	for (var j = 0; j < data[i].info.participants.length; j++ ){
                    		var gg = {};
                    		gg.matchId = data[i].metadata.matchId
                    		gg.gameDuration = data[i].info.gameDuration
                    		gg.queueId = data[i].info.queueId
                    		gg.riotIdGameName = data[i].info.participants[j].riotIdGameName
                    		gg.riotIdTagline = data[i].info.participants[j].riotIdTagline
                    		gamedata.push(gg);
                    	}
                    }
                    let temp = JSON.stringify(gamedata);
    				data2 = { 'gamedataList': temp };
                    $.ajax({
                    	type:"POST",
                    	url: "summonerSaveData",
                    	data: data2,
                    	success: function (res){
                    		console.log(res);
                    	},
                    	error: function (xhr, textStatus, errorThrown) {
                            console.error("Error occurred during AJAX request:");
                            console.error("Status: " + textStatus);
                            console.error("Error: " + errorThrown);
                            alert("Error occurred during AJAX request. See the console for details.");
                        }
                    });
                },
                error: function (xhr, textStatus, errorThrown) {
                    console.error("Error occurred during AJAX request:");
                    console.error("Status: " + textStatus);
                    console.error("Error: " + errorThrown);
                    alert("Error occurred during AJAX request. See the console for details.");
                }
            });
        });
     
        function displayGameInfo(gameInfoList) {
            $("#gameInfoTable tbody").empty();

            if (gameInfoList.length > 0) {
                $.each(gameInfoList, function (index, gameInfo) {
                    var summonerInfo = gameInfo.info.participants.find(function (participant) {
                        return participant.riotIdGameName === gameName && participant.riotIdTagline === tagLine;
                    });
                    var gameWinInfo = gameInfo.info.teams.find(function (team){
                    	return team.win === summonerInfo.win;
                    });
                    if (summonerInfo) {
                        var gameRow = "<tr>" +
                            "<th rowspan='2'>" + gameInfo.info.gameMode + "</th>" +
                            "<th rowspan='3' colspan='2'>" + summonerInfo.championName + "</th>" +
                            "<th rowspan='2'>" + summonerInfo.kills + " / " + summonerInfo.deaths + " / " + summonerInfo.assists + "</th>" +
                            "<th>킬관여율</th>" +
                            "<th rowspan='3'>" + Math.floor(gameInfo.info.gameDuration / 60) + "분" + ((Math.floor(gameInfo.info.gameDuration % 60)) < 10 ? "0" : "") + Math.floor(gameInfo.info.gameDuration % 60) + "초 </th>" +
                            "<td rowspan='3' id ='showMore'><button class='btn_toggle' data-index='" + index + "'>더보기</button></td>" +
                            "</tr>"+
                        	"<tr>" +
                        	"<th>"+ parseFloat(((summonerInfo.kills + summonerInfo.assists) / gameWinInfo.objectives.champion.kills)* 100).toFixed(0) +"%</th>" +
                        	"</tr>" +
                            "<th>" + (summonerInfo.win ? "승리" : "패배") + "</th>" +
                            "<th>" + summonerInfo.challenges.kda.toFixed(2) + "점 </th>" +
                            "<th>골드</th>" +
                            "</tr>";
                        $("#gameInfoTable tbody").append(gameRow);
               

                        var showMore = "<tr class='Toggle" + index + "' style='display:none'>" +
                        	"<th colspan='2'>" + gameInfo.info.gameMode + "</th>" +
                            "<th>승리(팀컬러)</th>" +
                            "<th>KDA</th>" +
                            "<th>입힌피해량</th>" +
                            "<th>받은피해량</th>" +
                            "<th>cs</th>" +
                            "</tr>";
                        for (var i = 0; i < gameInfo.info.participants.length; i++) {
                        	showMore += "<tr class='Toggle" + index + "' style='display:none'>" +
                            	"<td> 챔피언 이미지 </td>" +
                            	"<td>" + gameInfo.info.participants[i].championName + "</td>" +
                            	"<td>" + gameInfo.info.participants[i].riotIdGameName + "</td>" +
                            	"<td>" + gameInfo.info.participants[i].challenges.kda.toFixed(2) + "점</td>" +
                            	"<td>" + gameInfo.info.participants[i].totalDamageDealtToChampions + "</td>" +
                            	"<td>" + gameInfo.info.participants[i].totalDamageTaken + "</td>" +
                            	"<td>" + gameInfo.info.participants[i].totalMinionsKilled + "개</td>" +
                            	"</tr>";
                        }
                        $("#gameInfoTable tbody").append(showMore);
                        console.log(showMore);
                    } else {
                        // summonerInfo가 정의되지 않은 경우 처리
                        console.warn("게임 이름에 대한 SummonerInfo를 찾을 수 없습니다:", gameName);
                    }
                });
            	
            } else {
                $("#gameInfoTable tbody").append("<tr><td colspan='3'>게임 정보가 없습니다.</td></tr>");
            }
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
<<<<<<< HEAD
<h1>Summoner Search</h1>
<form id="searchForm">
    <label for="gameName">gameName:</label>
    <input type="text" id="gameName" name="gameName" required>
    <label for="tagLine">Tag Line:</label>
    <input type="text" id="tagLine" name="tagLine"> <!-- 태그라인 입력 필드 추가 -->
    <input type="submit" value="summonerSearch">
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
=======
 <%@include file="/WEB-INF/tiles/header.jsp" %>


<h1>INDEX.JSP</h1>





 <%@include file="/WEB-INF/tiles/footer.jsp" %>

</body>
    
>>>>>>> 5bf9997d194ad8ecf7044b88bfa0659dec6a0a96
</html>