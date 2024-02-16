<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="/javascript/chatjs.js"></script>
</head>
<body>
<input type="hidden" id="username" name="username" value="${username}" />
<input type="hidden" id="chatroomId" name="chatroomId" value="${chatroomId}" />
<input type="text" placeholder="메시지 입력" class="content">
<button type="button" value="전송" class="sendBtn" onclick="sendMsg()">전송</button>
<button type="button" value="나가기" class="quit" onclick="quit()">나가기</button>
<div>
	<span>메세지</span>
	<div class="msgArea"></div>
</div>
</body>
</html>