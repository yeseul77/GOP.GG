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
<title>프로필수정페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	if(${!empty msgType}){
     		$("#messageType").attr("class", "modal-content panel-warning");    
    		$("#myMessage").modal("show");
    	}
    });
 
    function passwordCheck(){
    	var memPassword1=$("#password1").val();
    	var memPassword2=$("#password2").val();
    	if(password1 != password2){
    		$("#passMessage").html("비밀번호가 서로 일치하지 않습니다.");
    	}else{
    		$("#passMessage").html("");
    		$("#password").val(memPassword1);
    	}   	
    }
    function goUpdate(){
    	
    	document.frm.submit(); // 전송
    }
  </script>
</head>
<body>
	<div class="container">

		<h2>회원정보수정</h2>
		<div class="panel panel-default">
			<div class="panel-heading">회원정보수정 폼</div>
			<div class="panel-body">
				<form name="frm" action="${contextPath}/mypage" method="post">
					<input type="hidden" id="email" name="email"
						value="${memberDto.email}" /> 
						
						<input type="hidden" id="password"
						name="password" value="" />
					<table class="table table-bordered"
						style="text-align: center; border: 1px solid #dddddd;">
						<tr>
							<td style="width: 110px; vertical-align: middle;">아이디</td>
							<td>${sessionScope.email}</td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">닉네임</td>
							<td colspan="2"><input id="username" name="username"
								class="form-control" type="text" maxlength="20"
								placeholder="변경할 닉네임을입력해주세요." value="${memberDto.username}" /></td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">비밀번호</td>
							<td colspan="2"><input id="password1" name="password1"
								onkeyup="passwordCheck()" class="form-control" type="password"
								maxlength="20" placeholder="비밀번호를 입력하세요." /></td>
						</tr>
						<tr>
							<td style="width: 110px; vertical-align: middle;">비밀번호확인</td>
							<td colspan="2"><input id="password2" name="password2"
								onkeyup="passwordCheck()" class="form-control" type="password"
								maxlength="20" placeholder="비밀번호를 확인하세요." /></td>
						</tr>
						<tr>
						</tr>
						<tr>
					</table>
						<span id="passMessage" style="color: red"></span>
							<input type="button" class="btn btn-primary btn-sm pull-right"
								value="취소" onclick="backhistory()" />	
						<span id="passMessage" style="color: red"></span>
							<input type="button" class="btn btn-primary btn-sm pull-right"
								value="수정" onclick="goUpdate()" />	
									
				</form>
			</div>

			<!-- 실패 메세지를 출력(modal) -->
			<div id="myMessage" class="modal fade" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div id="messageType" class="modal-content panel-info">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">${msgType}</h4>
						</div>
						<div class="modal-body">
							<p>${message}</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 푸터 위치 -->
 <%@include file="/WEB-INF/tiles/footer.jsp" %>
 <!-- 푸터 위치 -->
</body>
</html>