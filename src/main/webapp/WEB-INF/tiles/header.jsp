<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>GOP.GG</title>
    <style>
        /*
        * top-bar
        */
        .top-bar {
            background-color: #008000;
            color: #fff;
            min-height: 58px;
            line-height: 58px;
            border: none;
        }

        .top-left {
            float: left !important;
            padding: 0px;
            margin: 0px;
        }

        .logo {
            margin-top: 4px;
            margin-right: 5px;
            width: 50px;
            border-radius: 10px;
        }

        .top-right {
            float: right !important;
        }

        .top-right ul {
            list-style: none;
            padding: 0px;
            margin: 0px;
        }

        .top-right ul li {
            float: left;
            padding-left: 20px;
        }

        .top-right ul li a {
            text-decoration: none;
        }

        .top-right ul li a:link {
            color: white;
        }

        .top-right ul li a:visited {
            color: #fff;
        }

        .top-right ul li a:hover {
            color: #ffa07a;
        }

        .suc {
            display: none;
        }

        .bef {
            display: block;
        }
    </style>
</head>
<body>
<div class="top-bar">
    <div class="content">

        <h2 class="top-left top-home">GOP.GG</h2>
        <nav class="top-right">
            <c:if test="${empty m_id}">
                <ul>
                    <li class="suc" id="m_name">소환사님</li>
                    <li class="suc">
                        <form action="/member/logout" method="post">
                            <button>로그아웃</button>
                        </form>
                    </li>
                    <li class="bef"><a href="/login">로그인</a></li>
                    <li class="bef"><a href="/register">회원가입</a></li>
                    <li class="bef"><a href="/mypage">마이페이지</a></li>
                </ul>
            </c:if>
            <c:if test="${!empty m_id}">
                <ul>
                    <li class="suc" id="m_name">소환사님</li>
                    <li class="suc">
                        <form action="/member/logout" method="post">
                            <button>로그아웃</button>
                        </form>
                    </li>
                    <li class="bef"><a href="/logout">로그아웃</a></li>
                    <li class="bef"><a href="/mypage">마이페이지</a></li>
                </ul>
            </c:if>
        </nav>
    </div>
</div>

</body>
</html>
