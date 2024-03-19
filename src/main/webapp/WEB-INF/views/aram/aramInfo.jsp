<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tiles/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="/css/aramInfo.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="/js/aramInfo.js"></script>
</head>
<body>

<div class="back"></div>

<div id="wrap">

 <div class="inner">
 
   <input type="hidden" id="championInfo" name="championInfo" value='${cName}' />
		
   <div class="championInfo">
   
    <div class="info-left">
     <div class="left-first">
       <div class="champion-box"></div>
       <div class="champion-detail">
        <div class="detail-up">
          <div class="champion-info"></div>         
        </div>
        <div class="bottom-info">
            <ul class="qwer">
			   <li>Q</li>
			   <li>W</li>
			   <li>E</li>
			   <li>R</li>
			</ul>
			<div class="champion-skills"></div>
			<p class="info-desc"></p>
		</div>
       </div>
     </div>
     <div class="left-second">
       <div class="spell">
		 <div class="playerSpell">소환사 주문</div>
		 <div class="spellList"></div>
	   </div>
     </div>
       
    </div>
    
    <div class="info-chart">
    
      <div class="chart-box">
        <div id="myChart"></div>
        <div id="myChartTwo"></div>    
      </div>
      
      <div class="allRate">
		<div class="rates"></div>
	  </div>
    
    </div>
      
   </div>
   
   <div class="championTree">
   
    <div class="Tree-left">
    
      <div class="runeBuild">
        <div class="runeTitle">룬 세팅</div>
        <div class="runeList">
         <div class="listLine1"></div>
         <div class="listLine2"></div>
         <div class="listLine3"></div>
         <div class="runeFirst">
           <div class="main"></div>
		   <div class="mainPerks"></div>
         </div>
		 <div class="runeSecond">
		   <div class="subRune"></div>
		   <div class="subPerks"></div>
		 </div>		 
		 <div class="statPerks"></div>
	   </div>
     </div>
    
     
	</div>   
     
    <div class="Tree-right">
    	
			<div id="startItem">아이템 빌드</div>
			<div class="arrowFirst">
			  <div class="material-symbols-outlined">chevron_right</div>
			  <div class="material-symbols-outlined">chevron_right</div>
			</div>			
			<div class="arrowSecond">
			  <div class="material-symbols-outlined">chevron_right</div>
			  <div class="material-symbols-outlined">chevron_right</div>
			</div>
			<div class="arrowThird">
			  <div class="material-symbols-outlined">chevron_right</div>
			  <div class="material-symbols-outlined">chevron_right</div>
			</div>			
			 <table class="startItemList">
				<colgroup>
				 <col>
				 <col width="120">
				 <col width="120">
				</colgroup>
			</table>				  
	 
    
    </div>	
			
 
   </div>		

 </div>
 
</div>

	
<%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>