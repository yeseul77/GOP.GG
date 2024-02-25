<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>GOP.GG 회원가입</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</head>
<body>
	<h2 style="text-align: center;">GOP.GG 회원가입</h2>
	<form action="/register" method="post"
		onsubmit="return registerCheck();" class="container" style="max-width: 500px;">
		<div class="form-group">
			<label for="username">닉네임:</label>
			 <input type="text" name="username" id="username" class="form-control" placeholder="닉네임" autofocus>
				<label id="label1"></label>
			<div id="usernameError" class="text-danger"></div>
		</div>

<!-- 이메일 입력필드 ㅠㅠㅠ개같앙ㅁ나암ㄴ암ㄴ랗  -->
		 <!-- 이메일 입력 필드 -->
        <div class="form-group">
            <label>이메일:</label>
            <div class="input-group">
                <input type="text" id="email" name="email" class="form-control" required placeholder="이메일 입력">
            </div>
            <!-- 이메일 인증 버튼 -->
            <div class="input-group-append" style="margin-top: 10px;">
                <button type="button" class="btn btn-primary" id="mail-Check-Btn">인증번호 전송</button>
            </div>
        </div>
        
        <!-- 인증번호 입력 필드 -->
        <div class="form-group">
            <input type="text" id="code" name="code" class="form-control" placeholder="인증 코드 6자리를 입력해주세요.">
        </div>

		<div class="form-group">
			<label for="password">비밀번호:</label> <input type="password"
				name="password" id="password" class="form-control"
				placeholder="비밀번호">
			<div id="passwordError" class="text-danger"></div>
		</div>

		<div class="form-group">
			<label for="password2">비밀번호 확인:</label> <input type="password"
				name="password2" id="password2" class="form-control"
				placeholder="비밀번호 확인">
			<div id="password2Error" class="text-danger"></div>
		</div>

		<div class="form-group" style="text-align: center;">
			<button type="submit" class="btn btn-success">회원가입</button>
		</div>
	</form>
</body>
</html>