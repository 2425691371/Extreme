<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>房产变更页面</title>
		<base href="<%=basePath%>">
		 <link href="/Extreme/fang/assets/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body >
	<h3 style="text-align:center">房产变更页面</h3>
<form class="form-horizontal" action="/Extreme/fangAction?mark=change" method="post">
    <div class="form-group">
        <label class="col-sm-4 control-label">房间编号</label>
        <div class="col-sm-6">
            <input type="text" name="hid" class="form-control"  value="${h.hid }"  readonly="readonly"  placeholder="房间编号">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">楼栋号</label>
        <div class="col-sm-6">
            <input name="buildingId" type="text" class="form-control" readonly="readonly" value="${h.buildingId}" placeholder="楼栋号">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">单元号</label>
        <div class="col-sm-6">
            <input name="unitId" type="text" class="form-control" readonly="readonly" value="${h.unitId }" placeholder="单元号">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">房间号</label>
        <div class="col-sm-6">
            <input name="fangId" type="text" class="form-control" readonly="readonly" value="${h.fangId }" placeholder="房间号">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">户型图编号</label>
        <div class="col-sm-6">
            <input name="floorPlanId" type="text" class="form-control" readonly="readonly" value="${h.floorPlanId }" placeholder="户型图编号">
        </div>
    </div> <div class="form-group">
    <label  class="col-sm-4 control-label">卧室</label>
    <div class="col-sm-6">
        <input name="roomCount" type="text" class="form-control" readonly="readonly" value="${h.roomCount}" placeholder="卧室">
    </div>
</div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">客厅</label>
        <div class="col-sm-6">
            <input name="livingRoomCount" type="text" class="form-control" readonly="readonly" value="${h.livingRoomCount }" placeholder="客厅">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">厨房</label>
        <div class="col-sm-6">
            <input name="kitchenCount" type="text" class="form-control" readonly="readonly"  value="${h.kitchenCount}" placeholder="厨房">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">卫生间</label>
        <div class="col-sm-6">
            <input name="toiletCount" type="text" class="form-control" readonly="readonly" value="${h.toiletCount }" placeholder="卫生间">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">房产变更记录编号</label>
        <div class="col-sm-6">
            <input name="changeId" type="text" class="form-control"  value="${h.changeId }" placeholder="房产变更记录编号">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">房产变更时间</label>
        <div class="col-sm-6">
            <!--  <input name="time" type="text" class="form-control"  value="${h.time }" placeholder="房产变更时间">-->
            <input type="text" onfocus="WdatePicker()" class="form-control" name="time"   value="${h.time }" placeholder="房产变更时间" />
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-4 control-label">变更时业主</label>
        <div class="col-sm-6">
            <input name="name" type="text" class="form-control"   value="${h.name}" placeholder="变更时业主">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-6">
            <button type="submit" class="btn btn-default">确认变更</button>
        </div>
    </div>
</form>
<script src="/Extreme/util/My97DatePicker/WdatePicker.js"></script>
	</body>
</html>