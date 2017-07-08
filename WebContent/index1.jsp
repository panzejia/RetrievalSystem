<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="getarticles" class="cn.iflin.server.GetArticles" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>课题部落</title>
</head>
<body>
	<div class="top">
		<ul>
			<li class="topli">
			<%	
			if(session.getAttribute("username")==null){
				out.print("<a class=\"topli\" href=\"login\">登陆</a>");
			}else{
				out.print("<a class=\"topli\" href=\"personal\">"+session.getAttribute("username")+"</a>");
			} %>
			</li>
		</ul>
	</div>

<div class="body">
    <div class="pic">
		<a href="index.html">
		<img src="images/logo/logo.png"></a>
    </div>
    <div class="search">
        <form action="getresult" method="get"  >
            <input autocomplete="off" class="searchinput" id="search" type="text" name="sw" value="">
			<input class="inputbutton" id="submit" type="submit" value="搜索" />
        </form>
    </div>
</div>
<div class="foot">
    <div class="container">
	<div class="row">
	<c:forEach items="${articles}" var="article">
  		<div class="col-md-4">
    		<h2>${article.articleTtile}</h2>
    		<p>${article.articleText}</p>
  		</div>
  	</c:forEach>
	</div>
   </div>
</div>
</body>
</html>