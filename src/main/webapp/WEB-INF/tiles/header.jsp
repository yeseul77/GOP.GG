<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ko">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="icon" href="/images/favicon.ico">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="/css/header.css">
<script defer src="/js/header.js"></script>
</head>

<body>
	<div class="header">

		<div class="logo">
			<a href="${contextPath}/"><img src="/images/logoTest1.png"
				alt="logo"></a>
		</div>

		<div class="inner">

			<div class="header-menu">
				<ul class="menu">
					<li><a href="${contextPath}/">홈</a></li>
					<li><a href="${contextPath}/champion/list">챔피언 분석</a></li>
					<li><a href="${contextPath}/match">시너지분석</a></li>
					<li><a href="${contextPath}/aram/list">칼바람 나락</a></li>
					<li><a href="${contextPath}/chat/chatList">듀 오</a></li>
					<li><a href="${contextPath}/boardlist">커뮤니티</a></li>
				</ul>
			</div>

		</div>

		<c:if
			test="${sessionScope.loginState == null or !sessionScope.loginState}">

			<div class="side-menu">

				<div class="signup">
					<a href="${contextPath}/register">회원가입</a>
				</div>

				<div class="login">
					<a>로그인</a>
					<div class="loginBox">
						<div class="hiddenBox"></div>

						<form action="/login" method="post">

							<div class="loginfield">
								<div class="loginInput">
									<div class="loginInput-id">
										<input id="loginId" name="email" type="text" placeholder="아이디"
											autocomplete="off">
									</div>
									<div class="loginInput-pw">
										<input id="loginPw" type="password" name="password"
											placeholder="비밀번호">
									</div>
								</div>
								<div class="loginBtn">
									<input type="submit" value="로그인" />
								</div>
							</div>

						</form>

						<div class="loginError">
							<span></span>
						</div>
						<div class="findIdPw">
							<a href="/findpw">비밀번호를잊어버리셨나요?</a>
						</div>
					</div>
				</div>

			</div>

			<div class="shadow"></div>

		</c:if>

<c:if test="${sessionScope.loginState != null && sessionScope.loginState}">
    <div class="side-menu2">
        <!-- 마이페이지 프로필사진연동되야되는부분-->
        <div class="myPhoto">
            <img src="${profile}" alt="프로필 사진">
        </div>

				<div class="login">
					<div class="dropdown">
						<a class="btn btn-secondary dropdown-toggle" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
							${sessionScope.username}소환사님 환영합니다! </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="${contextPath}/member/memberinfo">마이 페이지</a></li>
						</ul>
					</div>
				</div>

				<div class="logout">
					<a href="${contextPath}/logout">로그아웃&nbsp;<span
						class="material-symbols-outlined">logout</span></a>
				</div>

			</div>

		</c:if>

	</div>

</body>
</html> 