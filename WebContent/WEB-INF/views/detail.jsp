<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="getarticle" class="cn.iflin.server.SystemGetArticle" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/detail.css" />
<title>${result.getTitle()}--课题部落</title>
</head>
<body>
<div id="header">
	<h1>申报详情</h1>
</div>
<div class="area">
	<div id="nav">
		时间：${result.getTime()}
		类别：计算机
		要求：
	</div>

	<div id="section">
		<h2 style="text-align:center">${result.getTitle()}</h2>
		<a>${result.getContent()}</a>
	</div>
</div>
<div class="foot">
	课题部落
</div>
</body>
</html>