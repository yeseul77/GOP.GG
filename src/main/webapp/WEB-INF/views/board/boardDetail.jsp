<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>게시물 상세 정보</title>
<!-- jQuery 라이브러리 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h2>게시글상세보기</h2>
	<table border="1" cellspacing="0" cellpadding="0" width="90%">
		<tr>
			<th width="10%" align="center">작성자</th>
			<td width="50%">${boardDto.username}</td>
		</tr>
		<tr>
			<th align="center">제목</th>
			<td>${boardDto.title}</td>
		</tr>
		<tr>
			<th align="center">내용</th>
			<td>${boardDto.content}</td>
		</tr>
		<tr>
			<td align="center">조회수</td>
			<td>${boardDto.viewcount}</td>
		</tr>
		<tr>
			<td id="likecount">${boardDto.likes}</td>
			<td>
				<button id="likes">♡</button>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button">수정</button>
				<button type="button" onclick="goList();">목록</button>
			</td>
		</tr>
	</table>

	<script>
		$(document).ready(function() {
			$('#likes').click(function() {
				let username = '${boardDto.username}';
				let idx = '${boardDto.idx}';
				
				console.log("likes start");

				if (username == null || username === '') {
					alert("로그인 해주세요!");
					location.href = "/login";
				} else {
					$.ajax({
						method: 'post',
						url: '/likes',
						data: {
							idx: idx,
							username: username
						}
					}).done(function(res) {
						alert(res);
						alert(res);
						location.href = "/detail?idx="+idx;
						updateLikeCount();
					}).fail(function(err) {
						alert("좋아요bb");
					});
				}
			});
		});

		function goList() {
			window.history.back();
		}

		function updateLikeCount() {
			$.ajax({
				method: 'get',
				url: '/likes/count?idx=${boardDto.idx}',
				success: function(response) {
					$('#likecount').text(response.likes);
				},
				error: function(xhr, status, error) {
					console.error(error);
				}
			});
		}
	</script>
</body>
</html>
