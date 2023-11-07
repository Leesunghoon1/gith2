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
	<div class="all-container">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<jsp:include page="../common/nav.jsp"></jsp:include>

		<div class="title">
			<h2>회원정보</h2>
		</div>
		<table id="table" class="table table-dark table-hover">
			<tr>
			<tr>
				<th>E-mail</th>
				<td>${mvo.email}</td>
			</tr>
			<tr>
				<th>PassWord</th>
				<td>**********</td>
			</tr>
			<tr>
				<th>Nick Name</th>
				<td>${mvo.nickName}</td>
			</tr>
			<tr>
				<th>가입일자</th>
				<td>${mvo.regAt}</td>
			</tr>
		</table>
		
		<div class="button-box">
			<a href="/member/modify?email=${mvo.email }">
			<button type="button" id="button" class="btn btn-outline-secondary">정보수정</button>
			</a>
		</div>


	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>