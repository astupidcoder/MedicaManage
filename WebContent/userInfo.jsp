<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生个人信息</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>

</script>
</head>
<body>
  <div class="easyui-panel" title="个人信息" style="width:100% max-width:400px;padding:30px 60px" >
  <form name="fm" method="post" action="studentModify">
  <div style="margin-bottom:20px">
    <input class="easyui-textbox" id="name" name="name" style="width:30%" data-options="label:'姓名:'" value="${currentUser.userName }" editable="false"><br><br>
    <input class="easyui-textbox" id="email" name="email" style="width:30%" data-options="label:'邮箱:'" value="${currentUser.email }" editable="false"><br><br>
    <input class="easyui-textbox" id="tel" name="tel" style="width:30%" data-options="label:'电话:'" value="${currentUser.tel }" editable="false"><br><br><br>
   </div>
   </form>
  </div>
  
</body>
</html>