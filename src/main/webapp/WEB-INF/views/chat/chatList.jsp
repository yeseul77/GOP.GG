<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/tiles/header.jsp" %>
<title>chatList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
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
    
     <li><button class="btn chatBtn" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions"><span class="material-symbols-outlined">more_vert</span>&nbsp;채팅 관리</button></li>
   
     <li><button id="update"><span class="material-symbols-outlined">refresh</span>&nbsp;업데이트</button></li>
     
     <li><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">방 만들기</button></li>
    
    </ul>
   
   </div>
 
  </div>
  
  <div class="chatroom-list">
    <div class="inner">
    	<div class="list-array">
    	
    	  <div id="clist"></div>
    	     	       
    	
    	</div> 
    </div>
   
  </div>

</div>

 <!-- Button trigger modal -->

<div id="chat-modal">

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">  
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <form action="/chat/createRoom" method="post">
        
        <div class="modal-body">
         <input type="hidden" id="username" name="username" value="${username}" />
         <input type="text" name="name" class="chatRoomInfo" placeholder="  제 목" autocomplete="off"/>
         <textarea name="memo" class="chatRoom-memo" placeholder="  내 용" autocomplete="off" maxlength="80"></textarea>
         
         <div class="line-head">찾고 있는 포지션</div>
         <div class="chatRoomInfo-line">        
          <input type="checkbox" id="top" name="position" value="top" />
          <label for="top">top</label>
          <input type="checkbox" id="jungle" name="position" value="jungle" />
          <label for="jungle">jungle</label>
          <input type="checkbox" id="middle" name="position" value="middle" />
          <label for="middle">middle</label> 
          <input type="checkbox" id="bottom" name="position" value="bottom" />
          <label for="bot">bottom</label> 
          <input type="checkbox" id="supporter" name="position" value="supporter" />
          <label for="supporter">supporter</label>        
         </div>
                
	     <input type="text" name="champion" class="chatRoomInfo" placeholder="  내 챔피언" autocomplete="off"/>	     
       
       </div>
       
       <div class="modal-footer">
        <button type="reset" class="btn btn-secondary">초기화</button>
        <button type="submit" class="btn btn-primary">등록하기</button>
      </div>
     
    </form>  
       
    </div>
  </div>
</div> 

</div>


<div id="chat-offcanvas">

<div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
  <div class="offcanvas-header">   
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <div id="mylist"></div>
  </div>
</div>

</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
  <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
      <img src="..." class="rounded me-2" alt="...">
      <strong class="me-auto">Bootstrap</strong>
      <small>11 mins ago</small>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    <div class="toast-body">
      <div class=submitArea id="submitArea"></div>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/tiles/footer.jsp" %>

</body>

</html>