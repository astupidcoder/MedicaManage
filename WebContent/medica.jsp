<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.sql.*"
    import="com.ysu.dao.MedicaDao" import="com.ysu.util.DbUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品信息</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
  $(function(){
	
  })
  
  function delMedica(){
	  var a=document.getElementsByName("ck");
	  var strIds=[];
	  for(var i=0;i<a.length;i++){
		 if(a[i].checked){
			 strIds.push(a[i].value);
		 } 
	  }
  if(strIds.length==0){
	  $.messager.alert("系统提示","请选择一项进行删除!!");
	  return;
  }
     $.messager.confirm("系统提示","您确定删除所选中的<font color=red>"+strIds.length+"</font>条数据吗？",function(r){
		  if(r){
			  var ids=strIds.join(",");
			  $.post("delMedica",{delIds:ids},function(rs){
				  if(rs.success){
					  $.messager.alert({
						  title:"系统提示",
						  msg:"删除成功！！",
						  fn:function(){
							  window.location.href='medica.jsp';
						  }  
					  });
				  }else{
					  $.messager.alert("系统提示","对不起，删除失败!!");
				  }
			  },"json")
		  }else{
			  return;
		  }
	  })
  }
  
  
  function modifyMedica(){
	  var a=document.getElementsByName("ck");
	  var strIds=[];
	  for(var i=0;i<a.length;i++){
			 if(a[i].checked){
				 strIds.push(a[i].value);
			 } 
		  }
	  if(strIds.length>1){
		  $.messager.alert("系统提示","对不起，您只能选择一项进行修改!!");
	  }else if(strIds.length==0){
		  $.messager.alert("系统提示","请选择一项进行修改!!");
	  }else{
		  window.location.href="modifyMedica.jsp?modifyId="+strIds;
	  }
  }
  
  
  
  
  function searchMedica(){
	   $("#tb2 tr").empty(""); 
	  $("#tb").hide();
	  $.post("searchMedica",{medicalName:$('#s_MedicaName').val()},function(r){
		  
		  var tb=document.getElementById("tb");
		  var tb2=document.getElementById("tb2");
		  var dataObj=eval("("+r+")");
			  var r=tb2.insertRow();
			  var c0=r.insertCell();
			  var c1=r.insertCell();
			 var c2=r.insertCell();
			 var c3=r.insertCell();
			 var c4=r.insertCell();
			 var c5=r.insertCell();
			 var c6=r.insertCell();
			 var c7=r.insertCell();
			 c0.innerHTML="";
			c1.innerHTML="药品编号";
			c2.innerHTML="名称";
			c3.innerHTML="所属类别";
			c4.innerHTML="价格";
			c5.innerHTML="库存";
			c6.innerHTML="生产日期";
			c7.innerHTML="生产";
	   for(var i=0;i<dataObj.result.length;i++){
			 var m=dataObj.result[i];
			 var r=tb2.insertRow();
			 var c0=r.insertCell();
			 var c1=r.insertCell();
			 var c2=r.insertCell();
			 var c3=r.insertCell();
			 var c4=r.insertCell();
			 var c5=r.insertCell();
			 var c6=r.insertCell();
			 var c7=r.insertCell();
			 c0.innerHTML='<input type=checkbox name=ck value='+m.medicaId+'>';
			c1.innerHTML=m.medicaId;
			c2.innerHTML=m.name;
			c3.innerHTML=m.className;
			c4.innerHTML=m.price;
			c5.innerHTML=m.volume;
			c6.innerHTML=m.pdate;
			c7.innerHTML=m.producer;
		 } 
	  });
  }
  
  
 
</script>
</head>
<body>
	<div align=center>
	  <input class="easyui-textbox" name="s_MedicaName" id="s_MedicaName" data-options="iconCls:'icon-search'"  prompt='请输入药品名称...'/>
	 <button class="easyui-linkbutton" name="searchBtn" id="searchBtn" onClick="searchMedica()">查询</button>&nbsp;&nbsp;
	 <button class="easyui-linkbutton" name="delBtn" id="delBtn" onClick="delMedica()">删除选中药品</button>&nbsp;&nbsp;
	 <button class="easyui-linkbutton" name="modifyBtn" id="modifyBtn" onClick="modifyMedica()">修改</button>
	 <br>
	</div>
	<table id="tb" width='1000px' border='1' align=center>
    <tr><th></th><th>药品编号</th><th>名称</th><th>所属类别</th><th>价格</th><th>库存</th><th>生产日期</th><th>生产商</th></tr>
<%
 DbUtil dbUtil=new DbUtil();
Connection con=null;
try{
	con=dbUtil.getCon();
}catch(Exception e){
	e.printStackTrace();
}
MedicaDao medicaDao=new MedicaDao();
ResultSet rs=medicaDao.medicaList(con,null,0);
while(rs.next()){
	out.println("<tr><td align=center><input type='checkbox' name=ck value='"+rs.getInt(1)+"'</td><td align=center>"+rs.getInt(1)+"</td><td align=center>"+rs.getString(2)+"</td><td align=center>"+rs.getString("className")+"</td><td align=center>"+rs.getDouble(4)+"</td><td align=center>"+rs.getFloat(5)+"</td><td align=center>"+rs.getDate(6)+"</td><td align=center>"+rs.getString(7)+"</td>");
}
	rs.close();
  dbUtil.closeCon(con);


%>
</table>
<table id="tb2" width='1000px' border='1' align=center></table>
</body>
</html>