<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.iflin.server.AdminOperating"%>
     <%@ page import="cn.iflin.server.UserOperating"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"><title>课题部落 -- 测试版--后台管理</title>
<link rel="stylesheet" type="text/css" href="css/user_index.css" />
<script type="text/javascript">
function getUser(email){
    var url = "getuser?email="+email;
    sendRequest(url);
}   
var XMLHttpReq = false;
//创建XMLHttpRequest对象       
function createXMLHttpRequest() {
    if(window.XMLHttpRequest) { //Mozilla 浏览器
        XMLHttpReq = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) { // IE浏览器
        try {
            XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
        }
    }
}
//发送请求函数
function sendRequest(url) {
    createXMLHttpRequest();
    XMLHttpReq.open("GET", url, true);
    XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
    XMLHttpReq.send(null);  // 发送请求
}
// 处理返回信息函数
function processResponse() {
    if (XMLHttpReq.readyState == 4) { // 判断对象状态
        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            var result = XMLHttpReq.responseText;    
            document.getElementById("data").innerHTML = result;     
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
</script>
</head>
<body style="width:970px; margin:0 auto;">
	<div class="top">
		<ul>
			<li class="topli">
				<a href="index.html" >主页</a>
			</li>
		</ul>
	</div>  
	<div class="pic">
	<img src="images/logo/logo.png"  width="270" height="129">
	</div>
	<div class="resultArea">
		<div class="result">
			<h2>尊敬的${requestScope.realname}，欢迎回来</h2>
		</div>
		<div class="result" style="margin-top:150px;">
			<h2>用户管理</h2>
			<c:forEach items="${users}" var="user">
			<div>
				<a href="massage?keyword=${user.getEmail()}">${user.getRealname()}${user.getWorkspace()}</a>				
			</div>
			</c:forEach>
		</div>
		<div class="result" id="user" style="width:20%;float:left;margin-top:150px;">
		<ul>
			<c:forEach items="${users}" var="user">
			<li>
			<a id="userid" href="javascript:void(0)" onclick="getUser('${user.getEmail()}')">${user.getRealname()}</a>
<br><br><br>
			</li>
			</c:forEach>
		</ul>
		</div>
		<div id = "data" style="width:80%;float:left;">
			
		</div>
	</div>
</body>
</html>