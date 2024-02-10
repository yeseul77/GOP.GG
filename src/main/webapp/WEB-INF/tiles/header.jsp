<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav>
  <div>
    <div>
      <a href="${contextPath}/">GOP.GG</a>
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
              <li><a href="${contextPath}/mypage">회원정보수정</a></li>
              <li><a href="${contextPath}/memImageFormc">사진등록</a></li>
              <li><a href="${contextPath}/member/logout">로그아웃</a></li>    
               <!-- 시큐리티가 설정한 member/logout url설정 옮기면안됨  -->
              <li><img src="${contextPath}/resources/images/이미지넣을예정" style="width: 50px; height: 50px"/> ${sessionScope.email} 소환사님 환영합니다 .</li>
        </ul>
      </c:if>
    </div>
  </div>
</nav>
