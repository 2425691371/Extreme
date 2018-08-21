<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
        <td width="15" height="30"><img src="/Extreme/tab/images/tab_03.gif" width="15" height="30" /></td>
        <td background="/Extreme/tab/images/tab_05.gif"><img src="/Extreme/tab/images/311.gif" width="16" height="16" /> <span class="STYLE4">住户列表</span></td>
        <td background="/Extreme/tab/images/tab_05.gif"></td>
        <td width="14"><img src="/Extreme/tab/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="/Extreme/tab/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE" id="tab">
          <tr>
            <td width="20%" height="18" background="/Extreme/tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">序号</div></td>
            <td width="30%" height="18" background="/Extreme/tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">住户名</div></td>
            <td width="30%" height="18" background="/Extreme/tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">age</div></td>
            <td width="20%" height="18" background="/Extreme/tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">身份证号</div></td>
          </tr>
          <c:forEach items="${renList }" var="ren">
	          <tr>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#"></a>${ren.idNum }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#"></a>${ren.name }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#"></a>${ren.age }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center"><a href="#"></a>${ren.idCard }</div></td>
	          </tr>
          </c:forEach>
        </table></td>
        <td width="9" background="/Extreme/tab/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="/Extreme/tab/images/tab_20.gif" width="15" height="29" /></td>
        <td background="/Extreme/tab/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1"><jsp:include page="../pageUtil.jsp">
            	<jsp:param value="/Extreme/renShuAction?mark=queryShu&pname=${param.pname }&count=${param.count }" name="url"/>
            </jsp:include>共${page.count }条纪录，当前第${page.curPage }/${page.navCount }页，每页${page.pageRow }条纪录</span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="/Extreme/tab/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>