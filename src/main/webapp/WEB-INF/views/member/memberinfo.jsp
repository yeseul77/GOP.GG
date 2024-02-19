<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/tiles/header.jsp"%> 
    <title>프로필 설정 페이지</title>
    <script src="/js/profile.js"></script>
</head>
<body>
  <form action="${contextPath}/memberinfo" method="post" enctype="multipart/form-data">
        <div style="width: 300px; margin: 0 auto; text-align: center;">
            <h2>프로필 설정</h2>
             <label>프로필사진</label>
             <!-- 최초가입이나,이미지초기화시에는 default profile 이미지로 고정 -->
           <img src="${empty sessionScope.profileImagePath ?'/uploads/defaultprofile.png' : sessionScope.profileImagePath}" 
           alt="default profile" style="width: 100px; height: 100px;">
            <input type="file" id="inputImage" class="btn2" name="profileImage">
            <input id="btnDelete" class="btn2_disable"  type="button" value="삭제" />
            <table> 
                <tr> 
                    <th scope="row"></th>
                    <td>
                        <div class="tdcell">
                            <p class="contxt_username nickname">
                          <p class="contxt_username nickname">
                            <label>닉네임</label>
                                <!-- 로그인한 세션의 username 값으로 초기화 -->
                                <input type="text" name="username" id="username" value="${sessionScope.username}" >
									<!--   한 글자 이상 입력된 상태에서만 적용버튼이 활성화되도록 -->
                            </p>
                        </div>
                    </td>
                </tr>
            </table>
            <br><br>
          <input type="submit" value="적용"> 
            <input type="reset" value="취소">
        </div>
    </form>
    <div style="text-align: center; margin-top: 20px;">
    <a href="${contextPath}/member/mypage/withdraw" class="btn" style="text-decoration: none; color: white; background-color: #f44336; padding: 10px 20px; border-radius: 5px;">회원 탈퇴</a>
</div>
    <%@include file="/WEB-INF/tiles/footer.jsp"%> 

 
</body>
</html>
