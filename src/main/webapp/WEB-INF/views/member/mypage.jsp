<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!--헤더 위치 -->
    <title>회원 정보 수정</title>
</head>
<body>
<h2>회원 정보 수정</h2>
<form action="/mypage" method="post">
    <div>
        <label for="email">이메일(아이디):</label>
        <span>${memberDto.email}</span>
    </div>
    <div>
        <label for="username">사용자 이름:</label>
        <input type="text" id="username" name="username" value="${memberDto.username}" required>
    </div>
    <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">정보 수정</button>
    </div>
</form>
<!-- <h5>회원 탈퇴</h5> -->
<!-- <form action="/delete" method="post" onsubmit="return confirm('정말로 회원을 탈퇴하시겠습니까?');"> -->
<!--     <button type="submit">회원 탈퇴</button> -->
<!-- </form> -->

<!-- 푸터 위치 -->
 <%@include file="/WEB-INF/tiles/footer.jsp" %>
 <!-- 푸터 위치 -->
</body>
</html>
