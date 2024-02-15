<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>GOP.GG 회원가입</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <!-- Popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<<<<<<< HEAD
=======

>>>>>>> 239a3c5 (0213오전까지한거커밋)
<%@include file="/WEB-INF/tiles/header.jsp" %>

<h2 style="text-align:center;">GOP.GG 회원가입</h2>
<form action="/register" method="post">
<<<<<<< HEAD
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table class="registertable" style="text-align:center; border: 1px">
<tr>
    <td><input type="text" name="email" id="email" placeholder="아이디"><br></td>
    <td><button type="button"> <!--  onclick="registercheck()"-->중복확인</button></td>
</tr>
<tr>
    <td><input type="password" name="password" id="password" placeholder="비밀번호"><!-- onkeyup="pwcheck()" --> <br></td>
    <td><input type="password" name="password2" id="password2"  placeholder="비밀번호확인"><!-- onkeyup="pwcheck()" --><br></td>
</tr>
<tr>
    <td><input type="text" name="username" id="username" placeholder="닉네임"><br></td>
</tr>
</table>
<button type="submit">회원가입</button>
</form>

=======
    <table class="table" style="width:50%; margin:auto;">
        <tr>
            <td><input type="text" name="email" id="email" class="form-control" placeholder="아이디 (이메일)"></td>
            <td><button type="button" class="btn btn-primary" onclick="registerCheck()">중복확인</button></td>
        </tr>
        <tr>
            <td colspan="2"><input type="password" name="password" id="password" class="form-control" placeholder="비밀번호"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="password" name="password2" id="password2" class="form-control" placeholder="비밀번호 확인"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="text" name="username" id="username" class="form-control" placeholder="닉네임"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center;"><button type="submit" class="btn btn-success">회원가입</button></td>
        </tr>
    </table>
</form>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">메세지 확인</h4>
            </div>
            <div class="modal-body">
                <p id="checkMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

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
                    alert("사용 가능한 이메일입니다.");
                } else {
                    alert("이미 사용 중인 이메일입니다.");
                }
            },
            error: function() {
                alert("서버 오류입니다. 나중에 다시 시도해주세요.");
            }
        });
    } else {
        alert("이메일을 입력해주세요.");
    }
}

$(document).ready(function(){
    // 여기서 메시지 관련 스크립트를 처리합니다.
});
</script>

>>>>>>> 239a3c5 (0213오전까지한거커밋)
<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
