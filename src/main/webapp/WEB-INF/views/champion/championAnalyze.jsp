<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/tiles/header.jsp"%>
<title>챔피언 분석 GOP.GG</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/css/champion.css">
<script src="/js/champion.js"></script>
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
		    			
			<div id="lineButton"></div>
		  
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