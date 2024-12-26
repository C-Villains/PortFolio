<%@ page language="java" contentType="text/html; charset=utf-8" %>

<%
    String userId = (String) session.getAttribute("userId");
%>

<html>
    <head>
        <title>가입 성공</title>
    </head>
    <body>
        <h2>가입 성공!</h2>
        <p>안녕하세요, <%= userId %>님!</p>
        <a href="HiddenFieldLogin.jsp">로그인 하러가기</a>
    </body>
</html>