<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatList</title>
<%@include file="/WEB-INF/tiles/header.jsp" %>
<link rel="icon" href="/images/favicon.ico">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/css/chatList.css">
<script defer src="/javascript/chatlist.js"></script>
</head>

<body>

<div class="back"></div>

<div id="wrap">

 <!-- Button trigger modal -->

<div class="chat-modal">

  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  Launch static backdrop modal
</button>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button>
      </div>
    </div>
  </div>
</div>

</div>




  <input type="hidden" id="username" name="username" value="${username}" /></br></br>
<!-- 이거 모달이 이쁘지 않을깜 -->
<form action="/chat/createRoom" method="post">
	제목: <input type="text" name="name" placeholder="채팅방 이름"></br>
	포지션: <input type="text" name="position" placeholder="선호위치"></br>
	챔피언: <input type="text" name="champion" placeholder="자신의 챔피언"></br>
	메모: <input type="text" name="memo" placeholder="메모"></br>
	<button type="submit">방 만들기</button>	
</form>
<button id="update">업데이트</button>
<div id="clist"></div>
<div class=submitArea id=submitArea></div>

</div>



<%@include file="/WEB-INF/tiles/footer.jsp" %>

</body>

</html>