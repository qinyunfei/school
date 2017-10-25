<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<base href="${pageContext.request.contextPath}/school">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 引入JQuery -->
<script type="text/javascript" src="resource/easyui/jquery.min.js"></script>



</head>

<body>
	<a href="messageConverter/1">发get请求</a><br>
	<a href="messageConverter/2">发get请求</a><br>
	
	
	<form action="messageConverter/3" method="post">
	<input type="text" name="name" value="sasas">
	<input type="submit" value="发post请求">
	</form>
	<a href="messageConverter/4">发请求</a><br>
	<a href="messageConverter/5">发请求</a><br>
	
	
</body>


</html>