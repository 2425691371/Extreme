<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<script src="/Extreme/util/util.js"></script>
<script src="/Extreme/util/ajaxUtil.js"></script>
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
        <td width="15" height="30"><img src="/Extreme/admin/images/tab_03.gif" width="15" height="30" /></td>
        <td background="/Extreme/admin/images/tab_05.gif"><img src="/Extreme/admin/images/311.gif" width="16" height="16" /> <span class="STYLE4">管理员列表</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="全选" onclick="all1()" />
         <input type="button" value="全不选" onclick="unall()"/>
          <input type="button" value="反选" onclick="fanxuan()"/>
           <input type="button" value="删除选中项" onclick="dele()"/>
        </td>
        <td width="14"><img src="/Extreme/admin/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="/Extreme/admin/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
          <tr>
            <td width="6%" height="26" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">选择</div></td>
            <td width="8%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="24%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">账号</div></td>
            <td width="20%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">手机</div></td>
            <td width="21%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">邮箱</div></td>
            <td width="7%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">密码重置</div></td>
            <td width="7%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">编辑</div></td>
            <td width="7%" height="18" background="/Extreme/admin/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">删除</div></td>
          </tr>
          <c:forEach items="${empList}" var="i">
          <tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox11" type="checkbox" class="STYLE2" value="${i.uid}" />
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">${i.uid}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${i.uname}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${i.phone}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${i.emal}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="/Extreme/admin/images/037.gif" width="9" height="9" /></span><span class="STYLE0"> [</span><a href="adminServlet?mark=jumpuppwd&uid=${i.uid}&page=${page.curPage}">重置</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="/Extreme/admin/images/037.gif" width="9" height="9" /></span><span class="STYLE1"> [</span><a href="adminServlet?&mark=jumpupdata&uid=${i.uid}&page=${page.curPage}">编辑</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="/Extreme/admin/images/010.gif" width="9" height="9" /></span><span class="STYLE2"> </span><span class="STYLE1" >[</span><a href="javascript:void(0)" onclick="dele1(${i.uid})" >删除</a><span class="STYLE1">]</span></div></td>
          </tr>
          </c:forEach>
         <tr align="center">
    		<td colspan="8" background="/Extreme/admin/images/tab_05.gif">
          <c:if test="${page.curPage!=page.firstPage }">
					<a href="adminServlet?mark=goquerry&curPage=${page.firstPage }">首页</a>
					<a href="adminServlet?mark=goquerry&curPage=${page.prevPage }">上一页</a>
			</c:if>
			<c:forEach begin="${page.startNav }" end="${page.endNav }" var="i">
			 	<c:choose>
			 		<c:when test="${i==page.curPage}">
			 			<font color="red" id="fontmark">${i }</font>
			 		</c:when>
			 		<c:otherwise>
			 			<a href="adminServlet?mark=goquerry&curPage=${i }">${i }</a>
			 		</c:otherwise>
			 	</c:choose>
			</c:forEach>
			<c:if test="${page.curPage!=page.lastPage }">
				<a href="adminServlet?mark=goquerry&curPage=${page.nextPage }">下一页</a>
				<a href="adminServlet?mark=goquerry&curPage=${page.lastPage }">尾页</a>
			</c:if>
			</td>
			</tr>
        </table>
        </td>
        <td width="9" background="/Extreme/admin/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="/Extreme/admin/images/tab_20.gif" width="15" height="29" /></td>
        <td background="/Extreme/admin/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left" ><span class="STYLE1">共${page.count}条纪录，当前第${page.curPage}/${page.navCount}页，每页${page.pageRow}条纪录</span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="/Extreme/admin/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
<script>
function all1(){
		var cbs=$("checkbox11");
		for(var i=0;i<cbs.length;i++){
			cbs[i].checked=true;
		}
	}
function unall(){
	var cbs=$("checkbox11");
for(var i=0;i<cbs.length;i++){
	cbs[i].checked=false;
}
}
function fanxuan(){
	var cbs=$("checkbox11");
	for(var i=0;i<cbs.length;i++){
		cbs[i].checked=!cbs[i].checked;
	}
}
function dele(){
	var cbs=$("checkbox11");
	var cbsarr=[];
	for(var i=0;i<cbs.length;i++){
		if(cbs[i].checked===true){
			cbsarr.push(cbs[i].value);
		}		
	}
	var str = cbsarr.join();
	var yingcang=$("#fontmark").innerHTML;
	if(str===""){
	alert("未选中元素");
	}else{
		ajax("post","adminServlet","mark=del&data="+str,function(data){
			alert(data);
		},false);
	}
	location.reload(true);
}

function dele1(value){
if(value===""){
	alert("未选中元素");
	}else{
		ajax("post","adminServlet","mark=del&data="+value+",",function(data){
			alert(data);
		},false);
	}  
location.reload(true);
}
</script>
</html>
