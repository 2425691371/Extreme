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
	<body >
	<h2 align="center">修改页面</h2>
	<form action="applyServlet?mark=updateApp" method="post" style='border: 1px solid black'>
		<table align="center" border="1px solid black" width="800px">
			<tr>
				<td>设备编号:</td>
				<td>
					<input type="text" name="Aid" value="${app.aid }"  /><%--  value="${appresult.Aid }" --%> 
				</td>
			</tr>
			<tr>
				<td>设备名称：</td>
				<td>
					<input type="text" name="Aname" value="${app.aname }"/>
				</td>
			</tr>
			<tr>
				<td>制造商：</td>
				<td>
					<input type="text" name="maker" value="${app.maker }" />					
				</td>
			</tr>
			<tr>
				<td>设备购价：</td>
				<td>
					<input type="text" name="price" value="${app.price }"/>
				</td>
			</tr>
			<tr>
				<td>设备状态：</td>
				<td>
					<select name="AppState">
						<option >可用</option>
						<option >不可用</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>设备类型：</td>
				<td>
					<select name="AppType">
						<option >固定资产</option>
						<option >可移动资产</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>启用时间</td>
				<td>
					<input type="text" name="UseTime" value="${app.useTime }" />
				</td>
			</tr>
			<tr>
				<td>采购人员：</td>
				<td>
					<input type="text" name="BuyUser" value="${app.buyUser }"/>
				</td>
			</tr>
			<tr>
				<td>维修人员</td>
				<td>
					<input type="text" name="FixUser" value="${app.fixUser }"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="确认修改"/>
				</td>
			</tr>
	</table>
	</form>
	</body>
</html>