<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
    <title>GOP.GG</title>
    <style>
        /* 전체 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
<<<<<<< HEAD
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
>>>>>>> 239a3c587345cfbe9e035541bb076e985d2f9dd7
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
>>>>>>> main
=======
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
>>>>>>> SA

/* <c:set var="contextPath" value="${pageContext.request.contextPath}"/> */

        .topnav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* 로그인/로그아웃 정보 스타일 */
        .user-info {
            float: right;
            color: white;
            padding: 14px 16px;
        }
    </style>
</head>
<body>
<nav>
    <div id="myNavbar">
      <ul>
        <li class="active"><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/boardMain">자유게시판</a></li>            
      </ul>
      <c:if test="${sessionScope.Loginstate == null or !sessionScope.Loginstate}">
        <ul>
              <li><a href="${contextPath}/login">로그인</a></li>
              <li><a href="${contextPath}/register">회원가입</a></li>            
        </ul>
      </c:if>
      <c:if test="${sessionScope.Loginstate != null && sessionScope.Loginstate}">
        <ul>
              <li><a href="${contextPath}/member/mypage">회원정보수정</a></li>
              <li><a href="${contextPath}/member/imageform">사진등록</a></li>
              <li><a href="${contextPath}/member/logout">로그아웃</a></li>    
               <!-- 시큐리티가 설정한 member/logout url설정 옮기면안됨  -->
              <li><img src="${contextPath}/resources/images/이미지넣을예정" style="width: 50px; height: 50px"/> ${sessionScope.username} 소환사님 환영합니다 .</li>
        </ul>
      </c:if>
    </div>
    
    <div id="myNavbar">
      <ul>
        <li class="active"><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/boardMain">자유게시판</a></li>            
      </ul>
      <c:if test="${sessionScope.Loginstate == null or !sessionScope.Loginstate}">
        <ul>
              <li><a href="${contextPath}/login">로그인</a></li>
              <li><a href="${contextPath}/register">회원가입</a></li>            
        </ul>
      </c:if>
      <c:if test="${sessionScope.Loginstate != null && sessionScope.Loginstate}">
        <ul>
<<<<<<< HEAD
              <li><a href="${contextPath}/member/mypage">마이페이지</a></li>
              <li><a href="${contextPath}/member/imageform">사진등록</a></li>
=======
              <li><a href="${contextPath}/member/mypage">회원정보수정</a></li>
              <li><a href="${contextPath}/member/Imageform">사진등록</a></li>
>>>>>>> SA
              <li><a href="${contextPath}/member/logout">로그아웃</a></li>    
               <!-- 시큐리티가 설정한 member/logout url설정 옮기면안됨  -->
              <li><img src="${contextPath}/resources/images/이미지넣을예정" style="width: 50px; height: 50px"/> ${sessionScope.username} 소환사님 환영합니다 .</li>
        </ul>
      </c:if>
    </div>
</nav>
</body>
</html>
