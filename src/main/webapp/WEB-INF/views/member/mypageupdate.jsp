<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>회원 정보 수정</h2>
    <form action="/update" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <p>
            Email<br>
            <input type="text" name="email" value="${member.email}"/>
        </p>

        <p>
            Username<br>
            <input type="text" name="username" value="${member.username}"/>
        </p>
    
        <p>
            Password<br>
            <input type="password" name="password" placeholder="Password를 입력해주세요"/>
        </p>

        <button type="submit">저장하기</button>
    </form>
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>회원 정보 수정</h2>
    <form action="/update" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <p>
            Email<br>
            <input type="text" name="email" value="${member.email}"/>
        </p>

        <p>
            Username<br>
            <input type="text" name="username" value="${member.username}"/>
        </p>
    
        <p>
            Password<br>
            <input type="password" name="password" placeholder="Password를 입력해주세요"/>
        </p>

        <button type="submit">저장하기</button>
    </form>
</body>
>>>>>>> YS
</html>