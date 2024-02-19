<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>matching</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script defer src="/javascript/ssejs.js"></script>
</head>
<body>
<h3>matching</h3>
<!-- <p id="result"></p> -->
	title<input id="title" type="text"></input></br>
	position<input id="position" type="text"></input></br>
	memo<input id="memo" type="text"></input></br>
	champion<input id="champion" type="text"></input></br>
<button id="test">test</button>
<h3>방 리스트</h3>
<a class="rlist"></a>
<c:forEach var="rList" items="${rList}">
<div>
	<h3>${rList.title}</h3>5
	<p>${rList.memo}</p>
	<p>${rList.position}</p>
	<p>${rList.champion}</p>
</div>
</c:forEach>

</body>
</html>