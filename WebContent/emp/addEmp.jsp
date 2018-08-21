<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addEmp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<script src="/Extreme/util/My97DatePicker/WdatePicker.js"></script>
  <body>
  <form action="/Extreme/employeeServelet?mark=add" method="post" enctype="multipart/form-data" >
  	<table border="1px" align="center">
  		<tr>
  			<td>姓名:</td>
  			<td><input type="text" name="ename" /></td>
  		</tr>
  		<tr>
  			<td>雇员编号:</td>
  			<td><input type="text" name="empNum" /></td>
  		</tr>
  		<tr>
  			<td>性别:</td>
  			<td>
  				男<input type="radio" name="gender" value="1" checked/>
  				女<input type="radio" name="gender" value="0"/>
  			</td>
  		</tr>
  		<tr>
  			<td>年龄:</td>
  			<td><input type="text" name="age" /></td>
  		</tr>
  		<tr>
  			<td>身份证号:</td>
  			<td><input type="text" name="perId" /></td>
  		</tr>
  		<tr>
  			<td>学历:</td>
  			<td>
  				<select name="education">
  					<option selected value="1">本科</option>
  					<option value="2">研究生</option>
  					<option value="3">博士</option>
  				</select>
  			</td>
  		</tr>
  		<tr>
  			<td>邮箱:</td>
  			<td><input type="text" name="mail" /></td>
  		</tr>
  		<tr>
  			<td>入职时间:</td>
  			<td><input type="text" name="hireDate" onfocus="WdatePicker()" class="Wdate" /></td>
  		</tr>
  		<tr>
  			<td>薪水:</td>
  			<td><input type="text" name="salary" /></td>
  		</tr>
  		<tr>
  			<td>手机号:</td>
  			<td><input type="text" name="phone" /></td>
  		</tr>
  		<tr>
  			<td>部门名:</td>
  			<td><input type="text" name="depName" /></td>
  		</tr>
  		<tr>
  			<td>上传头像:</td>
  			<td><input type="file" name="photoName" /></td>
  		</tr>
  		<tr colspan=2>
  			<td>
  				<input type="reset" value="重置"/>
  				<input type="submit" value="添加员工"/>
  			</td>
  		</tr>
  	</table>
  </form>
  </body>
</html>
