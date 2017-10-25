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
	用户名<br>
	密码<br>
	
	以下是国际化的使用方法<br>
	<fmt:message key="login.username"/><br>
	<fmt:message key="login.password"/><br>
	自定义localeResolver解析locale参数<br>
	<a href="i18n/show?locale=zh_CN">中文</a> | <a href="i18n/show?locale=en_US">English</a>
	
	
	
	
	
	
	

</body>


</html>