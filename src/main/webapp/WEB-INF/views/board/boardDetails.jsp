<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>${board.b_title}</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-3">
    <div class="card">
        <div class="card-body">
            <h3 class="card-title">${board.b_title}</h3>
            <p class="card-text">
                <small class="text-muted">
                    작성자: ${board.username} |
                    작성일: <fmt:formatDate value="${board.b_date}" pattern="yyyy-MM-dd HH:mm"/> |
                    조회수: ${board.viewcount}
                </small>
            </p>
            <p class="card-text">${board.b_content}</p>

            <!-- 추천 버튼 -->
            <button type="button" class="btn btn-outline-primary">
                <i class="fa fa-thumbs-up"></i> 추천 <span class="badge badge-light">${board.likes}</span>
            </button>
        </div>
    </div>

    <!-- 댓글  -->

    <!-- 목록으로 돌아가기  -->
    <div class="text-right mt-3 mb-3">
        <a href="boardList.jsp" class="btn btn-secondary">목록</a>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.9.11/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
