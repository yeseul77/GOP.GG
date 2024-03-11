<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/tiles/header.jsp"%>
<title>마이 페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="/js/profile.js"></script>
</head>
<body>
	<div>
		<br />
		<h1 class="title">나의 정보</h1>
	</div>

	<c:if test="${not empty sessionScope.profile}">
		<img src="${sessionScope.profile}" alt="프로필 이미지">
	</c:if>

	<form id="profileForm"
		action="${pageContext.request.contextPath}/member/memberinfo/updateProfile"
		method="post" enctype="multipart/form-data">
		<img id="profilePreview"
			src="${empty sessionScope.profile ? '/upload/default.png' : sessionScope.profile}"
			style="max-width: 50%; height: auto;"> <br /> <input
			type="file" name="attachments" id="attachments"
			accept=".jpg, .jpeg, .png, .gif"> <input type="hidden"
			name="email" value="${sessionScope.email}">
		<button type="button" id="btnUpdateProfile" class="btn btn-primary">사진
			변경</button>
	</form>

	<form id="userInfoForm"
		action="${pageContext.request.contextPath}/member/memberinfo/updateMember"
		method="post">
		<table>
			<tr>
				<th scope="row"></th>
				<td>
					<div class="tdcell">
						<p class="contxt_username nickname">
						<div class="sign-form">
							<div class="form-group">
								<label for="email">이메일</label> <input type="text"
									value="${sessionScope.email}" class="form-control"
									style="width: 250px;" disabled>
							</div>
							<div class="form-group">
								<label for="username" id="usernameLabel">닉네임:</label> <input
									type="text" name="username" id="username"
									value="${sessionScope.username}" class="form-control">
								<span id="usernameError" class="text-danger"></span>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<br>
		<br>
		<button type="button" id="btnUpdateUserInfo"
			class="btn btn-primary submit-btn">회원정보수정</button>
		<input type="reset" class="btn btn-primary" value="취소">
	</form>

	<div style="text-align: center; margin-top: 20px;">
		<a href="${pageContext.request.contextPath}/member/mypage/withdraw"
			class="btn"
			style="text-decoration: none; color: white; background-color: #f44336; padding: 10px 20px; border-radius: 5px;">회원
			탈퇴</a>
	</div>

	<script>
		// 파일 선택 시 프로필 이미지 미리보기
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('#profilePreview').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("#attachments").change(function() {
			readURL(this);
		});

		$("#btnUpdateProfile").click(function() {
			var formData = new FormData($("#profileForm")[0]);

			$.ajax({
				url : $("#profileForm").attr("action"),
				type : "POST",
				data : formData,
				processData : false,
				contentType : false,
				success : function(data) {
					// 업로드 성공 시 처리
					alert("프로필 이미지가 업데이트되었습니다.");
				},
				error : function(xhr, status, error) {
					// 에러 처리
					console.error(xhr.responseText);
				}
			});
		});

		// 회원 정보 업데이트 Ajax 처리
		$("#btnUpdateUserInfo").click(function() {
			var formData = $("#userInfoForm").serialize();

			$.ajax({
				url : $("#userInfoForm").attr("action"),
				type : "POST",
				data : formData,
				success : function(data) {
					// 업데이트 성공 시 처리
					alert("회원 정보가 업데이트되었습니다.");
				},
				error : function(xhr, status, error) {
					// 에러 처리
					console.error(xhr.responseText);
				}
			});
		});
	</script>
</body>
</html>
