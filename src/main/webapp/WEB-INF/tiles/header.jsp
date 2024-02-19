<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav>
  <div>
    <div>
      <a href="${contextPath}/">GOP.GG</a>
    </div>
    
    <div id="myNavbar">
      <ul>
        <li class="active"><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/boardlist">자유게시판</a></li>            
      </ul>
      <c:if test="${sessionScope.Loginstate == null or !sessionScope.Loginstate}">
        <ul>
              <li><a href="${contextPath}/login">로그인</a></li>
              <li><a href="${contextPath}/register">회원가입</a></li>            
        </ul>
      </c:if>
      <c:if test="${sessionScope.Loginstate != null && sessionScope.Loginstate}">
        <ul>
        	  <li><a href="${contextPath}/chat/chatList">듀오매칭</a>
              <li><a href="${contextPath}/member/memberinfo">마이페이지</a></li>
              <li><a href="${contextPath}/member/imageform">사진등록</a></li>
              <li><a href="${contextPath}/member/logout">로그아웃</a></li>    
               <!-- 시큐리티가 설정한 member/logout url설정 옮기면안됨  -->
              <li><img src="/resources/images/profile" style="width: 50px; height: 50px"/> ${sessionScope.username} 소환사님 환영합니다 .</li>
        </ul>
      </c:if>
    </div>
  </div>
</nav>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<nav>
  <div>
    <div>
      <a href="${contextPath}/">GOP.GG</a>
    </div>
    
    <div id="myNavbar">
      <ul>
        <li class="active"><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/boardlist">자유게시판</a></li>            
      </ul>
      <c:if test="${sessionScope.Loginstate == null or !sessionScope.Loginstate}">
        <ul>
              <li><a href="${contextPath}/login">로그인</a></li>
              <li><a href="${contextPath}/register">회원가입</a></li>            
        </ul>
      </c:if>
      <c:if test="${sessionScope.Loginstate != null && sessionScope.Loginstate}">
        <ul>
              <li><a href="${contextPath}/member/memberinfo">마이페이지</a></li>
              <li><a href="${contextPath}/member/logout">로그아웃</a></li>    
               <!-- 시큐리티가 설정한 member/logout url설정 옮기면안됨  -->
              <li><img src="/uploads/defaultprofile.png" style="width: 50px; height: 50px"/> ${sessionScope.username} 소환사님 환영합니다 .</li>
        </ul>
      </c:if>
    </div>
  </div>
</nav>
>>>>>>> YS
