<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>member/info.jsp</h1>
	
	<h2>${userVO.userid }님의 회원정보</h2><br>
	
	이름: ${userVO.username }<br>
	비밀번호: ${userVO.userpw }<br>
	이메일: ${userVO.useremail }<br>
	회원가입일: ${userVO.regdate }<br>
	
	<a href="/member/main">메인페이지</a>
</body>
</html>