<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="/js/match.js"></script>
<%--  <%@include file="/WEB-INF/tiles/header.jsp" %> --%>
</head>
<body>

<h3>시너지 검색 페이지</h3>
	챔피언 이름 입력:<input type="text" id="championName" name="championName"/>
	포지션:<select id= "teamPosition" name="teamPosition">
			<option value="top">top</option>
			<option value="middle">middle</option>
			<option value="jungle">jungle</option>
			<option value="bottom">bottom</option>
			<option value="utility">support</option>
		</select>
	</br>
	<button id="myteamPosition" onclick="search('top')">탑</button>
	<button id="myteamPosition" onclick="search('middle')">미드</button>
	<button id="myteamPosition" onclick="search('jungle')">정글</button>
	<button id="myteamPosition" onclick="search('bottom')">바텀</button>
	<button id="myteamPosition" onclick="search('utility')">서폿</button>
	<div id="result"></div>
<%-- <%@include file="/WEB-INF/tiles/footer.jsp" %> --%>

</body>
</html>