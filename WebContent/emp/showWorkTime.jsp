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
        <td background="/Extreme/emp/images/tab_05.gif"><img src="/Extreme/emp/images/311.gif" width="16" height="16" /> <span class="STYLE4">考勤信息表</span></td>
        <td width="14"><img src="/Extreme/emp/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="/Extreme/emp/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">编号</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">姓名</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">工作月份</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">应出勤天数</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">实际出勤天数</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">加班总天数</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">迟到早退总次数</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">请假总天数</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">添加</div></td>
            <td width="7%" height="18" background="/Extreme/emp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">编辑</div></td>
          </tr>
          <c:forEach items="${workTimeList }" var="work">
    		<tr align="center">
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.seNumber }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.ename }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.mouth }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.workTime }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.rworkTime }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.overTime }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.late }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center">${work.qingjia }</td>
    			<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center"><a href="#" onclick="updateWork(this)">[加班或请假]</a></td>
          		<td width="7%" height="26" background="/Extreme/emp/images/tab_14.gif" class="STYLE1" align="center"><a href="#" onclick="updateWork(this)">[编辑]</a></td>
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
    				<a href="employeeServelet?mark=queryTime&curPage=${page.firstPage }">首页</a>
    				<a href="employeeServelet?mark=queryTime&curPage=${page.prevPage }">上一页</a>
    			</c:if>
    			<c:forEach begin="${page.startNav }" end="${page.endNav }" var="i">
    			 	<c:choose>
    			 		<c:when test="${i==page.curPage}">
    			 			<font color="red">${i }</font>
    			 		</c:when>
    			 		<c:otherwise>
    			 			<a href="employeeServelet?mark=queryTime&curPage=${i }">${i }</a>
    			 		</c:otherwise>
    			 	</c:choose>
    			</c:forEach>
    			<c:if test="${page.curPage!=page.lastPage }">
    				<a href="employeeServelet?mark=queryTime&curPage=${page.nextPage }">下一页</a>
    				<a href="employeeServelet?mark=queryTime&curPage=${page.lastPage }">尾页</a>
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
	function updateWork(obj){
		var seNumber=getChildNodes(obj.parentNode.parentNode)[0].innerHTML;
		window.open("/Extreme/employeeServelet?mark=showTime&seNumber="+seNumber,"修改员工","height=400,width=600");
	}
</script>
