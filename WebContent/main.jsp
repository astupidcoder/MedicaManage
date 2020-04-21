<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中药管理系统</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
$(function(){
	var treeData=[{
		id:1,
		text:"导航菜单",
		children:[{
			id:11,
			text:"药品基本信息管理",
			children:[
				{
					id:112,
					text:"查询药品",
					attributes:{
						url:"medica.jsp"
					}
				},{
				id:111,
				text:"添加药品",
				attributes:{
					url:"addMedica.jsp"
				}
			},
			]
	},{
		id:12,
		text:"账户管理",
		children:[{
			id:21,
			text:"个人信息",
			attributes:{
				url:"userInfo.jsp"
			}
		},{
			id:22,
			text:"重置密码",
			attributes:{
			
			}
		}]
	},{
		id:13,
		text:"退出系统",
		attributes:{
			
		}
	}]
}];
	
	$("#tree").tree(
			{
				data:treeData,
				lines:true,
				onClick:function(node){
					if(node.attributes.url){
						openTabs(node.text,node.attributes.url);
					}else if(node.id==13){
						logout();
					}else if(node.id=22){
						$("#dlg").dialog('open');
					}
				}
			}
	);
	
	function logout(){
		$.messager.confirm('系统提示',"确定要退出系统吗？",function(x){
			if(x){
				window.location.href="login.jsp";
			}
		});
	}
	
	function openTabs(text,url){
		if($("#tabs").tabs('exists',text)){
			$("#tabs").tabs('select',text);
		}else{
			var content="<iframe framborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
			$("#tabs").tabs('add',{
				title:text,
				closable:true,
				content:content
			});
		}
	}
	
});
function closeDialog(){
	$("#dlg").dialog("close");
}
function modifyPwd(){
	var p1=$("#p1").val();
	var p2=$("#p2").val();
	if(p1==""){
		$.messager.alert("系统提示","密码不能为空！！");
		return;
	}
		if(p1!=p2){
			$("#fm").form("clear");
			$.messager.alert("系统提示","两次密码输入不一致！！");
		}else{
			$.messager.confirm(
				"提示信息",
				"您确定要修改密码吗？？",
				function(r){
					if(r){
						$("#fm").form("submit",{
							url:"modifyPwd",
							onSubmit:function(param){
								param.id=${currentUser.userId};
							},
							success:function(result){
								var result=eval('('+result+')');
								if(result.success){
									$.messager.alert("系统提示","密码修改成功！！！");
									$("#fm").form("clear");
									closeDialog();
								}else{
									$.messager.alert("系统提示","密码修改失败！！！");
									$("#fm").form("clear");
								}
							}
						});
					}
				}
			);
		
		}
}


</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px" align=right>   
	<font color=red size=10>${currentUser.userName }</font><font size=10>,欢迎您</font>
	</div>
	<div data-options="region:'south'" style="height:50px">南</div>
	<div data-options="region:'west'" style="width:150px" title="导航栏" split="true">
	   <ul id="tree"></ul>
	</div>
	<div data-options="region:'center'">
       <div class="easyui-tabs" id="tabs" fit="true" >
         <div title="首页">
            <div style="padding-top:150px" align="center"><font size="20" color="red">欢迎使用</font></div> 
         </div>
       </div>
    </div>
    <div class="easyui-dialog" name="dlg" id="dlg" closed=true title="修改密码" style="width:500px;height:300px">
    <div align=center><br><br>
     <form id="fm" method="post">
    <input class="easyui-passwordbox" id="p1" name="pwdbox" prompt="请输入新密码..." style="width:50%;"><br><br>
    <input class="easyui-passwordbox" id="p2" prompt="输入确认密码..." style="width:50%;"><br><br>
    <a class="easyui-linkbutton" onClick="modifyPwd()">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a class="easyui-linkbutton" onClick="closeDialog()">返回</a>
     </form> 
    </div>
  </div>
</body>

</html>