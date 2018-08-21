<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
-->
</style>


	<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
	<script type="text/javascript" src="js/dtree.js"></script>
	<script type="text/javascript">

		d = new dTree('d');
		d.add(0,-1,'尚学堂物业系统');
		d.add(1,0,'住户管理');
		d.add(2,1,'住户列表','/Extreme/residentTest?mark=query','住户列表','right');
		d.add(3,1,'住户添加','/Extreme/house/addHouse.jsp','住户添加','right');
		d.add(4,1,'年龄分析','/Extreme/house/ageHousejsp.jsp','住户列表','right');
		d.add(5,1,'学历分析','/Extreme/house/studyHouse.jsp','','right');
		d.add(6,1,'合同导出','/Extreme/residentTest?mark=downEmp','','right');
		d.add(10,0,'设备管理');
		d.add(11,10,'设备列表','/Extreme/applyServlet?mark=query','','right');
		d.add(12,10,'设备添加','/Extreme/ApplyJsp/addApply.jsp','','right');
		d.add(17,0,'缴费管理');
		d.add(18,17,'缴费项目','/Extreme/xmServlet?mark=query','','right');
		d.add(19,17,'缴费数据查询','/Extreme/renShuAction?mark=danZi&pname=取暖费','','right');
		d.add(20,17,'缴费人数分析','/Extreme/bcc/bzt.jsp','','right');
		d.add(21,17,'缴费费用分析','/Extreme/bcc/zzt.jsp','','right');
		d.add(22,0,'车辆管理');
		d.add(23,22,'车辆查询','/Extreme/car/carQuery.jsp','','right');
		d.add(24,22,'车辆添加','/Extreme/car/carAdd.jsp','','right');
		d.add(36,0,'人事管理');
		d.add(37,36,'员工信息','/Extreme/employeeServelet?mark=query','','right');
		d.add(38,36,'工资信息','/Extreme/emp/showSalary.jsp','','right');
		d.add(39,36,'部门信息','/Extreme/emp/showDep.jsp','','right');
		d.add(40,36,'考勤信息','/Extreme/employeeServelet?mark=queryTime','','right');
		d.add(33,0,'房产管理');
		d.add(34,33,'房产查询','/Extreme/fang/fangNumQuery.jsp','','right');
		d.add(35,33,'房产变更','/Extreme/fang/showFang.jsp','','right');
		d.add(41,0,'系统管理');
		d.add(42,41,'添加管理员','/Extreme/admin/addadmin.jsp','','right');
		d.add(43,41,'管理员列表','adminServlet?mark=goquerry','','right');
		d.add(44,41,'修改本账户密码','adminServlet?mark=jump','','right');
		d.add(45,41,'备份数据','adminServlet?mark=beifeng','','right');
		d.add(46,41,'还原数据','adminServlet?mark=querrydata','','right');

		window.onload=function(){
			document.getElementById("menu").innerHTML=d;
		}

	</script>
</head>

<body>
<table width="173" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td style="width:4px;" align="center" valign="top" background="images/main_20.gif"></td>
    <td width="169" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="20" background="images/main_11.gif">&nbsp;</td>
      </tr>
      <tr>
        <td> <div id="menu"></div> </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
