<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String msg=request.getParameter("msg");
	if(msg!=null){
		request.setAttribute("msg", "<font color='red'>编号已存在</font>");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<base href="<%=basePath%>">
	</head>
	<body>
	
		<form action="applyServlet?mark=add" method="post" onsubmit="return checkID()" >
			<table align="center" width="800px" border="1px solid black"  >
				<tr align="center">
					<td colspan="2">
						<h1>设备添加</h1>
					</td>
				</tr>
				<tr>
					<td>设备编号:</td>
					<td>
						<input type="text" name="Aid" id="Aid" />
						<span id="spanId"></span>
					</td>
				</tr>
				<tr>
					<td>设备名称：</td>
					<td>
						<input type="text" name="Aname"/>
					</td>
				</tr>
				<tr>
					<td>制造商：</td>
					<td>
						<input type="text" name="maker"/>					
					</td>
				</tr>
				<tr>
					<td>设备购价：</td>
					<td>
						<input type="text" name="price"/>
					</td>
				</tr>
				<tr>
					<td>设备状态：</td>
					<td>
						<select name="AppState">
							<option selected>可用</option>
							<option >不可用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>设备类型：</td>
					<td>
						<select name="AppType">
							<option selected>固定资产</option>
							<option >可移动资产</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>启用时间</td>
					<td>
						<input type="text" name="UseTime"/>
					</td>
				</tr>
				<tr>
					<td>采购人员：</td>
					<td>
						<input type="text" name="BuyUser"/>
					</td>
				</tr>
				<tr>
					<td>维修人员</td>
					<td>
						<input type="text" name="FixUser"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="添加"/>
					</td>
					<td>
						<input type="reset" value="重置"/>
					</td>
				</tr>
		</table>
		</form>
	</body>
</html>
<script src="util/util.js"></script>
<script src="util/ajaxUtil.js"></script>
<script>
	var id=document.getElementById("Aid");
	function checkID(){
		var i=id.value;
		var i1=Number(i);
		var i2=String(i1);
		if(i===i2){
			return true;
		}else{
			return false;
		}
	}
</script>