<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test.jsp</h1>
	email: ${email }
	<hr>
	
	${vo }
	<hr>
	${memberVO }<br>
	<!--Controller에서 매개변수 MemberVO로 설정 -> MemberVO에 있는 데이터 모두 불러올 수 있음  -->
	${memberVO.username }
	<!-- 특정정보만 출력하고 싶을때  -->
	<hr>
	${DBVO }<br>
	${DBVO.username }
	<hr>
</body>
</html>