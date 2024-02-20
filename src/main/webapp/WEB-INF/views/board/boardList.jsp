<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!--헤더 위치 -->
 <link rel="stylesheet" href="/css/boardlist.css">
</head>

<body>

<div id=wrap>

  <div class="container">
  <h2>게시판 목록</h2>
    <table class="board_list">
      <colgroup>
        <col width="15%"/>
        <col width="*"/>
        <col width="15%"/>
        <col width="20%"/>
      </colgroup>
      <thead>
        <tr>
          <th scope="col">글번호</th>
          <th scope="col">제목</th>
          <th scope="col">조회수</th>
          <th scope="col">작성일</th>
        </tr>
      </thead>
      <tbody>
        <c:if test="${not empty list}">
          <c:forEach var="listItem" items="${list}">
            <tr>
              <td>${listItem.b_bno}</td>
              <td class="title">${listItem.b_title}</td>
              <td>${listItem.b_views}</td>
              <td>${listItem.b_date}</td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty list}">
          <tr>
            <td colspan="4">조회된 결과가 없습니다.</td>
          </tr>
        </c:if>
      </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/boardWrite" class="btn btn-primary">글쓰기</a>
  </div>	

</div>

  
  	<!-- 푸터 위치 -->
	 <%@include file="/WEB-INF/tiles/footer.jsp" %>
	 <!-- 푸터 위치 -->
	 
</body>

</html>
