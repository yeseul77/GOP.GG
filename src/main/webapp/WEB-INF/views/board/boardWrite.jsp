<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글작성</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#savebtn").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		
		if(title ==""){
			alert("제목을 입력해주세요");
			document.boardform.title.focus();
			return false;
		}
		if(content ==""){
			alert("내용을 입력해주세요");
			document.boardform.content.focus();
			return false;
		}
<<<<<<< HEAD

=======
		// 폼에 입력한 데이터 서버로 전송
>>>>>>> 4650059d4c15d21ba8f31478a2cfb7c856c43d37
		document.boardform.submit();
	});
});
</script>
<script>
$(document).ready(function(){
    $("#resetbtn").click(function(){
        var title = $("#title").val();
        var content = $("#resetbtn").val();
        if(title !== "" || content !== "") {
            var confirmLeave = confirm("작성한 내용을 지우고 목록으로 돌아가시겠습니까?");
            if(confirmLeave) {
                window.location.href = "${pageContext.request.contextPath}/boardlist";
            }
        } else {
            window.location.href = "${pageContext.request.contextPath}/boardlist";
        }
    });
});

</script>
<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!--헤더 위치 -->
</head>
<body>
<h2>글쓰기</h2>
<form name="boardform" method="post" action="${contextPath}/boardwrite">
<div>
제목
<input name="title" id="title" size="80" placeholder="제목을 입력해주세요">
</div>
<div>
내용
<textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요"></textarea>
</div>
<div style="width:650px; text-align:center;">
<button type="button" id="savebtn" >저장</button>
<button type="button" id="resetbtn" class="btn btn-default">취소</button>
</div>
</form>
<!-- 푸터 위치 -->
 <%@include file="/WEB-INF/tiles/footer.jsp" %>
 <!-- 푸터 위치 -->
</body>
</html>
