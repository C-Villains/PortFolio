<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
<head>
    <title>Hidden Field을 이용한 사용가능 메뉴확인</title>
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");
    
        String id = request.getParameter("id");
        String grade_name = request.getParameter("grade_name");

        if (id == null || grade_name == null) {
            // 사용자 정보가 없으면 로그인 페이지로 이동
            response.sendRedirect("HiddenFieldLogin.jsp");
            return;
        }
    %>
    <h3>[<%=id%>(<%=grade_name %>)] 접속중 입니다.<br/>사용가능 메뉴입니다.</h3>
    <hr/>
    <form action="HiddenFieldMenuServlet" method="post">
        <%
            String grade = request.getParameter("grade");

            if (grade.equals("admin")){
        %>
        <input type="button" value="사이트 관리">
        <input type="button" value="회원관리">
        <form action="boardList.bbs" method="post">
            <input type="submit" value="자유 게시판">
        </form>
        <form action="memberboardList.mbs" method="post">
            <input type="submit" value="회원 게시판">
        </form>
        <%
            } else if (grade.equals("member")){
        %>
        <form action="boardList.bbs" method="post">
            <input type="submit" value="자유 게시판">
        </form>
       <form action="memberboardList.mbs" method="post">
           <input type="submit" value="회원 게시판">
        <%
            } else {
        %>
        <form action="boardList.bbs" method="post">
            <input type="submit" value="자유 게시판">
        </form>
        <%
            }
        %>
    </form>
</body>
</html>
