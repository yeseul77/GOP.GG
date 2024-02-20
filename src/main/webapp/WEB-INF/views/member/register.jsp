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
    <!-- JavaScript 코드에서 사용할 컨텍스트 경로 -->
    <script type="text/javascript">
        var contextPath = '<%= request.getContextPath() %>';
    </script>
   
</head>
<body>
<%@include file="/WEB-INF/tiles/header.jsp" %>

<h2 style="text-align:center;">GOP.GG 회원가입</h2>
<form action="/register" method="post">
    <table class="table" style="width:50%; margin:auto;">
        <tr>
            <td><input type="text" name="email" id="email" class="form-control" placeholder="아이디 (이메일)"></td>
            <td><button type="button" id="registerCheck" class="btn btn-primary">중복확인</button></td>
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
            <td colspan="2" style="text-align:center;"><button type="submit" class="btn btn-success" onclick="registerCheck()">회원가입</button></td>
        </tr>
    </table>
</form>
<p>
    이미 계정이 있으신가요? <a href="/login">로그인</a>
</p>

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

  <script>
  function registerCheck() {
	    let email = document.getElementById("email").value;
	    let password = document.getElementById("password").value;
	    let password2 = document.getElementById("password2").value;

	  
	    if (!/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
	        alert("유효한 이메일 주소를 입력해주세요.");
	        return false; // 폼 제출을 중단
	    }
	    
	    if (password.length < 4) {
	        alert("비밀번호는 최소 4자 이상이어야 합니다.");
	        return false; // 폼 제출을 중단
	    }

	    if (password !== password2) {
	        alert("입력한 비밀번호가 일치하지 않습니다.");
	        return false; 
	    }

	 
	    alert("회원가입이 완료되었습니다!");
	    document.querySelector('form').submit();
	    return true; 
	}

      if (m_pw !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
      }

      alert("회원가입이 완료되었습니다!");
      document.querySelector('form').submit();
    }
    
    function checkId() {
    let userId = document.getElementById("m_id").value;
    let idCheckMessageElement = document.getElementById("idCheckMessage");
    

    if (userId.length < 6) {
        idCheckMessageElement.innerText = "";
        idCheckMessageElement.style.color = "red";
        return;
    }

    fetch('/member/checkId?m_id=' + userId)
    .then(response => response.json())
    .then(data => {
        if (data.isDuplicated) {
            idCheckMessageElement.innerText = "이 아이디는 이미 사용 중입니다.";
            idCheckMessageElement.style.color = "red";
        } else {
            idCheckMessageElement.innerText = "사용 가능한 아이디입니다.";
            idCheckMessageElement.style.color = "green";
        }
    });
}
    }
    </script>

<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
