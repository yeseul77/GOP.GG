<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatList</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/javascript/chatlist.js"></script>
</head>
<body>
<%@include file="/WEB-INF/tiles/header.jsp" %></br>
<input type="hidden" id="username" name="username" value="${username}" /></br></br>
<!-- 이거 모달이 이쁘지 않을깜 -->
<form action="/chat/createRoom" method="post">
	제목: <input type="text" name="name" placeholder="채팅방 이름"></br>
	포지션: <input type="text" name="position" placeholder="선호위치"></br>
	챔피언: <input type="text" name="champion" placeholder="자신의 챔피언"></br>
	<button type="submit">방 만들기</button>	
</form>
<button id="update">업데이트</button>
<div id="clist"></div>
<div class=submitArea id=submitArea></div>
<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>