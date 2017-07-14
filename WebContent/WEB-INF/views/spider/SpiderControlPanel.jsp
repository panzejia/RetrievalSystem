<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.iflin.server.AdminOperating"%>
    <%@ page import="cn.iflin.spider.server.SpiderMysql"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"><title>课题部落 -- 测试版--爬虫控制面板</title>
<link rel="stylesheet" type="text/css" href="css/spider/spiderpanel.css" />
<script language="JavaScript" type="text/JavaScript" src="js/jquery-1.6.1.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
function getRight(){
    var url = "spiderpanelright";
    sendRequest(url);
}   
function getTaskName(taskId){
    var url = "getSpiderInfo?taskId="+taskId;
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
    XMLHttpReq.open("GET",url, true);
    XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
    XMLHttpReq.send(null);  // 发送请求
}
// 处理返回信息函数
function processResponse() {
    if (XMLHttpReq.readyState == 4) { // 判断对象状态
        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            var result = XMLHttpReq.responseText;    
            document.getElementById("result").innerHTML = result;     
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function doPreview(url,starttag,stoptag){ 
	createXMLHttpRequest();
	var url =$(url).val();
	if(starttag=="0"){
		url = "spiderView?url="+url+"&start=0&stop=0";
		window.open(url, '_blank'); 
		return;
	}else{
		var start =$(starttag).val();
		var stop =$(stoptag).val();
		url = "spiderView?url="+url+"&start="+start+"&stop="+stop;
		window.open(url, '_blank');
	}
}
function doListPreview(url,starttag,stoptag,firstUrl){ 
	createXMLHttpRequest();
	var url =$(url).val();
	var start =$(starttag).val();
	var stop =$(stoptag).val();
	var firstUrl =$(firstUrl).val();
	url = "spiderListUrlView?url="+url+"&start="+start+"&stop="+stop+"&firstUrl="+firstUrl;
	window.open(url, '_blank');
}
function doTimePreview(url,starttag,stoptag){ 
	createXMLHttpRequest();
	var url =$(url).val();
	var start =$(starttag).val();
	var stop =$(stoptag).val();
	url = "spiderTimeView?url="+url+"&start="+start+"&stop="+stop;
	window.open(url, '_blank');
}
function doSave(){ 
	var taskName =$("#taskName").val();
	var sourceName=$("#sourceName").val();
	var liurl =$("#liurl").val();
	var liststarttag =$("#liststarttag").val();
	var listoptag =$("#listoptag").val();
	var firstUrl =$("#firstUrl").val();
	var titlestarttag =$("#titlestarttag").val();
	var titlestoptag =$("#titlestoptag").val();
	var contentstarttag =$("#contentstarttag").val();
	var contentstoptag =$("#contentstoptag").val();
	var url = url = "spiderSave?taskName="+taskName+"&sourceName="+sourceName+"&liUrl="+liurl+
	"&liststartTag="+liststarttag+"&listopTag="+listoptag+"&firstUrl="+firstUrl
	+"&titlestartTag="+titlestarttag+"&titlestopTag="+titlestoptag+"&contentstartTag="+contentstarttag
	+"&contentstopTag="+contentstoptag;
	sendRequest(url);
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
		<div class="resultleft" id="user">
			<div class="resultlefttop">
				<a href="javascript:void(0)" onclick="getRight();">添加任务</a>
				<a href="#">任务设置</a>
			</div>
			<ul  style="list-style-type:none;">
			<c:forEach items="${spiders}" var="spider">
				<li><a href="javascript:void(0)"onclick="getTaskName('${spider.getTaskId()}')">${spider.getTaskName()}</a></li>
			</c:forEach>
			</ul>
		</div>
		<div class="resultright" id = "result" >
		</div>
	</div>
</body>
</html>