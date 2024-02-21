<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!--헤더 위치 -->
<head>
<title>로그인page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<h3 style="text-align:center;">소환사님 
<span style="font-size:30px;">&#128378;</span> 
    <span style="color: blue">
    <c:out value="${message}"/></span></h3>


<form action="login" method="post">
    <fieldset style="text-align:center; width: 500px; margin: auto ">
        <legend style="font-size: large; " >[로그인]</legend>

        <div id="LoginForm" style="font-size:medium; font-weight: bold; text-align: center;">
            <div style="height: 40px">
                <label>ID &nbsp; : </label>
                <input type="text" name="email" placeholder="아이디를 입력해 주세요.">
            </div>
            <div>
                <label>PW : </label>
                <input type="password" name="password" placeholder="비밀번호를 입력해 주세요.">
            </div>
            <p></p>
            <input type="submit" value="Login" style="width: 300px;height: 30px;font-weight: bold; font-size: medium">
        </div>
    </fieldset>
    <!-- 실패메세지 -->
    <!-- 다이얼로그창(모달) -->
<!-- Modal  부트스트랩 모달임!  myModal-->
<!-- 	<div id="myModal" class="modal fade" role="dialog"> -->
<!-- 	  <div class="modal-dialog"> -->
<!-- 	    Modal content -->
<!-- 	    <div id="checkType" class="modal-content"> -->
<!-- 	      <div class="modal-header"> -->
<!-- 	        <button type="button" class="close" data-dismiss="modal">&times;</button> -->
<!-- 	        <h4 class="modal-title">메세지 확인</h4> -->
<!-- 	      </div> -->
<!-- 	      <div class="modal-body"> -->
<!-- 	        <p id="checkMessage"></p> -->
<!-- 	<!--         p태그에 id값줘서  동작하게하려고함--> -->
<!-- 	      </div> -->
<!-- 	      <div class="modal-footer"> -->
<!-- 	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
<!-- 	      </div> -->
<!-- 	    </div> -->
<!-- 	  </div> -->
<!-- 	</div> -->
</form>
	<!-- 푸터 위치 -->
	 <%@include file="/WEB-INF/tiles/footer.jsp" %>
	 <!-- 푸터 위치 -->
</body>
</html>
