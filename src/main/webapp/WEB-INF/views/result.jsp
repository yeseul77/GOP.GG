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
        <th rowspan="2">���� ���</th>
        <th rowspan="3" colspan="2"> è�Ǿ� ����</th>
        <th rowspan="2">ų/����/���</th>
        <th>ų������</th>
       <th rowspan="3">���ӽð�</th>
       <td rowspan="3" id ="showMore"><button>������</button></td>

    </tr>
    <tr>
        <th>777</th> 
    </tr>
    <tr>
        <th>����</th>
        <th>���KDa</th>
        <th>���</th>
    </tr>
    </table>
</div>
</body>
</html>