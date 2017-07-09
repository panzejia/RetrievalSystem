<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息</title>
<script language="JavaScript" type="text/JavaScript" src="js/jquery-1.6.1.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.validate.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.metadata.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/messages_cn.js"></script>
<script>
$().ready(function() {
    $("#login").validate({
    	rules:{    		
    		email:{
    			required:true,
    			email:true
    		},
    		phone:{
    			required:true,
    			minlength:11
    		},
    		password:{
    			required:true,
    			minlength:6
    		},
    		repeatpassword:{
    			equalTo:"#password"
    			
    		}
    	},
    	messages:{   		
    		email:{
    			required:" * 邮箱地址不能为空！"
    		},
    		phone:{
    			required:" * 手机号码不能为空！",
    			minlength: $.format(" * 手机号长度不得小于11位！")
    		},
    		password:{
    			required:" * 用户密码不能为空！",
    			minlength: $.format(" * 密码长度不得小于6位！")
    		},
    		repeatpassword:{
    			equalTo:" * 两次密码输入不一致！"
    		}
    	}
    	
    	
    });
});

</script>
<style>
.error{
	color:red;
}
</style>
</head>
<body>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
<h1>修改个人信息</h1>
</div>
</div>
<hr>
<form name="regForm" id="login" action="personalchange" method="post">
<table width="550" border="0" align="center">
<tr>
<td>用户名: </td>
<td><%=session.getAttribute("username") %></td>
</tr>
<tr>
<td>电子邮箱: </td>
<td><input type="email" id="email" name="email" style="width:150px" /></td>
</tr>
<tr>
<td>手机号码: </td>
<td><input type="text" id="phone" name="phone" style="width:150px"/></td>
</tr>
<tr>
<td>密码: </td>
<td><input type="password" id="password" name="password" style="width:150px"/></td>
</tr>
<tr>
<td>确认密码: </td>
<td><input type="password" id="repeatpassword" name="repeatpassword" style="width:150px"/></td>
</tr>
	<tr>
	<td colspan="2" align="center"><input type="submit" value="确认"/></td>
	</tr>
</table>
</form>
<a href="personal"><button>返回</button></a>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
</div>
</div>
</body>
</html>
