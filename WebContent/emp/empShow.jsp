<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

-->
</style>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="/Extreme/emp/images/tab_03.gif" width="15" height="30" /></td>
        <td background="/Extreme/emp/images/tab_05.gif"><img src="/Extreme/emp/images/311.gif" width="16" height="16" /> <span class="STYLE4">员工信息表</span></td>
        <td width="14"><img src="/Extreme/emp/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
        <td align="center">
       			按员工姓名查找<input type="text" /><input type="button" value="查找" onclick="queryName(this)"/>&nbsp&nbsp&nbsp&nbsp按部门查找<input type="text" /><input type="button" value="查找" onclick="queryDepName(this)"/>
        </td>
  </tr>
  <tr>
        <td align="center">
        	<a href="#" onclick="addEmp()">[添加]</a><a href="#" onclick="selectAll()">&nbsp[全选]</a><a href="#" onclick="changSelect()">&nbsp[反选]</a><a href="#" onclick="delectAll()">&nbsp[删除选中项]</a><a href="/Extreme/employeeServelet?mark=downExcel&fileName=worker" >[导出Excel]</a>
        </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="/Extreme/emp/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
            <td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">选择</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">姓名</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">员工号</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">电话</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">年龄</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">性别</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">学历</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">身份证</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">入职时间</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">薪水</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">所在部门</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2" >修改</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">删除</div></td>
          </tr>
          <c:forEach items="${empList }" var="emp">
    		<tr align="center">
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center"><input type="checkbox" name="cbs" /></td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.seNumber }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.ename }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.empNum }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.phone }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.age }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.gender==1?"男":"女" }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.education }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.perId }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.hireDate }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.salary }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${emp.depName }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center"><a href="#" onclick="updateEmp(this)">[编辑]</a></td>
          		<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center"><a href="#" onclick="delThis(this)">[删除]</a></td>
    		</tr>
    	</c:forEach>
        </table></td>
        <td width="9" background="/Extreme/emp/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="/Extreme/emp/images/tab_20.gif" width="15" height="29" /></td>
        <td background="/Extreme/emp/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">共${page.count}条纪录，当前第${page.curPage}/${page.endNav}页，每页10条纪录</span></div></td>
            <td width="60%" class="STYLE1">
            	<c:if test="${page.curPage!=page.firstPage }">
    				<a href="employeeServelet?mark=query&curPage=${page.firstPage }">首页</a>
    				<a href="employeeServelet?mark=query&curPage=${page.prevPage }">上一页</a>
    			</c:if>
    			<c:forEach begin="${page.startNav }" end="${page.endNav }" var="i">
    			 	<c:choose>
    			 		<c:when test="${i==page.curPage}">
    			 			<font color="red">${i }</font>
    			 		</c:when>
    			 		<c:otherwise>
    			 			<a href="employeeServelet?mark=query&curPage=${i }">${i }</a>
    			 		</c:otherwise>
    			 	</c:choose>
    			</c:forEach>
    			<c:if test="${page.curPage!=page.lastPage }">
    				<a href="employeeServelet?mark=query&curPage=${page.nextPage }">下一页</a>
    				<a href="employeeServelet?mark=query&curPage=${page.lastPage }">尾页</a>
    			</c:if>
            </td>
          </tr>
        </table></td>
        <td width="14"><img src="/Extreme/emp/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
<script src="/Extreme/util/util.js"></script>
<script type="/Extreme/util/ajaxUtil.js"></script>
<script>
	var cbs = document.getElementsByName("cbs");
	function selectAll() {
		for (var i = 0; i < cbs.length; i++) {
			if (!cbs[i].checked) {
				cbs[i].checked = true;
			}
		}
	}
	function changSelect() {
		for (var i = 0; i < cbs.length; i++) {
			if (!cbs[i].checked) {
				cbs[i].checked = true;
			} else {
				cbs[i].checked = false;
			}
		}
	}
	function delectAll() {
		var seNumbers=[];
		for (var i = 0; i < cbs.length; i++) {
			if (cbs[i].checked) {
				var seNumber = getChildNodes(cbs[i].parentNode.parentNode)[1].innerHTML;
				seNumbers.push(seNumber);
			}
				window.location="/Extreme/employeeServelet?mark=delEmp&seNumbers="+seNumbers;
		}
	}
	function delThis(obj){
		var seNumber=getChildNodes(obj.parentNode.parentNode)[1].innerHTML;
		window.location="/Extreme/employeeServelet?mark=delEmp&seNumber="+seNumber;
	}
	function addEmp(){
		window.open("/Extreme/emp/addEmp.jsp","添加员工","height=400,width=600");
	}
	function updateEmp(obj){
		var seNumber=getChildNodes(obj.parentNode.parentNode)[1].innerHTML;
		window.open("/Extreme/employeeServelet?mark=showEmp&seNumber="+seNumber,"添加员工","height=400,width=600");
	}
	function queryName(obj){
		var ename=getPreviousSibling(obj).value;
		if(ename==""){
			window.location="/Extreme/employeeServelet?mark=query";
		}else{
			window.open("/Extreme/employeeServelet?mark=queryName&ename="+ename,"添加员工");
		}
	}
	function queryDepName(obj){
		var depName=getPreviousSibling(obj).value;
		if(depName==""){
			window.location="/Extreme/employeeServelet?mark=query";
		}else{
			window.open("/Extreme/employeeServelet?mark=queryDepName&depName="+depName,"添加员工");
		}
	}
</script>
