<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 정보 수정</title>
</head>
<body>
<h2>회원 정보 수정</h2>
<form action="${pageContext.request.contextPath}/member/update" method="post">
    <div>
        <label for="email">이메일(아이디):</label>
        <span>${member.email}</span> 
    </div>
    <div>
        <label for="username">사용자 이름:</label>
        <input type="text" id="username" name="username" value="${member.username}" required>
    </div>
    <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <button type="submit">정보 수정</button>
    </div>
</form>

<h2>회원 탈퇴</h2>
<form action="${pageContext.request.contextPath}/member/delete" method="post" onsubmit="return confirm('정말로 회원을 탈퇴하시겠습니까?');">
    <button type="submit">회원 탈퇴</button>
</form>
</body>
</html>
