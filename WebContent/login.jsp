<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ysu.model.User"  import="java.io.*"%>
<%
if(request.getAttribute("user")==null){
	Cookie[] cookies=request.getCookies();
	String userName="";
	String password="";
	for(int i=0;cookies!=null&&i<cookies.length;i++){
		if(cookies[i].getName().equals("user")){
			userName=cookies[i].getValue().split("-")[1];
			password=cookies[i].getValue().split("-")[2];
			pageContext.setAttribute("user",new User(userName,password));
			
		}
	}
}
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/jquery.slider.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.slider.css" />

</head>
<body>
<h1 align="center">中药进销存管理系统</h1>
<div align="center" style="padding-top:50px">
<form name="myForm" action="login" method="post" class="form-signin">
<table>
<tr><td>用户名:<input type="text" name="userName" id="userName" class="form-control" value="${user.userName }" placeholder="请输入用户名"/><td></tr>
<input type="hidden" id="hd" name="hd" value="">
<tr><td>&nbsp;密码:&nbsp;&nbsp;&nbsp;<input type="password" name="password" id="password" class="form-control" value="${user.pwd }" placeholder="请输入密码……"/></td></tr>
<tr><td>滑块验证:
<div class="demo1">
		<div id="slider1" class="slider"></div>
		<div class="result">验证结果：<span id="result1"></span></div>
	</div>
<script>
$("#slider1").slider({
	callback: function(result) {
		$("#result1").text(result);
		if(result){
			$("#hd").val("1");
		}else{
			$("#hd").val("0");
		}
		}
	
});
</script>
</td></tr>
<tr><td><font color="red">${error }</font></td></tr>
<tr><td align="center">&nbsp;&nbsp;&nbsp;<input type="submit" name="submit" value="登陆" class="btn btn-primary"/></td></tr>
</table>
</form>
</div>
</body>
</html>