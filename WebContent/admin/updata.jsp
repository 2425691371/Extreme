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
		<form action="adminServlet?mark=updata" method="post" onsubmit="verify4()" >
		<input type="hidden" name="courpage" value="${xxxpage }"/>
		<table width="100%" border='1px'>
		<tr>
		<td align="right">编号:</td>
		<td ><input type='text' name = 'uid' value = "${admin.uid}" readonly /></td>
		</tr>
		<tr>
		<tr>
		<td align="right">账号:</td>
		<td ><input type='text' name = 'uname' value = "${admin.uname }" onblur="verify(this.value)" /><font color="red" id='font1'></font><font color="green" id='font11'></font></td>
		</tr>
		<tr>
		<td align="right">手机号码:</td>
		<td ><input type='text' name = 'phone' value = "${admin.phone }" onblur="verify2(this.value)" /><font color="red" id='font4'></font><font color="green" id='font44'></font></td>
		</tr>
		<tr>
		<td align="right">电子邮箱:</td>
		<td ><input type='text' name = 'emal' value = "${admin.emal }" onblur="verify3(this.value)" /><font color="red" id='font5'></font><font color="green" id='font55'></font></td>
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
	<script>
	function verify(value1){
		var font1 = document.getElementById("font1");
		var font11 = document.getElementById("font11");
		var regexe = /[a-zA-Z0-9_]{1,}/ig;
		if(!regexe.test(value2)){
			font1.innerHTML="账号格式不正确";
		}else{
			font1.innerHTML="";
			  //创建自己的请求对象
		    var request;
		    if(window.XMLHttpRequest){
		        request=new XMLHttpRequest();
		    }else if(window.ActiveXObject){
		        request=new ActiveXObject("Msxml2.XMLHTTP");
		    }
		    //访问服务器
		    request.open("post","adminServlet",true);
		    //监听响应的状态
		    request.onreadystatechange=function(){
		        var state=request.readyState;
		        if(state==4){
		            //4表示数据已经接受完成
		            var status=request.status;
		            if(status==200){
		                //200表示一切正常
		                //接受服务器响应的数据
		                var result=request.responseText;
		                if(result==="success"){
		            		font1.innerHTML="此用户已存在";
		            		font11.innerHTML="";
		            		}else{
		            		font1.innerHTML="";
		            		font11.innerHTML="恭喜此用户可以使用";
		            }
		        }
		    }
			}
			request.setRequestHeader("content-type","application/x-www-form-urlencoded");
			request.send("mark=verify&uname="+value1);
			}
		
	}

		
		function verify2(value2){
			var font2 = document.getElementById("font4");
			var font3 = document.getElementById("font44");
				var pwd = document.getElementById("phone");
				var regexe = /1[0-9]{10}/ig;
				if(!regexe.test(value2)){
					font2.innerHTML="手机号格式不正确";
					font3.innerHTML="";
				}else{
					font2.innerHTML="";
					font3.innerHTML="√";
					}
			}
		function verify3(value2){
			var font2 = document.getElementById("font5");
			var font3 = document.getElementById("font55");
				var pwd = document.getElementById("emal");
				var regexe = /^\w+@[a-z0-9]{1,10}(-[0-9a-z]{1,10})?[.](com|cn|org|net)(\.cn)?$/ig;
				if(!regexe.test(value2)){
					font2.innerHTML="邮箱格式不正确";
					font3.innerHTML="";
				}else{
					font2.innerHTML="";
					font3.innerHTML="√";
					}
			}
		function verify4(){
			var font1 = document.getElementById("font1");
			var font4 = document.getElementById("font4");
			var font5 = document.getElementById("font5");
			var msg = document.getElementById("fonttop");
			var arr = [font1,font4,font5];
				for(var i=0;i<arr.length;i++){
					if(!arr[i].innerHTML){
						msg.innerHTML="请输入完整信息"
						return false;
					}
				}
				return true
			}
		</script>
</html>