<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.iflin.server.UserOperating"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>课题部落 --测试版--我的个人信息</title>
<link rel="stylesheet" type="text/css" href="css/user_index.css" />
</head>
<body>  
     <ul>
	<li><a href="index.html">主页</a></li>
     </ul>  
	<div class="pic">
	<img src="images/logo/logo.png"  width="270" height="129">
	</div>
	<div class="resultArea">
		<div class="result">
			<h2>${requestScope.username}，欢迎回来</h2>
			<p>email地址：${requestScope.email}</p>
			<p>联系电话：${requestScope.phone}</p>
		</div>
	</div>
</body>
</html>