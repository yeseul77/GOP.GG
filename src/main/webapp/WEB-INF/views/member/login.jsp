<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<form action="login" method="post" id="loginForm"
		onsubmit="return false;" autocomplete="off">
		<div id="login_wrap">
			<div id="login_box">
				<div class="login_con">
					<div class="login_tit">
						<h2>GOP.GG로그인</h2>
					</div>

					<div class="login_input">
						<label for="loginId" class="skip_info">아이디</label> <input
							type="text" id="m_id" name="m_id" placeholder="아이디" title="아이디" />
						<label for="password" class="skip_info">비밀번호</label> <input
							type="password" id="m_pw" name="m_pw" title="비밀번호"
							placeholder="비밀번호" />
						<button type="submit" class="login_btn">로그인</button>
						<button type="submit" class="register_btn">회원가입</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script>
	<script>
	$(function() {
		$('#logBtn').on("click", function() {
			let id = $('#m_id').val();
			if (id.trim() == "") {
				$('#m_id').focus();
				return;
			}
			let pwd = $('#m_pw').val();
			if (pwd.trim() == "") {
				$('#password').focus();
				return;
			}
			// 서버로 전송
			$.ajax({
				type : 'post',
				url : 'login',
				data : {
					"id" : id,
					"pwd" : pwd
				},
				success : function(result) {
					let res = result.trim();
					if (res === 'NOID') {
						alert("ID가 존재하지 않습니다.");
						$('#u_id').val("");
						$('#u_pw').val("");
						$('#u_id').focus();
					} else if (res === 'NOPWD') {
						alert("비밀번호가 틀렸습니다.");
						$('#u_pw').val("");
						$('#u_pw').focus();
					} else {
						parent.location.href = "index";
					}
				},
				error : function(xhr, status, error) {
					console.error(xhr.responseText);
					alert("로그인 요청 중 오류가 발생했습니다.");
				}
			});
		});
	});
</script>

	</script>
	</body>
</html>
