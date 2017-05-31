<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>科研项目申报信息检索系统</title>
<style>
<!--
#idxlogo {
    position: relative;
    margin-top: 40px;
    text-align: center;
    width: 100%;
    
}
#indexpage {
    width: 100%;
    padding: 0;
    margin: 20px 0;
    text-align: center;
}
#searchinput {
    width:500px;
	height:25px;
}
#inputbutton {
    width:90px;
	height:35px;
	background:#EDEDED;
	font-family:微软雅黑	;
	text-align:center;
	text-decoration:none;
}
-->
</style>
</head>
<body>
<div id="idxlogo" >
	<a href="http://www.iflin.cn:8080/RetrievalSystem">
	<img src="http://www.iflin.cn:8080/RetrievalSystem/image/logo/logo.png"></a>
</div>
<div id="indexpage">
	<form action="getresult" method="post"  >
		<input id="searchinput" type="text" name="searchResult" value="">
		<input id="inputbutton" type="submit" value="搜索" />
	</form>
	<br><br><br>
	<a href="getarticle">获取TOP30文章</a>
</div>
</body>
</html>