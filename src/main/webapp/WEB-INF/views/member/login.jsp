<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login-page</title>
</head>
<body>
 <%@include file="/WEB-INF/tiles/header.jsp" %>
	<form action="login" method="post" id="loginForm">
		<div id="login_wrap">
			<div id="login_box">
				<div class="login_con">
					<div class="login_tit">
						<h2>GOP.GG로그인</h2>
					</div>
					<div class="login_input">
						<label for="loginId">아이디</label> 
						<input type="text" id="m_id" name="m_id" placeholder="아이디"/> 
						<label for="password">비밀번호</label> 
						<input type="password" id="m_pw" name="m_pw" placeholder="비밀번호" />
						<button type="button" class="login_btn">로그인</button>
						<button type="button" class="register_btn">회원가입</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	 <%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
