<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>添加缴费项目信息</h4>
	${msg }
	<form action="/Extreme/xmServlet?mark=add" method="post">
		<table style="border-collapse:collapse" border="1" bordercolor="green" width="500px" id="tdVal">
			<tr>
				<td align="right">缴费类别：</td>
				<td><input type="text" name="pname"/></td>
			</tr>
			<tr>
				<td align="right">单价：</td>
				<td><input type="text" name="unitp"/></td>
			</tr>
			<tr>
				<td align="right">单位：</td>
				<td><input type="text" name="unit"/></td>
			</tr>
			<tr>
				<td align="right">是否代收：</td>
				<td><select name="ds">
					 	<option value="1">代收</option>
					 	<option value="0">不代收</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="添加"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
