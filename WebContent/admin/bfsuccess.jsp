
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
		<meta http-equiv="refresh" content="1;URL=adminServlet?mark=querrydata">
	</head>
	<body>
		<font style="font-size: 30px">恭喜您 ${mmm}已备份成功 即将跳转至备份列表 </font>
	</body>
</html>