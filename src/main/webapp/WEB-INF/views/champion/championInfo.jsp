<%@include file="/WEB-INF/tiles/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>championInfo</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/js/championInfo.js"></script>
<!-- <link rel="stylesheet" href="/css/champion.css"> -->
</head>
<body>
<input type="hidden" id="championInfo" name="championInfo" value='${cName}'/>
<div class="championInfo" style="display:flex;">
	<div class="champion-box"></div>
	<div class="champion-info"></div>
	<div class="bottom-info">
		<div class="champion-skills"></div>
		<p class="info-desc"></p>
	</div>
</div>

<%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>