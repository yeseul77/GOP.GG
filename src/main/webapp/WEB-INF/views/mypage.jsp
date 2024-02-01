<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>내정보 수정</title>
</head>
<body>
    <h2>내정보 수정</h2>
    <form action="mypage" method="post">

<!--         <div class="form-group"> -->
<!--     <label for="m_id">아이디 </label> -->
<%--     <input type="text" name="m_id" value="${MemberDto.m_id}" readonly /> --%>
<!-- </div> -->

	<div class="form-group">
    <label for="m_id">아이디 </label>
    <span>소환사아이디${MemberDto.m_id}</span>
	</div>


        <div class="pwd-success">
            <label for="m_pw"> 비밀번호 </label> <input type="password" name="m_pw" />
        </div>

        <div class="pwd-resuccess">
            <label for="m_pw2"> 비밀번호 재확인 </label> <input type="password" name="m_pw2" />
        </div>

        <button type="submit">저장하기</button>
    </form>
    
</body>
</html>
