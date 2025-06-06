<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "board.model.*" %>
<%@ page import = "java.util.*" %>

<html>
	<head>
		<title>게시판 글 열람</title>
	</head>
	<body>
		<h3>게시판 글 열람</h3>
		<table>
			<tr>
				<td colspan="4" align="right"><a href="memberboardList.bbs">[목록으로]</a></td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td colspan="3"><input type="text" name="subject" maxlength="50" size="50" value="${memberboardRead.subject}" disabled="disabled"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" maxlength="20" size="20" value="${memberboardRead.name}" disabled="disabled"></td>
				<td>조회수:${memberboardRead.readCnt},</td>
				<td>답글수:${memberboardRead.childCnt}</td>
			</tr>
			<tr>
				<td>본문</td>
				<td colspan="3"><textarea name="content" rows="8" cols="45" disabled="disabled">${memberboardRead.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<a href="memberboardUpdatePassword.mbs?num=${boardRead.num}">[수정]</a>
					<a href="memberboardDeletePassword.mbs?num=${boardRead.num}">[삭제]</a>
					<a href="memberboardReplyForm.mbs?num=${boardRead.num}">[답글]</a>
				</td>
			</tr>
		</table>
	</body>
</html>