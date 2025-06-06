<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>게시판 글 수정</title>
	</head>
	<body>
		<h3>게시판 글 수정</h3>
		<form action="memberboardUpdate.mbs" method="post">
			<table>
				<tr>
					<td colspan="4" align="right">
					<input type="hidden" name="num" value="${memberboardUpdateForm.num}"><a href="memberboardList.mbs">[목록으로]</a></td>
				</tr>
				<tr>
					<td>글 제목</td>
					<td colspan="3"><input type="text" name="subject" maxlength="50" size="50" value="${memberboardUpdateForm.subject}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="name" maxlength="20" size="20" value="${memberboardUpdateForm.name}" ></td>
					<td>비밀번호</td>
					<td><input type="password" name="password" maxlength="20" size="12"></td>				
				</tr>
				<tr>
					<td>본문</td>
					<td colspan="3"><textarea name="content" rows="8" cols="45">${memberboardUpdateForm.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<input type="submit" value="수정완료">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>