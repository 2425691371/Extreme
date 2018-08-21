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
	<form action="residentTest?mark=deltId&box=${res.idNum }" method="post"  >
		<table style="border:1px solid green" align="center" width="1300px" >
				<tr align="center">
				<td>
					<input type="button" value="添加" onclick="addTest()">
					<input type="button" value="全选" onclick="choose()" />
					<input type="button" value="全不选" onclick="invert()"/>
					<input type="button" value="反选" onclick="fanxuan()" />
					<input type="submit" value="选择删除" />
					<a href="/Extreme/house/ageHousejsp.jsp">[年龄分布图]</a>
					<a href="/Extreme/house/studyHouse.jsp">[专业分析图]</a>
					<a href="residentTest?mark=downEmp">[合同导出]</a>
				</td>
				</tr>
			<tr>
				<td>
				<table border="1" style="color:green" align="center" width="1200px" id="table" >
				<tr>
					<th >选择</th>
					<th >序号</th>
					<th >住户名</th>
					<th >性别</th>
					<th >年龄</th>
					<th >IDCard</th>
					<th >学历</th>
					<th >Email</th>
					<th >栋-单元-房号</th>
					<th >电话</th>
					<th >合同开始日期</th>
					<th >合同结束日期</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
					<c:forEach items="${reside }" var="res">
				<tr>
					<td>
						<input type="checkbox" value="${res.idNum }" name="box" />
					</td>
					<td>${res.idNum }</td>
					<td><div style='cursor:pointer'><a href="residentTest?mark=pgshow&idNum=${res.idNum }">${res.name }</a></div></td>
					<td>${res.gender==1?"男":"女" }</td>
					<td>${res.age }</td>
					<td>${res.idCard }</td>
					<td>${res.study }</td>
					<td>${res.email }</td>
					<td>${res.room }</td>
					<td>${res.phone }</td>
					<td>${res.startdate }</td>   
					<td>${res.enddate }</td>
					<td><a href="residentTest?mark=alteId&idNum=${res.idNum }" >编辑</a></td>
					<td><a href="residentTest?mark=deltId&box=${res.idNum }">删除</a></td>
					
				</tr>
					</c:forEach>
			</table>
			<tr align="center">
				<td colspan="12">
					<jsp:include page="/pageUtil.jsp">
					<jsp:param value="residentTest?mark=query" name="url"/>
					</jsp:include>
				</td>
			</tr>
		</table>
		</form>
	</body>
	
	<script src="/Extreme/util/util.js"></script>
	<script >

			
		//添加按钮，引用前面已有的页面
		function addTest(){
			window.open("/Extreme/house/addHouse.jsp","newOpen","width=1200px,higth=1200px");
		}
		
		//全选全不选
		function choose(){
			var box=$("box");
			for(var i=0;i<box.length;i++){
					box[i].checked=true;
					
				}
			}
		
		//全不选
		function invert(){
			var box=$("box");
			for(var i=0;i<box.length;i++){
				box[i].checked=false;
			}
		}
		
		//反选
		function fanxuan(){
			var box=$("box");
				for(var i=0;i<box.length;i++){
					if(box[i].checked){
						box[i].checked=false;
					}else{
						box[i].checked=true;
					}
				}
			
 
		}
	</script>
</html>

