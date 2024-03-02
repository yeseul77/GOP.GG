<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필사진변경</title>
</head>
<body>

<!-- 헤더 위치 -->
 <%@include file="/WEB-INF/tiles/header.jsp" %>
 <!-- 헤더 위치 -->

<form action="${contextPath}/imageupdate" method="post" enctype="multipart/form-data">
  <input type="hidden" name="username" value="${memberDto.username}"/>
  <table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd;">
    <tr>
      <td style="width: 110px; vertical-align: middle;">아이디</td>
      <td>${memberDto.email}</td>
    </tr>
    <tr>
      <td style="width: 110px; vertical-align: middle;">사진 업로드</td>
      <td colspan="2">
        <span class="btn btn-default">
          이미지를 업로드하세요.
          <input type="file" name="profileImage" />
        </span>
      </td>            
    </tr>      
    <tr>
      <td colspan="2" style="text-align: left;">
         <input type="submit" class="btn btn-primary btn-sm pull-right" value="등록"/>
      </td>             
    </tr>
  </table>
</form> 
<!-- 실패 메세지를 출력(modal) -->
<div id="myMessage" class="modal fade" role="dialog" >
  <div class="modal-dialog">  
    <!-- Modal content-->
    <div id="messageType" class="modal-content panel-info">
      <div class="modal-header panel-heading">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">${messageType}</h4>
      </div>
      <div class="modal-body">
        <p>${message}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>  
  </div>
</div>   
	<!-- 푸터 위치 -->
	 <%@include file="/WEB-INF/tiles/footer.jsp" %>
	 <!-- 푸터 위치 -->
</body>
</html>
