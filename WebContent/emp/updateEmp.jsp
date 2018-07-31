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
  <form action="/Extreme/employeeServelet?mark=updateEmp" method="post" enctype="multipart/form-data">
  	<table border="1px" align="center">
  		<tr>
  			<td>员工号:</td>
  			<td><input type="text" name="seNumber" value="${emp.seNumber}"  readonly/></td>
  			<td rowspan="12" ><img src="${emp.photoPath}" width="200" height="300"></td>
  		</tr>
  		<tr>
  			<td>姓名:</td>
  			<td><input type="text" name="ename" value="${emp.ename}"/></td>
  		</tr>
  		<tr>
  			<td>雇员编号:</td>
  			<td><input type="text" name="empNum" value="${emp.empNum}"/></td>
  		</tr>
  		<tr>
  			<td>性别:</td>
  			<td>
  				男<input type="radio" name="gender" checked="${emp.gender==1?true:false}" value="1"/>
  				女<input type="radio" name="gender" checked="${emp.gender==0?true:false}" value="0"/>
  			</td>
  		</tr>
  		<tr>
  			<td>年龄:</td>
  			<td><input type="text" name="age" value="${emp.age}"/></td>
  		</tr>
  		<tr>
  			<td>身份证号:</td>
  			<td><input type="text" name="perId" value="${emp.perId}" readonly/></td>
  		</tr>
  		<tr>
  			<td>学历:</td>
  			<td>
  				<select name="education" id="sel">
  					<option value="1" select="${emp.gender==1?true:false}">本科</option>
  					<option value="2" select="${emp.gender==2?true:false}">研究生</option>
  					<option value="3" select="${emp.gender==3?true:false}">博士</option>
  				</select>
  			</td>
  		</tr>
  		<tr>
  			<td>邮箱:</td>
  			<td><input type="text" name="mail" value="${emp.mail}"/></td>
  		</tr>
  		<tr>
  			<td>入职时间:</td>
  			<td><input type="text" name="hireDate" onfocus="WdatePicker()" class="Wdate" value="${emp.hireDate}"/></td>
  		</tr>
  		<tr>
  			<td>薪水:</td>
  			<td><input type="text" name="salary" value="${emp.salary}"/></td>
  		</tr>
  		<tr>
  			<td>手机号:</td>
  			<td><input type="text" name="phone" value="${emp.phone}"/></td>
  		</tr>
  		<tr>
  			<td>部门名:</td>
  			<td><input type="text" name="depName" value="${emp.depName}"/></td>
  		</tr>
  		<tr>
  			<td>上传修改头像:</td>
  			<td><input type="file" name="photoName" /></td>
  		</tr>
  		<tr colspan=2>
  			<td>
  				<input type="reset" value="重置" />
  				<input type="submit" value="确认修改"/>
  			</td>
  		</tr>
  	</table>
  </form>
  </body>
</html>
