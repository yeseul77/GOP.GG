<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/tiles/header.jsp"%>
<title>칼바람 나락 GOP.GG</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/css/aram.css">
<script defer src="/js/aram.js"></script>
</head>
<body>
<div class="back"></div>

<div id="wrap">

 <div class="inner">

<input type="hidden" id="championNames" name="championNames" value='${championNames}' />

<div id="champion">	
	<nav class="championList">
	
		<aside>
		
		  <div class="searchBar">
		  
		    <div class="searchInput">
		      <label class="hidden" for="filterChampionInput"></label>
			  <input id="searchChampion" class="championInput" type="text" name="searchChampion" placeholder="&nbsp;&nbsp;챔피언 검색 + Enter키 입력" autocomplete="off" class="css-97nwo7 e12jtn8s0" value="">
			  <div class="material-symbols-outlined">search</div>
		    </div>
		    
		    <div class="sortBtn">
                <button id="ascendingButton"><span class="material-symbols-outlined">arrow_drop_up</span></button>
                <button id="descendingButton"><span class="material-symbols-outlined">arrow_drop_down</span></button>
              </div>
              <div class="sortBtn1">
                <button id="ascendingButton1"><span class="material-symbols-outlined">arrow_drop_up</span></button>
                <button id="descendingButton1"><span class="material-symbols-outlined">arrow_drop_down</span></button>
              </div>
              <div class="sortBtn2">
                <button id="ascendingButton2"><span class="material-symbols-outlined">arrow_drop_up</span></button>
                <button id="descendingButton2"><span class="material-symbols-outlined">arrow_drop_down</span></button>
              </div>
              <div class="sortBtn3">
                <button id="ascendingButton3"><span class="material-symbols-outlined">arrow_drop_up</span></button>
                <button id="descendingButton3"><span class="material-symbols-outlined">arrow_drop_down</span></button>
              </div>
              <div class="sortBtn4">
                <button id="ascendingButton4"><span class="material-symbols-outlined">arrow_drop_up</span></button>
                <button id="descendingButton4"><span class="material-symbols-outlined">arrow_drop_down</span></button>
              </div>
		    			
		  
		  </div>			
			
		</aside>
		
		
		<main>
		
			<div></div>
			
		</main>
		
	</nav>
 </div>

 </div>

</div>

<%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>