<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatList</title>
<%@include file="/WEB-INF/tiles/header.jsp" %>
<link rel="icon" href="/images/favicon.ico">


<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/css/chatList.css">
<script defer src="/javascript/chatlist.js"></script>
</head>

<body>

<div class="back"></div>

<div id="wrap">

  <div class="chat-header">

   <div class="inner">
 
    <ul class="header-menu">
   
     <li><button id="update"><span class="material-symbols-outlined">refresh</span>&nbsp;업데이트</button></li>
     
     <li><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">방 만들기</button></li>
    
    </ul>
   
   </div>
 
  </div>
  
  <div class="chatroom-list">
    <div class="inner">
    	<div class="list-array">
    	
    	  <div id="clist"></div>
          <div class=submitArea id=submitArea></div>
    	
    	</div> 
    </div>
   
  </div>
  
  
  

</div>

 <!-- Button trigger modal -->

<div class="chat-modal">

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">  
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="/chat/createRoom" method="post">
        <div class="modal-body">
        <input type="hidden" id="username" name="username" value="${username}" /></br></br>
        
	      제목: <input type="text" name="name" placeholder="채팅방 이름"></br>
	     포지션: <input type="text" name="position" placeholder="선호위치"></br>
	     챔피언: <input type="text" name="champion" placeholder="자신의 챔피언"></br>
	      메모: <input type="text" name="memo" placeholder="메모"></br>
       </div>
       <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">확인</button>
      </div>
     </form>     
    </div>
  </div>
</div>

</div>





  





<%@include file="/WEB-INF/tiles/footer.jsp" %>

</body>

</html>