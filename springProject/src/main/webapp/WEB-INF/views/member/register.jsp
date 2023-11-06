<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	
	<form action="/member/register" method="post">
	
				<div class="title"><h2>회원가입</h2></div>
	
				<div >
				<span>E-mail(ID)</span>
				  <input type="text" class="form-control" placeholder="E-MAIL" name="email" aria-describedby="basic-addon1">
				</div>
				<div>
				<span>PW</span>
				 <input type="text" class="form-control" placeholder="PASSWORD" name="pwd" aria-describedby="basic-addon1">
				</div>
				<div>
					<span>닉네임</span>
					<input type="text" class="form-control" placeholder="닉네임" name="nickName" aria-describedby="basic-addon1">
				</div>
				
				<div class="button-box">
				<button type="submit" id="sign-btn" class="btn btn-outline-secondary">
				회원가입
				</button>
				
				</div>
	
	
	</form>
	



	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>