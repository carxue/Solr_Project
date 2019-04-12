<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
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
<title></title>
</head>
<body>
<div align="center">
     <br> <br><br><br><br>
  <font color="red" size="6">查询结果</font><br>
  ${flist} <br>
  <br><br><br><br>
  <a href="<%=basePath %>/index.jsp">返回首页</a>
</div>
</body>
</html>
