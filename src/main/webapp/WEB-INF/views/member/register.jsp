<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입 GOP.GG</title>
<link rel="icon" href="/images/favicon.ico">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="/css/register.css">
<script defer src="/js/register.js"></script>
</head>
<body>
<div class="back">

<div class="headerLine">
  <div class="inner">
    <div class="logo">
     <a href="${contextPath}/"><img src="/images/logoTest2.png" alt="logo"></a>
    </div>
    <span>MEMBERS</span>
    <a href="${contextPath}/login"><span>로그인</span></a>
  </div>
</div>

<div id="wrap">

 <div class="inner">
 
    <span class="loginTitle">회 원 가 입</span>
    
	<form action="/register" method="post" onsubmit="return registerCheck();" class="container">
	
		<div class="form-group">					
			<input type="text" name="username" id="username" class="formInput" placeholder="닉네임" autocomplete="off">			
			<div id="usernameError" class="text-danger"></div>
		</div>

		<div class="form-group1">			
			<div class="input-group">
				<input type="hidden" id="sendemail" name="email" value="email">
				<input type="text" id="email" name="mail" class="formInput" required placeholder="이메일 입력" autocomplete="off">
				<div class="input-group-append">
					<span id="middle" value="@" class="input-group-text">@</span>
				</div>
				<select id="email_address" class="formList" onchange="emailDomainChange()">
					<option value="naver.com">naver.com</option>
					<option value="gmail.com">gmail.com</option>
					<option value="daum.net">daum.net</option>
					<option value="direct">직접입력</option>
				</select> 
				<input type="text" id="email_direct" class="form-control" placeholder="이메일 직접 입력" style="display: none;">

				<div class="inputBtn">
					<button type="button" class="btn btn-primary" id="sendBtn" name="sendBtn" onclick="sendNumber()">인증 번호</button>
				</div>
			</div>

			<div class="form-group2">
				<div class="input-group">
					<input type="text" id="code" name="code" class="form-control" placeholder="인증 코드 6자리를 입력해주세요." autocomplete="off">
					<div class="inputBtn">
						<button type="button" class="btn btn-primary" name="confirmBtn" id="confirmBtn" onclick="confirmCode()">이메일 인증</button>
					</div>
				</div>
			</div>
			<input type="text" id="Confirm" name="Confirm" style="display: none" value="">
			<div id="emailError" class="text-danger"></div>
		</div>

		<div class="form-group3">
			 
			<input type="password" name="password" id="password" class="formInput" placeholder="비밀번호">
			<div id="passwordError" class="text-danger"></div>
		
		</div>

		<div class="form-group4">
			<input type="password" name="password2" id="password2" class="formInput" placeholder="비밀번호 확인">
			<div id="password2Error" class="text-danger"></div>
		</div>

		<div class="form-group5" style="text-align: center;">
			<button type="submit" class="btn btn-primary">가입하기</button>
		</div>

	</form>
 
 </div>

</div>
</div>
	
	<!-- 모달-->
	<div class="modal fade" id="feedbackModal" tabindex="-1"
		aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabel">회원가입 피드백</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function showFeedbackModal(title, message) {
			$('#feedbackModal .modal-title').text(title);
			$('#feedbackModal .modal-body').text(message);
			$('#feedbackModal').modal('show');
		}
	</script>
	
</body>
</html>
