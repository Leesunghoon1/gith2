<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>

		<nav class="navbar navbar-expand-lg bg-body-tertiary">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Navbar</a>

				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">

						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/">Home</a></li>

						<li class="nav-item"><a class="nav-link" href="/board/list">Board
								List</a></li>

						<li class="nav-item"><a class="nav-link"
							href="/member/register">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" href="/member/login">로그인</a></li>








						<sec:authorize access="isAuthenticated()">
							<!-- 로그인시 보여줄 네브바  -->


						</sec:authorize>







						<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
						<!-- 사용자 정보 principal -->
						<sec:authorize access="isAuthenticated()">

							<!-- 선언해주기  -->

							<sec:authentication property="principal.mvo.email"
								var="authEmail" />
							<sec:authentication property="principal.mvo.nickName"
								var="authNick" />
							<sec:authentication property="principal.mvo.regAt"
								var="authRegAt" />
							<sec:authentication property="principal.mvo.lastLogin"
								var="authLastLogin" />
							<sec:authentication property="principal.mvo.authList" var="auths" />

							<c:choose>
								<c:when
									test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
									<li class="nav-item"><a class="nav-link" href="#">*ADMIN
											${authNick}(${authEmail})</a></li>
									<li class="nav-item"><a class="nav-link"
										href="/member/list">Member List</a></li>
								</c:when>
								<c:when
									test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_USER')).get()}">
									<li class="nav-item"><a class="nav-link" href="#">*ROLE_USER
											${authNick}(${authEmail})</a></li>
								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>

							<!-- 로그인 해야 오픈되는 서비스 -->
							<form action="/member/logout" method="post" id="logoutForm">
								<input type="hidden" name="email" value="${authEmail }">
							</form>
							<li class="nav-item"><a class="nav-link" id="logoutLink"
								style="cursor: pointer">로그아웃</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/member/detail/${authEmail}">Member Info</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/board/register">Board register</a></li>



						</sec:authorize>


					</ul>
				</div>
			</div>
		</nav>

		<script type="text/javascript">
		//버튼 대신 a링크로 form태그 날릴수있게 도와주는 역활
		document.getElementById('logoutLink').addEventListener('click', (e)=>{
			e.preventDefault();
			document.getElementById('logoutForm').submit();
		})
		
		</script>



	</header>
</body>
</html>