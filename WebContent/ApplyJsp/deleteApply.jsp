<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<body  onload="queryApply()" >
		<table align="center" border="1px solid black" width="800px" id='con'>
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="全选"  onclick="chooseAll()"/>
					<input type="button" value="全不选"  onclick="chooseNo()" />
					<input type="button" value="反选"   onclick="chooseOther()" />
					<input type="button" value="选择删除" onclick="delChoose()"/>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>设备编号</td>
				<td>设备名</td>
				<td>设备使用时间</td>
			</tr>
			 <c:forEach items="${appResult }" var="a">
				<tr>
					<td>
						<input type='checkbox' name='cb'/>
					</td>
					<td>${a.aid }</td>
					<td>${a.aname }</td>
					<td>${a.useTime }</td>
				</tr>
			</c:forEach> 
		</table>
		<table align="center" border="1px solid black" width="800px">  
			<tr>
				<td colspan="4">
				<%-- 	<c:forEach begin="${page.startNav }" end="${page.endNav }" var="i">
					<c:choose>
						<c:when test="${page.curPage==i }">
							<font color='red'>${i }</font>
						</c:when>
						<c:otherwise>
							<a href='applyServlet?mark=query&curPage=${i }'>${i }</a>
						</c:otherwise>	
					</c:choose>
					</c:forEach> --%>
					 <jsp:include page="pageUtil.jsp">
						<jsp:param value="applyServlet?mark=query" name="url"/>
					</jsp:include> 
				 </td>
			 </tr>
	        <tr>
	        	 <td colspan="4">共${page.count }条纪录，当前第${page.curPage }/${page.lastPage}页，每页最多10条纪录</td>
	        </tr>
		</table>
	</body>
</html>
<script src="util/util.js"></script>
<script src="util/ajaxUtil.js"></script>
<script>
	var tab=document.getElementById("con");
	var cbs=document.getElementsByName("cb");
	function chooseAll(){
		for(var i=0;i<cbs.length;i++){
			cbs[i].checked=true;
		}
	}
	function chooseNo(){
		for(var i=0;i<cbs.length;i++){
			cbs[i].checked=false;
		}
	}
	function chooseOther(){
		for(var i=0;i<cbs.length;i++){
			cbs[i].checked=!cbs[i].checked;
		}
	}
	function delChoose(){
		for(var i=0;i<cbs.length;i++){
			if(cbs[i].checked){
				var result=confirm("确定删除编号为"+getNextSibling(cbs[i].parentNode).innerHTML+"的设备吗?");
				if(result){
					var TrIndex=(cbs[i].parentNode.parentNode).rowIndex;
					var Val=getNextSibling(cbs[i].parentNode).innerHTML;
					ajax("get","applyServlet","mark=delApp&Val="+Val); 
					tab.deleteRow(TrIndex);
					i--;
				}
			}
		}
	}
function queryApply(){
	ajax("get","applyServlet","mark=queryDel",function(data){
		eval("var appArr="+data);
		for(var i =0;i<appArr.length;i++){
			var newTr=tab.insertRow();
			newTr.insertCell(0).innerHTML="<input type='checkbox' name='cb'/>";
			newTr.insertCell(1).innerHTML=appArr[i].aid;
			newTr.insertCell(2).innerHTML=appArr[i].aname;
			newTr.insertCell(3).innerHTML=appArr[i].useTime;
			newTr.insertCell(4).innerHTML=appArr[i].buyUser;
		}
	});
} 
</script>