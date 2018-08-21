
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<base href="<%=basePath%>">
	</head>
	<body>
		<font id='fonttop' color="red"></font>
		<form action="adminServlet?mark=upthispwd" method="post" onsubmit="verify4()" >
		<input type="hidden" id="hid" value="${admin.password }"/>
		<table width="100%" border='1px'>
		<tr>
		<td align="right">账号:</td>
		<td ><input type='text' name = 'uname' value = "${admin.uname}" readonly /></td>
		</tr>
		<tr>
		<td align="right">初始密码:</td>
		<td ><input type='text' id='pwd1'  onblur="verify(this.value)" /><font color="red" id='font1'></font><font color="green" id='font11'></font></td>
		</tr>
		<tr>
		<tr>
		<td align="right">新密码:</td>
		<td ><input type='text' name = 'pwd' id='pwd'/><font color="red" id='font2'></font><font color="green" id='font22'></font></td>
		</tr>
		<tr>
		<td align="right">重复密码:</td>
		<td ><input type='text'   onblur="verify1(this.value)" /><font color="red" id='font3'></font><font color="green" id='font33'></font></td>
		</tr>
		<tr>
		<td align="right"><input type='submit' value='修改' /></td>
		<td ><input type="reset" value='重置' /></font></td>
		</tr>
		</table>
		</form>
		  <a href="javascript:void(0);" onclick="javascript:history.go(-1);">放弃修改</a>
	</body>
	<script src="/Extreme/util/util.js"></script>
	<script src="/Extreme/util/ajaxUtil.js"></script>
	<script >
	function verify(value){
		var font2 = document.getElementById("font1");
		var font3 = document.getElementById("font11");
			var pwd111 = document.getElementById("hid");
			if(!(value===pwd111.value)){
				font2.innerHTML="初始密码不正确";
				font3.innerHTML="";
			}else{
				font2.innerHTML="";
				font3.innerHTML="√";
				}
		}
	function verify1(value2){
		var font2 = document.getElementById("font3");
		var font3 = document.getElementById("font33");
			var pwd = document.getElementById("pwd");
			if(!(value2===pwd.value)){
				font2.innerHTML="两次密码不一致";
				font3.innerHTML="";
			}else{
				font2.innerHTML="";
				font3.innerHTML="√";
				}
		}
	function verify4(){
		var font3 = document.getElementById("font3");
				if(!font3.innerHTML){
					msg.innerHTML="请输入完整信息"
					return false;
				}
			return true
		}
	</script>
</html>