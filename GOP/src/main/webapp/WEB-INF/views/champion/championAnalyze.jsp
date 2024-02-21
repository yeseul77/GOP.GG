<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/tiles/header.jsp"%>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="/javascript/champion.js"></script> -->

<%-- <input type="hidden" id="championNames" name="championNames" value="${championNames}" /> --%>



<%-- <%@include file="/WEB-INF/tiles/footer.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatList</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/javascript/champion.js"></script>
<link rel="stylesheet" href="/css/champion.css">
</head>
<body>
<input type="hidden" id="championNames" name="championNames" value='${championNames}' />

<div id="champion">
	<aside id="championList"></aside>
</div>

<%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>