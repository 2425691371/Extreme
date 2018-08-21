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
		<from>
			<table border="1" width="600px" style="color:green" align="center">
				<tr align="center">
					<td colspan="3">
						<span>
							<input type="button" value="返回信息表" onclick="test()" />	
							<input type="button" value="返回注册页面" onclick="test1()" />	
						</span>
					</td>
				</tr>	
				<tr>
					<td>姓名</td>
					<td>性别</td>
					<td rowspan="2"><img src="${reside.photo }"  width="500px"/></td>
				</tr>
				<tr>
					<td >${reside.name }</td>
					<td>${reside.gender }</td>
				</tr>	
			</table>		
		</from>
	</body>
	<script>
		function test(){
			window.location="/Extreme/residentTest?mark=query","testOpen","width=1200px,higth=1200px";
		}
		function test1(){
			window.location="/Extreme/house/addHouse.jsp","test1Open","width=1200px,higth=1200px";
		}
		
		
		
</script>
</html>
