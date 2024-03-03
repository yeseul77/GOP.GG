<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
</head>
<body>
    <h3 style="text-align:center;">소환사님 
        <span style="font-size:30px;">&#128378;</span> 
        <span style="color: blue">
            <c:out value="${message}"/>
        </span>
    </h3>
    <form action="/login" method="post" style="text-align:center; width: 500px; margin: auto">
        <fieldset>
            <legend style="font-size: large;">[로그인]</legend>
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
        </fieldset>
    </form>
    <div style="text-align:center;">
        <div>
            <a href="/findinfo">아이디, 비밀번호를 잊으셨나요?</a>
        </div> 
        <div>
            <span>GOP.GG는 처음이신가요?</span>
            <a href="/register">가입하기</a>
        </div>
    </div>
</body>
</html>