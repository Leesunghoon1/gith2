<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register page</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
			
		<sec:authentication property="principal.mvo.email" var="authEmail" />

	<form action="/board/register" method="post"
		enctype="multipart/form-data">

		<div class="mb-3">
			<label for="t" class="form-label">제목</label> <input type="text"
				class="form-control" name="title" id="t" placeholder="제목">
		</div>

		<div class="mb-3">
			<label for="w" class="form-label">작성자</label> <input type="text"
				class="form-control" name="writer" id="w" placeholder="Email" value="${authEmail}" readonly="readonly">
		</div>


		<div class="mb-3">
			<label for="c" class="form-label">내용</label> <input type="text"
				class="form-control" name="content" id="c" placeholder="내용">
		</div>

		<!-- 파일 업로드 영역  -->
		<div class="input-group mb-3">
			<input type="file" class="form-control" multiple="multiple"
				style="display: none" id="files" name="files"
				aria-describedby="basic-addon1">
			<!-- input button trigger 용도 -->
		</div>
		<!-- input button trigger 용도 -->
		<!-- button을 못꾸미니까 input으로 css꾸미려고 -->

		<div class="fileupload-btn-box">
			<button type="button" id="trigger" class="btn btn-secondary">파일첨부</button>
		</div>

		<!-- 첨부파일 표시 영역 -->
		<div class="input-group mb-3" id="file-zone"></div>

		<div class="button-box">
			<button type="submit" id="button" class="btn btn-outline-secondary">등록</button>
		</div>
	</form>


	<jsp:include page="../common/footer.jsp" />

	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>

</body>
</html>