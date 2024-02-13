<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>  
          <%@include file="/WEB-INF/tiles/header.jsp" %>  
    <script>
    $(document).ready(function() {
        $("#withdrawButton").click(function() {
            if (confirm("회원 탈퇴하시겠습니까?")) {
                $.ajax({
                    type: "POST",
                    url: "mypage",
                    success: function(response) {
                   
                        if (response.success) {
                            alert("회원 탈퇴되었습니다.");
                        } else {
                            alert("탈퇴에 실패하였습니다.");
                        }
                    }
                });
            }
        });
    });
</script>

</head>
<body>
    <h2>내정보 수정</h2>
    <form action="mypage" method="post">

	<div class="form-group">
    <label for="m_id">아이디 </label>
    <span>소환사아이디${MemberDto.m_id}</span>
	</div>
        <div class="pwd-success">
            <label for="m_pw"> 비밀번호 </label> <input type="password" name="m_pw" />
        </div>

        <div class="pwd-resuccess">
            <label for="m_pw2"> 비밀번호 재확인 </label> <input type="password" name="m_pw2" />
        </div>

        <button type="submit">저장하기</button>
    </form> 
    
     <button id="withdrawButton" type="button">회원 탈퇴</button>
     


 <%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
