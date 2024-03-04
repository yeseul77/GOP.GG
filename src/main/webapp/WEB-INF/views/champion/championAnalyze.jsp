<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include file="/WEB-INF/tiles/header.jsp"%>
<title>championList</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/js/champion.js"></script>
<link rel="stylesheet" href="/css/champion.css">
</head>
<body>
<input type="hidden" id="championNames" name="championNames" value='${championNames}' />
<!-- 티어 상관없이 모든 티어 통틀어서 나오는 승률,픽률,벤률 op챔 나누기-->
<div id="champion">	
	<nav class="championList">
		<aside>
			<label class="hidden" for="filterChampionInput">챔피언 검색(가렌, ㄱㄹ, ...)</label>
			<input id="searchChampion" type="text" name="searchChampion" 
			placeholder="챔피언 검색 (가렌, ㄱㄹ, ...)" autocomplete="off" class="search" value="">
			<div id="lineButton"></div>
		</aside>
		<main>
			<div></div>
		</main>
	</nav>
</div>

<%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>