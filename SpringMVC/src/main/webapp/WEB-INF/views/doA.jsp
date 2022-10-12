<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>doA.jsp</h1>
	name: ${name }<br> 
	<!-- Controller에서 @modelAttribute 설정해서 영역표시없이 이름으로만 부를 수 있음 -->
	name(param): ${param.name }<br>
	
	tel: ${tel }<br>
	
</body>
</html>