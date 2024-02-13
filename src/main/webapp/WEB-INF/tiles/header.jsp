<html>
<head>
    <title>GOP.GG</title>
    <style>
        /* 전체 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        /* 상단 네비게이션 스타일 */
        .topnav {
            background-color: #333;
            overflow: hidden;
        }

        .topnav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* 로그인/로그아웃 정보 스타일 */
        .user-info {
            float: right;
            color: white;
            padding: 14px 16px;
        }
    </style>
</head>
<body>
    <div class="topnav">
        <a href="/index">Home</a>
        <a href="/board">BOARD</a>
        <% if (session.getAttribute("email") == null) { %>
            <a href="/login">LOGIN</a>
            <a href="/register">REGISTER</a>
            <a href="/register">MY PAGE</a>
        <% } else { %>
            <!-- 로그인한 사용자에게만 보이는 부분 -->
            <a href="/mypage">MY PAGE</a>
            <div class="user-info">
                접속자정보 : ${username} 
                <a href="/logout">LOGOUT</a>
            </div>
            <a href="/chat/chatList">Matching</a>
        <% } %>
    </div>
</body>
</html>