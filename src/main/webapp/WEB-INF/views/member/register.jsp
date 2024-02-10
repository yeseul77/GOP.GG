<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>GOP.GG 회원가입</title>
</head>
<body>
<script>
function registerCheck(){
	var memberID=$("email").val;
	$ajax({
		url:"${contextPath}/register"
		type:"post"
		success:function(result){
			
		},
		error:function(){}
	})
}
</script>
<script type="text/javascript">
$(document).ready(function(){
	if(${!empty msgType}){
		$("#messageType").attr("class","modal-content panel-warning");
		$("#myMessage").modal("show");
	}
});

</script>
<%@include file="/WEB-INF/tiles/header.jsp" %>

<h2>GOP.GG 회원가입</h2>
<form action="/register" method="post">
<table class="registertable" style="text-align:center; border: 1px">
<tr>
    <td><input type="text" name="email" id="email" placeholder="아이디"><br></td>
    <td><button type="button" onclick="registercheck()">중복확인</button></td>
</tr>
<tr>
    <td><input type="password" name="password" id="password" onkeyup="pwcheck()" placeholder="비밀번호"><br></td>
    <td><input type="password" name="password2" id="password2" onkeyup="pwcheck()" placeholder="비밀번호확인"><br></td>
</tr>
<tr>
    <td><input type="text" name="username" id="username" placeholder="닉네임"><br></td>
</tr>
</table>
<button type="submit">회원가입</button>
</form>
<!-- 다이얼로그창(모달) -->
<!-- Modal  부트스트랩 모달임!  myModal-->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div id="checkType" class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">메세지 확인</h4>
      </div>
      <div class="modal-body">
        <p id="checkMessage"></p>
<!--         p태그에 id값줘서  동작하게하려고함-->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 푸터 -->
<%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
</html>
