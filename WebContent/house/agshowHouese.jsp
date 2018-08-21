<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<th>住户列表</th>
		<table border="1" style="color:green" align="center" width="600px">
			<tr>
				<td>序号</td>
				<td>住户名</td>
				<td>年龄</td>
				<td>身份证号</td>
			</tr>
			<c:forEach items="${empList }" var="emp">
			<tr>
				<td>${emp.idNum }</td>
				<td>${emp.name }</td>
				<td>${emp.age }</td>
				<td>${emp.idCard }</td>
			</tr>
			</c:forEach>
			<tr>
    		<td colspan="4" align="center">
    			<jsp:include page="/pageUtil.jsp">
    				<jsp:param value="residentTest?mark=queryByAge&ageArea=${param.ageArea }&count=${param.count }" name="url"/>
    			</jsp:include>
    		</td>
		</table>
	</body>
</html>