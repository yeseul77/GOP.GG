<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/tiles/header.jsp"%> 
    <title>프로필 설정 페이지</title>
</head>
<body>
    <form action="${contextPath}/memberinfo" method="post">
        <div style="width: 300px; margin: 0 auto; text-align: center;">
            <h2>프로필 설정</h2>
             <label>프로필사진</label>
             <!-- 최초가입이나,이미지초기화시에는 default profile 이미지로 고정 -->
            <img src="위치!" alt="default profile" style="width: 100px; height: 100px;"><br>
            <input type="file" id="inputImage" class="btn2" name="profileImage">
            <input id="btnDelete" class="btn2_disable" onclick="clickcr(this,'prf.nick','','event')" type="button" value="삭제" />
            <table> 
                <tr> 
                    <th scope="row"></th>
                    <td>
                        <div class="tdcell">
                            <p class="contxt_username nickname">
                          <p class="contxt_username nickname">
                            <label>닉네임</label>
                                <!-- 로그인한 세션의 username 값으로 초기화 -->
                                <input type="text" name="username" id="username" value="${sessionScope.username}">
                                <input type="text" style="display: none;">
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
    <%@include file="/WEB-INF/tiles/footer.jsp"%> 

    
    <script>
    document.addEventListener('DOMContentLoaded', function () {
    	//DOMContentLoaded가 onload보다 먼저 실행된다.
        // 폼 내의 모든 입력 요소에 대해 이벤트 리스너 ㄱㄱ
        var inputs = document.querySelectorAll('input, select');
        inputs.forEach(function(input) {
            input.addEventListener('keypress', function(e) {
                // Enter 키가 눌렸을 때
                if (e.which === 13) {
                    // 기본 동작 방지
                    e.preventDefault();
                }
            });
        });
    });
    

    </script>
    <script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        // 삭제버튼
        var btnDelete = document.getElementById('btnDelete');
        if (btnDelete) {
            btnDelete.addEventListener('click', function() {    
                alert('프로필 이미지가 삭제되었습니다.'); 
            });
        }
    });
    
    </script>
    
</body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/tiles/header.jsp"%> 
    <title>프로필 설정 페이지</title>
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
            <input id="btnDelete" class="btn2_disable" onclick="clickcr(this,'prf.nick','','event')" type="button" value="삭제" />
            <table> 
                <tr> 
                    <th scope="row"></th>
                    <td>
                        <div class="tdcell">
                            <p class="contxt_username nickname">
                          <p class="contxt_username nickname">
                            <label>닉네임</label>
                                <!-- 로그인한 세션의 username 값으로 초기화 -->
                                <input type="text" name="username" id="username" value="${sessionScope.username}">
                                <input type="text" style="display: none;">
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
    <%@include file="/WEB-INF/tiles/footer.jsp"%> 

    
    <script>
    document.addEventListener('DOMContentLoaded', function () {
    	//DOMContentLoaded가 onload보다 먼저 실행된다.
        // 폼 내의 모든 입력 요소에 대해 이벤트 리스너 ㄱㄱ
        var inputs = document.querySelectorAll('input, select');
        inputs.forEach(function(input) {
            input.addEventListener('keypress', function(e) {
                // Enter 키가 눌렸을 때
                if (e.which === 13) {
                    // 기본 동작 방지
                    e.preventDefault();
                }
            });
        });
    });
    

    </script>
    <script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        // 삭제버튼
        var btnDelete = document.getElementById('btnDelete');
        if (btnDelete) {
            btnDelete.addEventListener('click', function() {    
                alert('프로필 이미지가 삭제되었습니다.'); 
            });
        }
    });
    
    </script>
    
</body>
</html>
>>>>>>> YS
