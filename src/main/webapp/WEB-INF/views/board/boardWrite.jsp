<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 작성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
        crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/dayjs@1.10.6/dayjs.min.js"></script>
   	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <script>
        function initCreatedDate() {
            document.getElementById('createdDate').value = dayjs().format('YYYY-MM-DD');
        }

//         window.onload = function() {
//             initCreatedDate();
//         };

		$(document).ready(function(){
			initCreatedDate();
		});

    </script>
</head>
<body>
    <div class="wrap">
        <h1>글쓰기</h1>
        <form action="/board/boardwrite" method="post">
            <input type="hidden" name="username" value="${memberDto.username}">
            <label for="title">제목:</label>
            <input id="title" name="title">
            <br>
            <label for="writer">작성자:${memberDto.username}</label>
            <br>
            <label for="createdDate">등록일:</label>
            <input type="text" id="createdDate" name="createdDate" readonly />
            <br>
            <label for="content">내용:</label><br>
            <textarea id="content" name="content" rows="10" cols="60"></textarea>
            <br>
            <label for="boardtype">type</label>
            <select id="boardtype" name="boardtype">
                <option value="유저찾기">유저찾기</option>
                <option value="꿀팁">꿀팁</option>
            </select>
            <br>
            <input type="submit" value="작성">
        </form>
    </div>
</body>
</html>
