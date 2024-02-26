<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<<<<<<< HEAD
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
=======
<%@include file="/WEB-INF/tiles/header.jsp" %>
    <title>GOP.GG 회원가입</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <!-- Popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    
    <link rel="stylesheet" href="css/register.css">
    <script defer src="js/register.js"></script>
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
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

<<<<<<< HEAD
		<div class="form-group">
  <label for="user_email"><small>이메일</small></label>
    <input type="text" id="user_email" class = "align-left" required placeholder = "이메일 입력">
    <div id = "confrimEmail">
        <span id="middle">@</span>
        <select id="email_address" name="email_address" title="이메일 선택" class="email_address">
            <option value="naver.com">naver.com</option>
            <option value="gmail.com">gmail.com</option>
            <option value="daum.net">daum.net</option>
            <option value="direct">직접입력</option>
        </select>
        <input type="text" id="email_direct" name="email_direct" class="form-control" placeholder="이메일 직접 입력" style="display: none;">
        <div class="input-group-append">
            <button type="button" class="btn btn-primary" id="mail-Check-Btn">전송</button>
=======

<!-- BACKGROUND IMAGE -->

  <div class="back"></div>

  <section class="sign">

    <div class="inner">

      <div class="signup-box">

        <div class="headline">
          <p>회 원 가 입</p>
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
        </div>

        <form action="/register" method="post">

	      <div class="text-write">

           <div class="write-id">
             <div class="id-wrap">
             	<p>아이디</p>
                <input type="text" id="userId" name="email" class="signup-id" placeholder="이메일 입력">
                <button class="idBtn" onclick="registerCheck()">중복확인</button>
             </div>
             <div class="messageEmail">
                <span id="errorEmail" class="error-message"></span>
                <span id="successEmail" class="success-message"></span>
             </div>
                    
           </div>

           <div class="write-pw">
             <p>비밀번호</p>
             <input type="password" id="password" name="password" oninput="validdateUserPw()" class="signup-pw" maxlength="20" placeholder="영문자, 특수문자, 숫자 4자이상 입력가능">
             <div class="messagePw">
               <span id="errorUserPw" class="error-message"></span>
               <span id="successUserPw" class="success-message"></span>
             </div>
          </div>

          <div class="write-pw-match">
            <p>비밀번호 확인</p>
            <input type="password" id="password2" name="password2" oninput="validdatePwMatch()" class="signup-match" maxlength="20" placeholder="비밀번호 재입력">
            <div class="messageMatch">
              <span id="errorMatch" class="error-message"></span>
              <span id="successMatch" class="success-message"></span>
            </div>
           </div>

          <div class="write-nickname">
            <p>닉네임</p>
            <input type="text" id="userName" name="username" oninput="validdateUserNickname()" class="signup-nickname" maxlength="20" placeholder="한글, 영문, 숫자 4자이상 입력가능">
          	<div class="messageNickname">
               <span id="errorNickname" class="error-message"></span>
               <span id="successNickname" class="success-message"></span>
            </div>
          </div>
          
        </div>

        <div class="write-complete">        
           <input type="submit" class="btnSignUp" value="가입하기" disabled>           
        </div>

      </form>

        <div class="write-information">
          <ul>
            <li><a href="javascript:void(0)">이용약관</a></li>
            <li><a href="javascript:void(0)">개인정보처리방침</a></li>
            <li><a href="javascript:void(0)">청소년보호정책</a></li>
          </ul>
        </div>

      </div>

    </div>

  </section>

<<<<<<< HEAD
			
			<div class="form-group">
				<input type="text" id="code" name="code" class="form-control"
					placeholder="인증 코드 6자리를 입력해주세요.">
			</div>
			<div id="emailError" class="text-danger"></div>
		

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
=======
<script type="text/javascript">
function registerCheck() {
  var email = $("#email").val();
  if(email) {
    $.ajax({
      url: "${contextPath}/registerCheck",
      type: "post",
      data: {email: email},
      success: function(result) {
        if(result === 'OK') {
          $("#errorEmail").text(""); // 오류 메시지 초기화
          $("#successEmail").text("사용 가능한 이메일입니다."); // 성공 메시지 설정
        } else {
          $("#successEmail").text(""); // 성공 메시지 초기화
          $("#errorEmail").text("이미 사용 중인 이메일입니다."); // 오류 메시지 설정
        }
      },
      error: function() {
        $("#successEmail").text(""); // 성공 메시지 초기화
        $("#errorEmail").text("서버 오류입니다. 나중에 다시 시도해주세요."); // 오류 메시지 설정
      }
    });
  } else {
    $("#successEmail").text(""); // 성공 메시지 초기화
    $("#errorEmail").text("이메일을 입력해주세요."); // 오류 메시지 설정
  }
}
</script>

>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
</body>
</html>