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
  <font color="black" size="6">欢迎管理员: <font color="red" size="7">${username}</font>登录</font><br>
  <br><br>
  <a href="<%=basePath %>admin/reIndex.xhtml?type=url_product">删除<font color="red">各种产品</font>索引</a><br><br>
  <a href="<%=basePath %>admin/reIndex.xhtml?type=url_product_gold">删除<font color="red">实物金</font>索引</a><br><br>
  <a href="<%=basePath %>admin/reIndex.xhtml?type=url_shop">删除<font color="red">机构</font>索引</a><br><br>
  <a href="<%=basePath %>admin/reIndex.xhtml?type=url_shop_goods">删除<font color="red">机构商品</font>索引</a><br><br>
  <a href="<%=basePath %>admin/reIndex.xhtml?type=all">删除<font color="red">所有</font>索引</a><br><br>
  
  
  
  
  
  <br><br><br><br>
  <a href="<%=basePath %>/index.jsp">返回首页</a>
</div>
</body>
</html>
