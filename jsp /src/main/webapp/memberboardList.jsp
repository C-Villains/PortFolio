<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>회원 게시판</title>
	</head>
	<body>
		<h3>회원 게시판입니다.</h3>
		
		<table border="1">
			<tr>
				<td colspan="7" align="right">
					<a href="memberboardWriteForm.mbs">[새글쓰기]</a>
				</td>
			</tr>
			<tr>
				<td>글 번호</td>
				<td>글 제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>작성시간</td>
				<td>조회수</td>
				<td>답글수</td>
			</tr>		
			<c:forEach items="${memberboardList }" var="dto">
				<tr>
					<td><a href="memberboardRead.bbs?num=${dto.num }">${dto.num }</a></td>
					
					<td>
						<c:forEach begin="1" end="${dto.lev }">
							<%= "&nbsp;&nbsp;" %>
						</c:forEach>
						<a href="memberboardRead.bbs?num=${dto.num }">${dto.subject }</a></td>
					<td>${dto.name }</td>
					<td>${dto.writeDate }</td>
					<td>${dto.writeTime }</td>
					<td>${dto.readCnt }</td>
					<td>${dto.childCnt }</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="7">
				<a href="memberboardList.bbs">[첫 페이지로]</a>
					<c:forEach var ="i" begin="1" end ="${pageCnt}">
						<a href="memberboardList.bbs?curPage=${i}">[${i}]</a>
					</c:forEach>
				</td>
			
			</tr>
			
			<tr>
				<td colspan="7" align="center">
					<form action="memberboardSearch.bbs" method="post">
						<select name="searchOption">
							<option value="subject">제목</option>
							<option value="content">본문</option>
							<option value="both">제목+본문</option>
							<option value="name">작성자</option>
						</select>
						<input type="text" name="searchWord">
						<input type="submit" value="검색">
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>