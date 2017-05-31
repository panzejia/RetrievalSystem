<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="getarticles" class="cn.iflin.server.GetArticles" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取TOP30文章</title>
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
-->
</style>
</head>
<body>
	<div id="idxlogo" >
		<a href="http://www.iflin.cn:8080/RetrievalSystem">
		<img src="http://www.iflin.cn:8080/RetrievalSystem/image/logo/logo.png"></a>
	</div>
    <div id="indexpage">
       <c:forEach items="${articles}" var="article">
          <div class="title" >
          	<h1>${article.articleTtile}</h1>
          </div>
          <div class="time" >
          	<p>${article.articleTime}</p>
          </div>
          <div class="context" >
          	<p>${article.articleText}</p>
          </div>
       </c:forEach>
       <br />
    </div>
</body>
</html>