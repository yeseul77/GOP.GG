<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>GOP 커뮤니티</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-3">
    <h1 class="text-center">GOP 커뮤니티</h1>


    <table class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">제목</th>
                <th scope="col">글쓴이</th>
                <th scope="col">날짜</th>
                <th scope="col">조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boards}">
                <tr>
                    <td>${board.b_bno}</td>
                    <td>${board.b_title}</td>
                    <td>${board.username}</td>
                    <td><fmt:formatDate value="${board.b_date}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${board.viewcount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 글쓰기 버튼 -->
    <div class="text-right mb-3">
        <a href="boardlist/boardwrite" class="btn btn-primary">글쓰기</a>
    </div>

    <!-- 페이징 -->
    <c:if test="${pageCount > 1}">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:forEach begin="1" end="${pageCount}" var="pageNum">
                    <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${pageNum}">${pageNum}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
