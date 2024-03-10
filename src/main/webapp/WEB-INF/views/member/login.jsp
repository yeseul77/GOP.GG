<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<title>로그인 GOP.GG</title>
<link rel="icon" href="/images/favicon.ico">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" href="/css/login.css">
</head>

<body>
<div class="back">

<div class="headerLine">
  <div class="inner">
    <div class="logo">
     <a href="${contextPath}/"><img src="/images/logoTest2.png" alt="logo"></a>
    </div>
    <span>MEMBERS</span>
    <a href="${contextPath}/register"><span>회원가입</span></a>
  </div>
</div>

<div id="wrap">

 <div class="inner">
 
    <span class="loginTitle">로 그 인</span>
        
    <div class="loginBox">
    
       <form action="/login" method="post">
                              
        <input type="text" class="inputId" name="email" placeholder="아이디를 입력해 주세요." autocomplete="off">
        <input type="password" class="inputPw" name="password" placeholder="비밀번호를 입력해 주세요." autocomplete="off">
        <input type="submit" class="inputBtn" value="확 인">
       
      </form>
    
      <div class="loginBtn">
         <a href="/findpw">비밀번호를 잊으셨나요?</a>
      </div> 
     
      <div class="question">
        <span>GOP.GG는 처음이신가요?</span>
        <a href="/register">가입하기</a>
      </div>
    
    </div>
    
 </div>

 </div>

</div>
    
</body>
</html>