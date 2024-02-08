<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login-page</title>
</head>
<body>
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!-- 헤더 위치 -->
<h3 style="text-align:center;">소환사님 
<span style="font-size:30px;">&#128378;</span> 
    <span style="color: blue">
    <c:out value="${message}"/></span></h3>


<form action="/login" method="post">
    <fieldset style="text-align:center; width: 500px; margin: auto ">
        <legend style="font-size: large; " >[로그인]</legend>

        <div id="LoginForm" style="font-size:medium; font-weight: bold; text-align: center;">
            <div style="height: 40px">
                <label>ID &nbsp; : </label>
                <input type="text" name="email" placeholder="아이디를 입력해 주세요.">
            </div>
            <div>
                <label>PW : </label>
                <input type="password" name="password" placeholder="비밀번호를 입력해 주세요.">
            </div>
            <p></p>
            <input type="submit" value="LogIn" style="width: 300px;height: 30px;font-weight: bold; font-size: medium">
        </div>
    </fieldset>
</form>
	<!-- 푸터 위치 -->
	 <%@include file="/WEB-INF/tiles/footer.jsp" %>
	 <!-- 푸터 위치 -->
</body>
</html>
