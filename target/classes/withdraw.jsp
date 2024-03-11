<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 탈퇴</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#withdrawButton").click(function() {
                confirmWithdraw();
            });
        });

        function confirmWithdraw() {
            var userId = $("#input_field_id").val();
            var password = $("#input_field_password").val();
            var reason = $("#withdrawReason").val();

            if (!userId || !password) {
                alert('아이디와 비밀번호를 확인하세요.');
                return;
            }

            if (!$('#confirmCheck').prop('checked')) {
                alert('탈퇴 전 모든 정보 확인에 동의해야 합니다.');
                return;
            }

            var confirmMsg = "정말로 탈퇴하시겠습니까?";
            if (reason && reason !== "") {
                confirmMsg += "\n탈퇴 사유: " + reason;
            }

            if (window.confirm(confirmMsg)) {
                $("#withdrawAccountForm").submit();
            }
        }
    </script>
</head>
<body>
    <header>
        <%@include file="/WEB-INF/tiles/header.jsp"%>
    </header>

    <main>
        <div class="titleArea">
            <ul>
                <li>탈퇴 전 모든 정보를 확인해주세요.</li>
                <li>탈퇴 후 복구는 불가능합니다.</li>
            </ul>
        </div>

        <form id="withdrawAccountForm" name="withdrawAccountForm" action="/memberinfo/withdraw" method="post" target="_self">
            <div>
                <p class="input-group">
                    <label for="input_field_id" class="input_label">아이디</label>
                    <input id="input_field_id" name="email" class="inputField" type="text" placeholder="아이디를 입력하세요" autocomplete="off">
                </p>
                <p class="input-group">
                    <label for="input_field_password" class="input_label">비밀번호</label>
                    <input id="input_field_password" name="password" class="inputField" type="password" placeholder="비밀번호를 입력하세요" autocomplete="off">
                </p>

                <div class="reasonArea">
                    <label for="withdrawReason"><strong>탈퇴 사유 선택 (선택 사항)</strong></label>
                    <select id="withdrawReason" name="withdrawReason">
                        <option value="">-- 선택하세요 --</option>
                        <option value="just">그냥</option>
                        <option value="other">기타</option>
                    </select>
                </div>

                <div class="confirmCheck">
                    <input type="checkbox" id="confirmCheck" name="confirmCheck">
                    <label for="confirmCheck"> 모두 확인하였으며, 이에 동의합니다.</label>
                </div>

                <div class="buttonGroup">
                    <button type="button" class="btnSubmit" id="withdrawButton">탈퇴하기</button>
                </div>
            </div>
        </form>
    </main>

    <footer>
        <%@include file="/WEB-INF/tiles/footer.jsp"%>
    </footer>
</body>
</html>
