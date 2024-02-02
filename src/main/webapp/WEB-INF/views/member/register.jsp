<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head

meta charset="UTF-8">


<title>Insert title here</title>

</head>
<body>
 <%@include file="/WEB-INF/tiles/header.jsp" %>

<h2> GOP.GG 회원가입 </h2>
<form action="/register" method="post">
<input type="text" name="m_id" id="m_id"placeholder="아이디"><br>
<input type="password" name="m_pw"id="m_pw"placeholder="비밀번호"><br>
<input type="text" name="m_name" id="m_name" placeholder="닉네임"><br>
<button type="submit">회원가입</button>
</form>
 <%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>