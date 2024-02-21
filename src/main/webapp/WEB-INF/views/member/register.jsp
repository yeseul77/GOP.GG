<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>GOP.GG 회원가입</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function checkEmailDuplication() {
    var email = $('#email').val().trim(); // jQuery로 이메일 값

    if(email.length === 0) {
        alert("이메일을 입력해주세요.");
        return;
    }

    $.ajax({
        url: contextPath + "/checkEmail", // 서버 측 이메일 중복 확인 처리 URL
        type: "POST",
        data: { email: email },
        dataType: "json", 
        success: function(response) {
            if(response.isDuplicated) {
                $('#emailError').text("이미 사용 중인 이메일입니다.");
            } else {
                $('#emailError').text("사용 가능한 이메일입니다.");
            }
        },
        error: function(xhr, status, error) {
            alert("에러가 발생했습니다.");
        }
    });
}
    }
    
    function registerCheck() {
        var email = document.getElementById('email').value.trim();
        var password = document.getElementById('password').value.trim();
        var password2 = document.getElementById('password2').value.trim();
        var username = document.getElementById('username').value.trim();

        var emailError = document.getElementById('emailError');
        var passwordError = document.getElementById('passwordError');
        var password2Error = document.getElementById('password2Error');
        var usernameError = document.getElementById('usernameError');

        emailError.innerHTML = '';
        passwordError.innerHTML = '';
        password2Error.innerHTML = '';
        usernameError.innerHTML = '';

        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            emailError.innerHTML = '유효한 이메일 주소를 입력해주세요.';
            return false;
        }

        if (password.length < 4) {
            passwordError.innerHTML = '비밀번호는 최소 4자 이상이어야 합니다.';
            return false;
        }

        if (password !== password2) {
            password2Error.innerHTML = '비밀번호가 일치하지 않습니다.';
            return false;
        }

        if (username === '') {
            usernameError.innerHTML = '닉네임을 입력하세요.';
            return false;
        }

        return true;
    }
</script>
</head>
<body>
    <%@include file="/WEB-INF/tiles/header.jsp"%>

    <h2 style="text-align: center;">GOP.GG 회원가입</h2>
    <form action="/register" method="post" onsubmit="return registerCheck();">
        <table class="table" style="width: 50%; margin: auto;">
            <tr>
                <td><input type="text" name="email" id="email" class="form-control" placeholder="이메일(gop@gg.com)">
                    <div id="emailError" class="text-danger"></div>
                </td>
                <td><button type="button" class="btn btn-primary" onclick="checkEmailDuplication();">중복확인</button></td>
            </tr>
            <tr>
                <td colspan="2"><input type="password" name="password" id="password" class="form-control" placeholder="비밀번호">
                    <div id="passwordError" class="text-danger"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="password" name="password2" id="password2" class="form-control" placeholder="비밀번호 확인">
                    <div id="password2Error" class="text-danger"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="text" name="username" id="username" class="form-control" placeholder="닉네임">
                    <div id="usernameError" class="text-danger"></div>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;"><button type="submit" class="btn btn-success">회원가입</button></td>
            </tr>
        </table>
    </form>

    <p>이미 계정이 있으신가요? <a href="/login">로그인</a></p>
    <%@include file="/WEB-INF/tiles/footer.jsp"%>
</body>
</html>
