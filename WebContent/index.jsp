<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<div align="center">
	  <font color="blue" size="5">管理员登录</font><br><br>
		<form action="<%=basePath%>admin/login.xhtml" method="post">
			用户名:<input type="text" name="username"><br>
			密&nbsp;&nbsp;码<input type="password" name="password" value=""><br>
			<input type="submit" value="登录">
		</form>

	</div>
</body>
</html>
