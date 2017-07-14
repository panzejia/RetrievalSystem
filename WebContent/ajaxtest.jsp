<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     
    <title>My JSP 'ajaxIndex.jsp' starting page</title>
     
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
 
<script type="text/javascript">
function test(text){
    var url = "getData.jsp?username="+text;
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
		<div id="user" class="result" style="width:30%;flaot:left;">
			<br><br>
			<li><a id="userid" href="javascript:void(0)" onclick="test('123')">123</a></li>
		</div>
		<div id = "data" style="width:70%;flaot:right">
		ceshi
		</div>
		    <table>
        <tr>
            <td>ajax提取数据：</td>
        </tr>
        <tr><td><div id="data">原始数据</div></td></tr>
        <tr><td><input type="text" name="username" id="userid"/></td></tr>
        <tr><td><input type="button" value="submit" onclick="test()"/></td></tr>
    </table>
	</div>
</body>
</html>