<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="getresult" class="cn.iflin.server.SystemSearch" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
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
       <c:forEach items="${results}" var="result">
          <div class="title" >
          	<h1>${result.getTitle()}</h1>
          </div>
          <div class="time" >
          	<p>${result.getTime()}</p>
          </div>
          <div class="context" >
          	<p>${result.getContent()}</p>
          </div>
       </c:forEach>
    </div>
</body>
</html>