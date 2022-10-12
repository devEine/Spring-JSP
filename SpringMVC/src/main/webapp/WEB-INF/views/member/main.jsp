<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/member/main.jsp</h1>
	<h2>메인페이지</h2>
	
	<!--로그인 정보가 없으면, login페이지로 이동 -->
	<c:if test="${loginVO ==null }"> 
		<!-- 사용자 정보없음 --> 
		<c:redirect url="/member/login"></c:redirect>
	</c:if>
	
	${loginVO.userid }님 환영합니다.<br>
	${sessionScope.loginVO.userid }님 환영합니다.<br>
	
	<!--로그아웃   -->
	<input type="button" value="로그아웃"
			onclick="location.href='/member/logout';">
	<a href="javascript: location.href='/member/logout';">로그아웃</a>
	
	<hr>
	
	<a>회원정보 조회</a><br>
	<input type="button" value="회원정보"
			onclick="location.href='/member/info';">
</body>
</html>