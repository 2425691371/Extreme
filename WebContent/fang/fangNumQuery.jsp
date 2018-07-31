<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>   
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <base href="<%=basePath%>">
<title>房产查询页面</title> 
</head>
<body onload="getHouse()">
			查询条件:<select id="ids" >
					<option value="1" selected="selected" >房间编号</option>
					<option value="2">房间结构</option>
					</select>
					<input type="text" value=""  id="tid" />
					<input type="button"  value="查询" onclick="getFang()" /><br/>
					<div id="mn"></div>
</body>
</html>
<script src="/Extreme/util/util.js"></script>
<script src="/Extreme/util/ajaxUtil.js"></script>
<script>
	var div=$("#mn");
	function getHouse(){
		ajax("get","/Extreme/fangAction","mark=query",function(data){
			eval("var hoseArray="+data);
			for(var i=0;i<hoseArray.length;i++){
				var div1=document.createElement("div");
				var div2=document.createElement("div");
				div2.innerHTML='<img src="/Extreme/houseImg/101.jpg" style="width: 200px" /><br/>';
				div2.style="width:200px;float: left;margin-left: 10px; border: 1px solid gray";
				div1. style='text-align: center;width:200px;float: left;margin-left: 10px';
				div1.innerHTML='房间地址:'+hoseArray[i].buildingId+'栋'+hoseArray[i].unitId+'单元'+hoseArray[i].fangId+'室<br/>'+
				'户型结构:'+hoseArray[i].roomCount+'室'+hoseArray[i].livingRoomCount+'厅'+hoseArray[i].kitchenCount+'厨'+hoseArray[i].toiletCount+'卫<br/><input type="button" value="详细信息" onclick="newWin('+hoseArray[i].hid+')"/>';
			}
		});
	}
function getFang(){
	var ids=$("#ids").value;
	var num=$("#tid").value;
	div.innerHTML="";
	ajax("post","/Extreme/fangAction","mark=queryById&ids="+ids+"&num="+num,function (data){
		eval("var hoseArray="+data);
		for(var i=0;i<hoseArray.length;i++){
			var div1=document.createElement("div");
			var div2=document.createElement("div");
			div2.innerHTML='<img src="/Extreme/houseImg/101.jpg" style="width:200px" /><br/>';
			div2.style="width:200px;float: left;margin-left: 10px; border: 1px solid gray";
			div1. style='text-align: center;width:200px;float: left;margin-left: 10px';
			div1.innerHTML='房间地址:'+hoseArray[i].buildingId+'栋'+hoseArray[i].unitId+'单元'+hoseArray[i].fangId+'室<br/>'+
			'户型结构:'+hoseArray[i].roomCount+'室'+hoseArray[i].livingRoomCount+'厅'+hoseArray[i].kitchenCount+'厨'+hoseArray[i].toiletCount+'卫<br/><input type="button" value="详细信息" onclick="newWin('+hoseArray[i].hid+')"/>';
			div.appendChild(div2);
			div2.appendChild(div1);
		}
	});
}
function newWin(hid){
	window.location="/Extreme/fangAction?mark=show&hid="+hid;
}
</script>