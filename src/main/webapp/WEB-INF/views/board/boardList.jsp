<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>자유게시판</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h1>GOP.GG 커뮤니티</h1>

		<!-- 검색 -->
		<div class="search_box">
			<form id="searchForm" onsubmit="return false;" autocomplete="off">
				<div class="sch_group fl">
					<select title="검색 유형 선택">
						<option value="">제목</option>
						<option value="">내용</option>
					</select> <input type="text" placeholder="키워드를 입력해 주세요." title="키워드 입력" />
					<button type="button" class="bt_search">
						<i class="fas fa-search"></i><span class="skip_info">검색</span>
					</button>
				</div>
			</form>
		</div>

		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">번호</th>
					<th scope="col" width="40%">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${bList}" varStatus="loop">
					<tr>
						<th scope="row">${board.idx}</th>
						<td><a href="detail?idx=${board.idx}">${board.title}</a></td>
					
						<td>${memberList[loop.index].username}</td>
						<td>${board.viewcount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/board/boardwrite" class="btn btn-primary">글쓰기</a>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
