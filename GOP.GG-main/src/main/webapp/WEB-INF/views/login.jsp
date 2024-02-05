<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
    <form action="login" method="post" id="loginForm" onsubmit="return false;" autocomplete="off">
        <div id="login_wrap">
            <div id="login_box">
                <div class="login_con">
                    <div class="login_tit">
                        <h2>GOP.GG로그인</h2>
                    </div>

                    <div class="login_input">
                       
                           <label for="loginId" class="skip_info">아이디</label> 
                           <input type="text" id="loginId" name="loginId" placeholder="아이디" title="아이디" />
                            <label for="password" class="skip_info">비밀번호</label>
                             <input type="password" id="password" name="password" title="비밀번호" placeholder="비밀번호" />               
                        <button type="button" onclick="login();" class="login_btn">로그인</button>
                        <button type="button" onclick="openSignupPopup();  class="signup_btn">회원가입</button>
                    </div>
                </div>
            </div>
      </div>
    </form>
</body>
</html>
