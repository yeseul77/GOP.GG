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
<table class="registertable" style="text-align:center; border: 1px">
<tr>
    <td><input type="text" name="m_id" id="m_id" placeholder="아이디"><br></td>
    <td><button type="button" onclick="registercheck()">중복확인</button></td>
</tr>
<tr>
    <td><input type="password" name="m_pw" id="m_pw" onkeyup="pwcheck()" placeholder="비밀번호"><br></td>
    <td><input type="password" name="m_pw2" id="m_pw2" onkeyup="pwcheck()" placeholder="비밀번호확인"><br></td>
</tr>
<tr>
    <td><input type="text" name="m_name" id="m_name" placeholder="닉네임"><br></td>
</tr>
<tr>
    <td><input type="text" name="email" id="email" placeholder="이메일을입력해주세요"><br></td>
</tr>
</table>
<button type="submit">회원가입</button>
</form>

<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
