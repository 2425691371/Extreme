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
  <form action="/Extreme/employeeServelet?mark=updateTime" method="post" >
  	<table border="1px" align="center">
  		<tr>
  			<td>员工号:</td>
  			<td><input type="text" name="seNumber" value="${work.seNumber}"  readonly/></td>
  		</tr>
  		<tr>
  			<td>姓名:</td>
  			<td><input type="text" name="ename" value="${work.ename}"/></td>
  		</tr>
  		<tr>
  			<td>工作月份:</td>
  			<td><input type="text" name="mouth" value="${work.mouth}"/></td>
  		</tr>
  		<tr>
  			<td>应该工作天数:</td>
  			<td><input type="text" name="workTime" value="${work.workTime}"/></td>
  		</tr>
  		<tr>
  			<td>实际工作天数:</td>
  			<td><input type="text" name="rworkTime" value="${work.rworkTime}"/></td>
  		</tr>
  		<tr>
  			<td>加班天数:</td>
  			<td><input type="text" name="overTime" value="${work.overTime}" /></td>
  		</tr>
  		<tr>
  			<td>迟到早退次数:</td>
  			<td><input type="text" name="late" value="${work.late}" /></td>
  		</tr>
  		<tr>
  			<td>请假天数:</td>
  			<td><input type="text" name="qingjia" value="${work.qingjia}"/></td>
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
