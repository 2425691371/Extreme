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
	<body > 
		<table  align="center" border="1px solid black" width="800px">
			<tr>
				<td>
					按设备名称查询：
					<input type="text" name="ApplyName" id="AName"/>
					<span id="names"></span>
				</td>
				<td>
					按编号查询：
					<input type="text" name="Aid" id="AID" /> 
					<span id="ids"></span>
					
				</td>
				<td>
					<input type="button" value="查询" onclick="querySome()"/>
				</td>
			</tr>
			<tr >
				<td>
					<h3>设备列表</h3>
					<%-- <h6>${msg }</h6> --%>
				</td>
				<td colspan="3" align="center" >
					<input type="button" value="全选" onclick="chooseAll()"/>
					<input type="button" value="全不选" onclick="chooseNo()"/>
					<input type="button" value="反选" onclick="chooseOther()"/>
					<input type="button" value="选择删除" onclick="delChoose()"/>
					<input type="button" value="选中修改" onclick="updateIt()"/>
				</td>
			</tr>
			
		</table>
		 <table  align="center" border="1px solid black" width="800px" id="con">
				<tr>
					<td>
						选择
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
				<jsp:include page="pageUtil.jsp">
					<jsp:param value="applyServlet?mark=query" name="url"/>
				</jsp:include>
				 </td>
			 </tr>
	        <tr>
	        	 <td colspan="4">共${page.count }条纪录，当前第${page.curPage }/${page.lastPage}页，每页最多10条纪录</td>
	        </tr>
		</table>
	            <%-- <td colspan="4">
	            <c:if test="${page.curPage!=page.firstPage }">
	            	<a href='applyServlet?mark=query&curPage=${page.firstPage }'>首页</a>
	            	<a href='applyServlet?mark=query&curPage=${page.prevPage }'>上一页</a>
	            </c:if>
	            	<c:forEach begin="${page.startNav }" end="${page.endNav }" var="i">
	            	<c:choose>
	            		<c:when test="${page.curPage==i }">
	            				<font color='red'>${i }</font>
	            		</c:when>
	            		<c:otherwise>
	            			<a href="applyServlet?mark=query&curPage=${i }">${i }</a>
	            		</c:otherwise>
	            	</c:choose>
	            	</c:forEach>
	            	<c:if test="${page.curPage!=page.lastPage }">
	            	<a href='applyServlet?mark=query&curPage=${page.nextPage }'>下一页</a>
	            	<a href='applyServlet?mark=query&curPage=${page.lastPage }'>尾页</a>
	            </c:if>
	            </td> --%>
	    
	</body>
</html>
<script src="util/util.js"></script>
<script src="util/ajaxUtil.js"></script>
<script>
	var tab=document.getElementById("con");
	var cbs=document.getElementsByName("cb");
	var AId=document.getElementById("AID");
	var AName=document.getElementById("AName");
	var ids=document.getElementById("ids");
	//条件查询
	function querySome(){
		 var aname=AName.value;
		var i=AID.value;
		var i1=Number(i);
		var i2=String(i1);
		if(i===i2){
			//走ajax
			for(var i=1;i<tab.rows.length;i++){
				tab.deleteRow(i);
				i--;
			}
			ajax("get","applyServlet","mark=querySome&AID="+AID.value+"&AName="+AName.value,function(data){
				eval("var appArr="+data);
				for(var i =0;i<appArr.length;i++){
					var newTr=tab.insertRow();
					newTr.insertCell(0).innerHTML="<input type='checkbox' name='cb'/>";
					newTr.insertCell(1).innerHTML=appArr[i].aid;
					newTr.insertCell(2).innerHTML=appArr[i].aname;
					newTr.insertCell(3).innerHTML=appArr[i].useTime;
				}
			});
			
		}else{
			ids.innerHTML="<font color='red'>编号不正确</font>";
		} 
	}
	function delChoose(){
		for(var i=0;i<cbs.length;i++){
			if(cbs[i].checked){
				var TrIndex=(cbs[i].parentNode.parentNode).rowIndex;
				var Val=getNextSibling(cbs[i].parentNode).innerHTML;
				ajax("get","applyServlet","mark=delApp&Val="+Val);
				tab.deleteRow(TrIndex);
				i--;
			}
		}
	}
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
	function updateIt(){
		for(var i=0;i<cbs.length;i++){
			if(cbs[i].checked){
				var Aid=cbs[i].parentNode.parentNode.cells[1].innerHTML;
				 window.open("applyServlet?mark=updateIt&Aid="+Aid,"newwin","width=850,height=500");
			}
		}
	}
</script>