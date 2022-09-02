<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<br>
	<br>
		
	<div class="container">

	<div class="row" style="margin-top:20px">
	    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
			<form action="${contextPath }/member/findId" method="post" class="checkout__form">
				<fieldset>
					<h2 align="center">아이디 찾기</h2>
					<hr class="colorgraph">
					<div class="form-group">
	                    <input type="text" name="memberNm" id=""memberNm"" class="form-control input-lg" placeholder="이름">
					</div>
					<div class="form-group">
	                    <input type="email" name="email" id="email" class="form-control input-lg" placeholder="이메일">
					</div>
					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
	                        <input type="submit" class="btn btn-lg btn-success btn-block" value="찾기">
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6">
							<a href="${contextPath }/member/findPw" class="btn btn-lg btn-primary btn-block">비밀번호 찾기</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	
</body>
</html>