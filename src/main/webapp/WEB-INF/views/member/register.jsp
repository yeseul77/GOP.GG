<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
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
</head>

<body>


<!-- BACKGROUND IMAGE -->

  <div class="back"></div>

  <section class="sign">

    <div class="inner">

      <div class="signup-box">

        <div class="headline">
          <p>회 원 가 입</p>
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
                <span id="successEmail" class="success-message">사용가능한 아이디입니다.</span>
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

</body>
</html>
