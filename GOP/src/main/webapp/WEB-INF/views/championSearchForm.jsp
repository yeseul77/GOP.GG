<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챔피언 분석</title>
</head>
<body>
    <h1>챔피언 분석</h1>

    <form id="championSearchForm">
        <label for="championName">챔피언 이름:</label>
        <input type="text" id="championName" name="championName" required>
        <input type="submit" value="조회">
    </form>

    <div id="rankingInfo">
    	<h2>랭킹 정보</h2>
    	<table>
    	    <thead>
 	    	    <tr>
                	<th>순위</th>
                	<th>챔피언 이름</th>
            	    <th>승률</th>
        	    </tr>
    	    </thead>
	        <tbody>
            	<c:forEach items="${rankingList}" var="ranking">
                	<tr>
                    	<td>${ranking.rank}</td>
                    	<td>${ranking.championName}</td>
                    	<td>${ranking.winRate}</td>
                	</tr>
            	</c:forEach>
        	</tbody>
    	</table>
	</div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#championSearchForm").submit(function (event) {
                event.preventDefault();
                var championName = $("#championName").val();
                // AJAX를 사용하여 서버에서 챔피언 정보를 가져와서 동적으로 표시하는 코드를 작성합니다.
                $.ajax({
                    type: "GET",
                    url: "/getChampionInfo", // 챔피언 정보를 가져오는 서버의 엔드포인트
                    data: { championName: championName },
                    success: function (data) {
                        // 챔피언 정보 페이지로 이동하고 챔피언 정보를 보여줍니다.
                        window.location.href = "/championInfo?championName=" + championName;
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        console.error("Error occurred during AJAX request:", textStatus, errorThrown);
                        alert("챔피언 정보를 가져오는 도중 오류가 발생했습니다.");
                    }
                });
            });
        });
    </script>
</body>
</html>