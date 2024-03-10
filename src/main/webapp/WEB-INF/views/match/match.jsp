<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/tiles/header.jsp" %>
<meta charset="UTF-8">
<title>챔피언 시너지 GOP.GG</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="/css/match.css">
<script defer src="/js/match.js"></script>
<%--  <%@include file="/WEB-INF/tiles/header.jsp" %> --%>
</head>
<body>

<div class="back"></div>

<div id="wrap">

  <div class="synergy-head">
    <div class="inner">
      <div class="search">
        <input type="text" id="championName" name="championName" class="championInput" placeholder="&nbsp;&nbsp;챔피언 검색"/>
        <div class="material-symbols-outlined">search</div>
        <select id= "teamPosition" name="teamPosition" class="championSelect">
		  <option value="">&nbsp;내 포지션</option>
		  <option value="top">top</option>
		  <option value="middle">middle</option>
		  <option value="jungle">jungle</option>
		  <option value="bottom">bottom</option>
		  <option value="utility">support</option>
	    </select>
	    <div class="position" onclick="handleClick(event)">
	      <button id="myteamPosition" onclick="search('top')"><img src="/images/top.svg" alt="top" class="lineImage"><span class="lineText">탑</span></button>
	      <button id="myteamPosition" onclick="search('middle')"><img src="/images/middle.svg" alt="middle" class="lineImage"><span class="lineText">미드</span></button>
	      <button id="myteamPosition" onclick="search('jungle')"><img src="/images/jungle.svg" alt="jungle" class="lineImage"><span class="lineText">정글</span></button>
	      <button id="myteamPosition" onclick="search('bottom')"><img src="/images/bottom.svg" alt="bottom" class="lineImage"><span class="lineText">바텀</span></button>
	      <button id="myteamPosition" onclick="search('utility')"><img src="/images/supporter.svg" alt="supporter" class="lineImage"><span class="lineText">서폿</span></button>
	    </div>
      </div>     
    </div>
  </div>
  
  <div class="synergy-result">
    <div class="inner">
      <div class="sortBtn1">
        <button id="ascendingButton1"><span class="material-symbols-outlined">arrow_drop_up</span></button>
        <button id="descendingButton1"><span class="material-symbols-outlined">arrow_drop_down</span></button>
      </div>
      <div class="sortBtn">
        <button id="ascendingButton"><span class="material-symbols-outlined">arrow_drop_up</span></button>
        <button id="descendingButton"><span class="material-symbols-outlined">arrow_drop_down</span></button>
      </div>
       <table id="synergy-table">
    	 <colgroup>
      	  <col width="15%">
      	  <col width="15%">
      	  <col width="55%">
      	  <col width="15%"> 
    	 </colgroup>
    	 <thead id="result">
      	  <tr class="result-head">     	  
       	    <th class="head-menu"><span>순&nbsp;&nbsp;위</span></th>
       	    <th class="head-menu"><span>시너지&nbsp;&nbsp;챔피언</span></th>
       	    <th class="head-menu"><span>시너지&nbsp;&nbsp;승률</span></th>
       	    <th class="head-menu"><span>게임&nbsp;&nbsp;판수</span></th>
      	  </tr>    
    	</thead>
    	
  	  </table>
    </div>
  </div>
  
  
  
  
  	
</div>



<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>