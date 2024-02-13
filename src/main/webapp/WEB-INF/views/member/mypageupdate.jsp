<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보수정</title>
</head>
<body>
    <div class="header"></div>
    <div class="titleArea">
        <h2>회원정보 ID</h2>
        <ul>
            <li>마이페이지 입장?</li>
            <li>본인이 가입하신 이름과 이메일을 입력해 주세요.</li>
        </ul>
    </div>
    <div class="tabs">
    <button class="tab-button active" onclick="showTab('findId')">아이디 찾기</button>
    <button class="tab-button" onclick="window.location='/member/resetPw'">비밀번호 초기화</button>
</div>

    <form id="findIdForm" name="findIdForm"  method="post" target="_self" >

        <div class="findId">
            <fieldset>
              
                
                <p class="input-group" id="input_name">
                    <strong class="input_label">이름 </strong>
                    <input id="input_field_name" name="name" class="lostInput" placeholder="이름을 입력하세요" value="" type="text">
                </p>
                
                <p class="input-group" id="input_email">
                    <strong class="input_label">이메일 입력</strong>
                    <input id="input_field_email" name="email" class="lostInput" placeholder="이메일을 입력하세요" value="" type="text">
                </p>

                <!-- 결과를 표시할 부분을 추가 -->
                <span id="idResult" style="display:none;">
                    회원님의 아이디: <span id="userId"></span>
                </span>

                <div class="ec-base-button gColumn" id="button_group">
                    <button type="submit" id="submit_button" class="btnSubmit sizeM" onclick="findIdAction(); return false;">확인</button>
                  
                </div>
            </fieldset>
        </div>
    </form>

</body>
</html>