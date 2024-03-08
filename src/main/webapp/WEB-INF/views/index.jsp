<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!--헤더 위치 --> 
 <title>메인 페이지 GOP.GG</title> 
 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
 <script>
 // 메시지 변수 설정
 const message = '${message}';  // 컨트롤러에서 addFlashAttribute로 추가한 메시지를 받음
 const isLoggedIn = ${sessionScope.isLoggedIn != null && sessionScope.isLoggedIn}; // 로그인 상태
 const loggedInUser = '${sessionScope.email}'; // 로그인한 사용자의 이메일

 console.log("Message:", message, "Is Logged In:", isLoggedIn, "Logged In User:", loggedInUser);

 $(function() {
     msgPrint();
     loginStatus();
     
 });

 // 메시지 출력 함수
 function msgPrint() {
     if (message !== '') {
         alert(message);
     }
 }

 // 로그인 상태에 따른 처리 함수
 function loginStatus() {
     if (isLoggedIn) {
         console.log("User is logged in:", loggedInUser);
         // 로그인 상태에 따른 추가 동작 구현
     } else {
         console.log("User is not logged in.");
         // 비로그인 상태에 따른 추가 동작 구현
     }
 }
 </script>
	<script>
	$(document).ready(function () {
	    $("#searchForm").submit(function (event) {
	        event.preventDefault();
	        const pattern = /^(.+?)\s*(K?R?\d*)$/;
	        var result = pattern.exec($("#fullgameName").val().trim());
	        var gameName = result[1];
	        var tagLine = result[2];
			
	        if (gameName.trim() === "") {
	            alert("gameName cannot be empty.");
	            return;
	        }

	        $.ajax({
	            type: "GET",
	            url: "summonerSearch",
	            data: { gameName: gameName, tagLine: tagLine },
	            success: function (data) {
	                console.log("Received data:", data);
	                // 여기서 result.jsp로 이동하며, data를 query string 또는 session 등을 통해 전달할 수 있음
	                window.location.href = "/summonerSearch?gameName=" + encodeURIComponent(gameName) + "&tagLine=" + encodeURIComponent(tagLine);
	            },
	            error: function (xhr, textStatus, errorThrown) {
	                handleAjaxError(xhr, textStatus, errorThrown);
	            }
	        });
	    });
	});

	function handleAjaxError(xhr, textStatus, errorThrown) {
	    console.error("Error occurred during AJAX request:", textStatus, errorThrown);
	    alert("Error occurred during AJAX request. See the console for details.");
	}

 </script>
    


<link rel="stylesheet" href="/css/index.css">
<script defer src="/js/chatindex.js"></script>
<script defer src="/js/index.js"></script>
<script defer src="/js/rank.js"></script>

</head>

<body>

<!-- BACKGROUND IMAGE -->

  <div class="back"></div>

  <div id="wrap">
  
  	<!-- SECTION LOGO -->
  <section class="main-logo">
    <div class="inner">
      <div class="logo">
        <img src="/images/main-logo.png" alt="mainlogo" />
      </div>
    </div>
  </section>

  
<section class="search">
    <div class="inner">
      <div class="search-bar">
        
         <form id="searchForm" action="/summonerSearch" method="get" class="input">
           <input type="text" id="fullgameName" name="fullgameName" class="fullnameInfo" placeholder="소환사 이름 + KRI" required>          
           <button type="submit"><span class="material-symbols-outlined">search</span></button>
        </form>
   
      </div>
    </div>
  </section>


  <!-- DUO -->

  <section class="duo">

    <div class="inner">

      <div class="duo-list">

        <div class="duo-more">
          <a href="javascript:void(0)" target="_blank">듀오찾기</a>
        </div>

        <div class="swiper">
          
          <div class="swiper-wrapper">                          	
          
          </div>
                                 
        </div>

        <div class="swiper-button-prev">
          <div class="material-symbols-outlined">
            arrow_back
          </div>
        </div>
        <div class="swiper-button-next">
          <div class="material-symbols-outlined">
            arrow_forward
          </div>
        </div>

      </div>

    </div>

  </section>

  <!-- BEST PLAYER -->

  <section class="best">

    <div class="inner">

      <div class="bestplayer-title">
        <span class="best-title">[ Today's Best 소환사 ]</span>
      </div>

      <div class="bestplayer-box">
      
        <div class="bestWinner">
          <div class="bestHeader">승&nbsp;&nbsp;률</div>
          <div id= "best-winner" class="best-winner"></div>
        </div>
        
        <div class="bestKda">
          <div class="bestHeader">KDA</div>
          <div id="best-kda" class="best-kda"></div>
        </div>
        
        <div class="bestTime">
          <div class="bestHeader">플레잉&nbsp;타임</div>
          <div id="best-time" class="best-time"></div>
        </div>
    
      </div>

    </div>

  </section>
  
  </div>
  

<!-- 푸터 위치 -->
 <%@include file="/WEB-INF/tiles/footer.jsp" %>
 <!-- 푸터 위치 -->
</body>


</html>