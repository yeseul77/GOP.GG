<<<<<<< HEAD:src/main/webapp/WEB-INF/views/chat/chatList.jsp
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
<%@include file="/WEB-INF/tiles/header.jsp" %>
<input type="hidden" id="username" name="username" value="${username}" />
<form action="/chat/createRoom" method="post">
	<input type="text" name="name" placeholder="채팅방 이름">
	<button type="submit">방 만들기</button>	
</form>
<button id="update">업데이트</button>
<div id="clist">
<c:forEach var="room" items="${roomList}" >
	<div>
	<a href="/chat/chatroom?chatroomId=${room.chatroomId}" name="chatroomId" value="${room.chatroomId}"> ${room.title}</a></br>
	<a>${room.userId}</a></br>
	<button type="button" class="chatroomId" value="${room.chatroomId}">참가신청</button>
	</div>
	</br>
</c:forEach>
</div>
<div class=submitArea>

</div>
<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
=======
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
<input type="hidden" id="username" name="username" value="${username}" />
<form action="/chat/createRoom" method="post">
	<input type="text" name="name" placeholder="채팅방 이름">
<!-- 	<input type="text" name="username" placeholder="유저이름"> -->
	<button type="submit">방 만들기</button>	
</form>
<button id="update">업데이트</button>
<div id="clist">
<c:forEach var="room" items="${roomList}" >
	<div>
	<a href="/chat/chatroom?chatroomId=${room.chatroomId}" name="chatroomId" value="${room.chatroomId}"> ${room.title}</a></br>
	<a>${room.userId}</a>
	</div>
	</br>
</c:forEach>
</div>
</body>
>>>>>>> YS:GOP.GG-main/src/main/webapp/WEB-INF/views/chatting/chatList.jsp
</html>