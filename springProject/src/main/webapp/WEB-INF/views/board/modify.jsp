<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>

	<form action="/board/modify" method="post">
		div class="position-absolute top-50 start-50 translate-middle">
		<div class="table-box">
			<div class="title">
				<h2>글수정</h2>
			</div>
			<table id="table" class="table table-dark table-hover">
				<tr>
					<th>#</th>
					<td><input type="text" name="bno" readonly="readonly"
						value="${bvo.bno }"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="${bvo.title}">
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" readonly="readonly" name="writer"
						value="${bvo.writer}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="form-control" aria-label="With textarea"
							name="content"></textarea></td>
				</tr>
			</table>
		</div>
		<div class="button-box">
		<div>
			<button type="submit" id="button" class="btn btn-outline-secondary">수정</button>
			<a href="/board/remove?bno=${bvo.bno }"><button type="button" id="button" class="btn btn-outline-secondary">삭제</button></a>
		</div> 
		</div>
	</form>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>