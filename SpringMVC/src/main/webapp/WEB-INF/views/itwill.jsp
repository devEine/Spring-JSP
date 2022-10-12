<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/itwill.jsp</h1>
	<!-- 주소창에 ?mag=busan으로 값 전달할 시, 화면에 출력됨  -->
	JSP: <%=request.getParameter("msg") %>
	EL(param):${param.msg } <br>
	EL: ${msg } <!-- 화면에 표시안됨  -->
</body>
</html>