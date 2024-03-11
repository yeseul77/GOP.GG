f<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>본인확인 & 비밀번호 찾기</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">본인확인 & 비밀번호 찾기</h2>
        <ul>
            <li>가입하실 때 인증코드를 받았던 이메일을 입력해주세요,</li>
            <li>임시 비밀번호를 전송해 드립니다!</li>
        </ul>
        <form id="findpwForm">
            <div class="mb-3">
                <label for="email" class="form-label">등록한 이메일 주소:</label>
                <input type="email" class="form-control" id="email" name="email" required>
                <div id="emailError" class="form-text text-danger"></div>
            </div>
            <button type="submit" class="btn btn-primary">임시 비밀번호 발송</button>
        </form>
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath}/js/findpw.js"></script>
	<script type="text/javascript">
		function showFeedbackModal(title, message) {
			$('#feedbackModal .modal-title').text(title);
			$('#feedbackModal .modal-body').text(message);
			$('#feedbackModal').modal('show');
		}
	</script>
</body>
</html>
