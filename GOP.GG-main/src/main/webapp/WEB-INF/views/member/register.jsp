<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>GOP.GG 회원가입</title>
</head>
<body>
<%@include file="/WEB-INF/tiles/header.jsp" %>

<h2>GOP.GG 회원가입</h2>
<form action="/register" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table class="registertable" style="text-align:center; border: 1px">
<tr>
    <td><input type="text" name="email" id="email" placeholder="아이디"><br></td>
    <td><button type="button" onclick="registercheck()">중복확인</button></td>
</tr>
<tr>
    <td><input type="password" name="password" id="password" onkeyup="pwcheck()" placeholder="비밀번호"><br></td>
    <td><input type="password" name="password2" id="password2" onkeyup="pwcheck()" placeholder="비밀번호확인"><br></td>
</tr>
<tr>
    <td><input type="text" name="username" id="username" placeholder="닉네임"><br></td>
</tr>
</table>
<button type="submit">회원가입</button>
</form>

<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
