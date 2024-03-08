<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tiles/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>championInfo</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/js/aramInfo.js"></script>
<!-- <link rel="stylesheet" href="/css/champion.css"> -->
</head>
<body>
	<input type="hidden" id="championInfo" name="championInfo"
		value='${cName}' />
	<main>
		<div class="championInfo" style="display: flex; margin-top: 80px;">
			<div class="champion-box"></div>
			<div class="champion-info"></div>
			<div class="bottom-info">
				<div class="champion-skills"></div>
				<p class="info-desc"></p>
			</div>
			<div class="lineButton"></div>
			<div class="allRate">
				<div class="rates"></div>
			</div>
			<div class="runeList">
				<div class="main"></div>
				<div class="mainPerks"></div>
				<div class="subRune"></div>
				<div class="subPerks"></div>
				<div class="statPerks"></div>
			</div>
			<div class="spell">
				<div class="playerSpell">소환사 주문</div>
				<div class="spellList"></div>
			</div>
			<div class="itemList">
				<div class="item">
					<div id="startItem">아이템 빌드</div>
					<div>
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
	</main>


	<%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>