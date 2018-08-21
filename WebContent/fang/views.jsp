<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房屋详细信息</title>
</head>
<body>
	 <table border="1px"  width="400" align="center">
	 	<tr>
	 		<th><img  src="/Extreme/houseImg/101.jpg" style="width:300" ></th>
	 	</tr>
		<c:forEach items="${house }" var="h">
			<tr>
				<td align="right" >房间编号:</td>
				<td align="left">${h.hid }</td>
			</tr>
			<tr>
				<td align="right">楼栋号:</td>
				<td align="left">${h.buildingId }</td>
			</tr>
			<tr>
				<td align="right">单元号:</td>
				<td align="left">${h.unitId }</td>
			</tr>
			<tr>
				<td align="right">房间号:</td>
				<td align="left">${h.fangId }</td>
			</tr>
			<tr>
				<td align="right">户型图编号</td>
				<td align="left">${h.floorPlanId }</td>
			</tr>
			<tr>
				<td align="right">卧室:</td>
				<td align="left">${h.roomCount }</td>
			</tr>
			<tr>
				<td align="right">客厅:</td>
				<td align="left">${h.livingRoomCount }</td>
			</tr>
			<tr>
				<td align="right">厨房:</td>
				<td align="left">${h.kitchenCount }</td>
			</tr>
			<tr>
				<td align="right">卫生间:</td>
				<td align="left">${h.toiletCount }</td>
			</tr>
			<tr>
				<td align="right">房产变更编号:</td>
				<td align="left">${h.changeId }</td>
			</tr>
			<tr>
				<td align="right">变更时间:</td>
				<td align="left">${h.time }</td>
			</tr>
			<tr>
				<td align="right">变更前业主:</td>
				<td align="left">${h.name }</td>
			</tr>
			<tr>
              <th colspan="2"><a href="/Extreme/fangAction?mark=queryIds&hid=${h.hid }">变更</a></th>
            </tr>
		</c:forEach>
	</table> 
</body>
</html>