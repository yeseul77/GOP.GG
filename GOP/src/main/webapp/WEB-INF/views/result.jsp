<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#showMore").click(function () {
                $("#gameInfoTable").toggle();
            });
        });
    </script>
</head>
<body>
	<div >
    <table id="gameInfoTable" align="center" border="1" width = "600">
       <tr>
        <th rowspan="2">게임 모드</th>
        <th rowspan="3" colspan="2"> 챔피언 사진</th>
        <th rowspan="2">킬/데스/어시</th>
        <th>킬관여율</th>
       <th rowspan="3">게임시간</th>
       <td rowspan="3" id ="showMore"><button>더보기</button></td>

    </tr>
    <tr>
        <th>777</th> 
    </tr>
    <tr>
        <th>승패</th>
        <th>평균KDa</th>
        <th>골드</th>
    </tr>
    </table>
</div>
</body>
</html>