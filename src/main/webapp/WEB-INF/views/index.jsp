<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
<<<<<<< HEAD
 <!--헤더 위치 -->
    <title>Summoner Search</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
    <script>
=======
 <!--헤더 위치 -->  
 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
 <script src="/js/chatindex.js"></script>
 <script>
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
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
	        var gameName = $("#gameName").val();
	        var tagLine = $("#tagLine").val();

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

// 	            $.ajax({
// 	                type: "POST",
// 	                url: "summonerSearch",
// 	                data: { gameName: gameName, tagLine: tagLine },
// 	                success: function (data) {
// 	                    console.log("Received data:", data);
// 	                    displayGameInfo(data);
// 	                },
// 	                error: function (xhr, textStatus, errorThrown) {
// 	                    handleAjaxError(xhr, textStatus, errorThrown);
// 	                }
// 	            });

	       
	        


 </script>
<link rel="stylesheet" href="/css/index.css">

<script defer src="/js/index.js"></script>
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

  <!-- SECTION SEARCH -->

<!--  <section class="search"> -->
<!--   <div class="inner"> -->
<!--     <div class="search-bar"> -->
<!--       <form id="searchForm"> -->
<!--         <div class="input"> -->
<!--           <input type="text" id="gameName" placeholder="소환사 이름 + #KR1" required> -->
<!--           태그라인 입력 필드 추가 -->
<!--           <input type="text" id="tagLine" placeholder="Tag Line"> -->
<!--         </div> -->
<!--         <div class="search-icon"> -->
<!--           <button type="submit"> -->
<!--             <div class="material-symbols-outlined"> -->
<!--               search -->
<!--             </div> -->
<!--           </button> -->
<!--         </div> -->
<!--       </form> -->
<!--       <div class="search-bar-record"> -->
<!--         <div class="record-bar"> -->
<!--           최근에 본 소환사가 없습니다. -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </section> -->
<section class="search">
	<div class="inner">
	<form id="searchForm" action="/summonerSearch" method="get">
		<label for="gameName">gameName:</label> <input type="text"
			id="gameName" name="gameName" required> <label for="tagLine">Tag
			Line:</label> <input type="text" id="tagLine" name="tagLine">
		<!-- 태그라인 입력 필드 추가 -->
		<div class="search-icon">
		<input type="submit" value="summonerSearch">
		</div>
	</form>
	<div class="search-bar-record">
        <div class="record-bar">
          최근에 본 소환사가 없습니다.
        </div>
      </div>
    </div>
</section>

<<<<<<< HEAD
=======

  <!-- DUO -->

  <section class="duo">

    <div class="inner">

      <div class="duo-list">

        <div class="duo-more">
          <a href="javascript:void(0)" target="_blank">듀오찾기</a>
        </div>

        <div class="swiper">

          <div class="swiper-wrapper">
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">1</a> -->
<!--             </div>	 -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">2</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">3</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">4</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">5</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">6</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">7</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">8</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">9</a> -->
<!--             </div> -->
<!--             <div class="swiper-slide"> -->
<!--               <a href="javascript:void(0)" target="_blank">10</a> -->
<!--             </div> -->
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
        <div class="best-winner">승률</div>
        <div class="best-kda">KDA</div>
        <div class="best-time">플레잉 타임</div>
      </div>

    </div>

  </section>
  
  </div>
  

>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
<!-- 푸터 위치 -->
 <%@include file="/WEB-INF/tiles/footer.jsp" %>
 <!-- 푸터 위치 -->
</body>


</html>