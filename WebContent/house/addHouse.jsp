<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		.cs{
			border-bottom:1px solid green
		}
		table td{
				border-left:1px solid green;border-bottom:1px solid green
			}
	</style>
</head>

<script src="/Extreme/house/date/My97DatePicker/WdatePicker.js"></script>
<script src="/Extreme/util/util.js"></script>
<script src="/Extreme/util/ajaxUtil.js"></script>




<body onload="threeOption(),studySpan()" >
	 <form action="residentTest?mark=add" method="post" enctype="multipart/form-data" > 
	
		<table   width="800px"  style="border:1px solid green" align="center">
			<tr >
				<th class="cs" align="right">住户名:</th>
				<td class="cs">
					<input type="hidden" name="idNum"  />
					<input type="text" name="name" onblur="spanTest(this.value)" />
					<span style="color:red" id="id1"></span>
				</td>
			</tr>
			<tr >
				<th class="cs" align="right">性别:</th>
				<td class="cs">
					<input type="radio" name="gender" value="1" checked />男
					<input type="radio" name="gender" value="0" />女
				</td>
			</tr>
			<tr >
				<th class="cs" align="right">年龄:</th>
				<td class="cs">
					<input type="text" name="age" onblur="spanAge(this.value)"/>
					<span style="color:red" id="id2"></span>
				</td>
			</tr>
			<tr >
				<th class="cs" align="right">身份证号:</th>
				<td class="cs">
					<input type="text" name="idCard" onblur="spanCard(this.value)"/>
					<span style="color:red" id="id3"></span>
				</td>
			</tr>
			<tr >
				<th class="cs" align="right">学历:</th>
				<td class="cs">
					<select id="selec" name="study">
						<option value="">-请选择-</option>
					</select>
				</td>
				
				<!-- 动态添加option
             select对象.options.add(new Option(text,value));
         	    动态删除option
             	清空所有的option
             select.options.length=0;
             	删除某一个option
             select.remove(index);
             */ -->
            
				
			</tr>
			<tr >
				<th class="cs" align="right">邮箱:</th>
				<td class="cs">
					<input type="text" name="email"  onblur="spanEmail(this.value)" />
					<span style="color:red" id="id4"></span>
				</td>
			</tr>
			<tr >
				<th class="cs" align="right">所租的房子:</th>
				<td class="cs">
				<select id="twower" onchange="elementOption(this.value)" name="sect1" >
					<option value="">-请选择楼栋-</option>
				</select>
				<select id="unit" onchange="roomOption(this.value)" name="sect2" >
					<option value="">-请选择单元-</option>
				</select>
				<select id="room" name="sect3" >
					<option value="">-请选择房间-</option>
				</select>
				</td>
				
			</tr>
			<tr >
				<th class="cs" align="right">手机号:</th>
				<td class="cs" >
					<input type="text" name="phone" onblur="spanPhone(this.value)" />
					<span style="color:red" id="id5"></span>
				</td>
			</tr>
			<tr>
				<th class="cs" align="right">合同有效期:</th>
				<td class="cs">
					<input type="text" onfocus="WdatePicker()" class="Wdate" name="startdate" onblur="spanDate(this.value)" />
					<input type="text" onfocus="WdatePicker()" class="Wdate" name="enddate" onblur="spanDate(this.value)" />
					<span style="color:red" id="id6"></span>
				</td>
			</tr>
			<tr >
				<th class="cs" align="right" >住户照片:</th>
				<td class="cs">
					<input type="file" name="photo" />
				</td>
			</tr>
			<tr align="right">
				<td >
					<input type="submit" value="添加用户"  />
				</td>
				<td align="left">
				<input type="reset"  value="重置" />
				</td>
			</tr>
		</table>
		
	</form>
</body>

<script>
		<!-- 表单验证-->
	function spanTest(spanVal){
		var id1=$("#id1");
		 var rg= /^[\u4e00-\u9fa5]{2,4}$|^[a-zA-Z]{1,30}$/gi;
		 if(!spanVal){
			 id1.innerHTML="不能为空";
			 return false;
		 }else if(!rg.test(spanVal)){
			 id1.innerHTML="只能输入2-4个中文或1-30英文";
			 return false;
		 }else{
			 id1.innerHTML="";
		 }
	}
	
	function spanAge(ageVal){
		var id2=$("#id2");
		var rg=/120|((1[0-1]|\d)?\d)/;
		if(!ageVal){
			id2.innerHTML="不能为空";
			return false;
		}else if(!rg.test(ageVal)){
			id2.innerHTML="请输入正确的格式";
			return false;
		}else{
			id2.innerHTML="";
		}
	}
	
	function spanCard(cardVal){
		var id3=$("#id3");
		var rg=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if(!cardVal){
			id3.innerHTML="不能为空";
			return false;
		}else if(!rg.test(cardVal)){
			id3.innerHTML="请输入18位正确格式身份证";
			return false;
		}else {
			id3.innerHTML="";
		}
	}
	
	function spanEmail(emailVal){
		var id4=$("#id4");
		var rg=/^\w+@[a-z0-9]{1,10}(-[0-9a-z]{1,10})?[.](com|cn|org|net)(\.cn)?$/ig;
		if(!emailVal){
			id4.innerHTML="不能为空";
			return false;
		}else if(!rg.test(emailVal)){
			id4.innerHTML="邮箱格式不正确";
			return false;
		}else{
			id4.innerHTML="";
		}
	}
	
	function spanPhone(phoneVal){
		var id5=$("#id5");
		var rg=/^1\d{10}$/;
		if(!phoneVal){
			id5.innerHTML="不能为空";
			return false;
		}else if(!rg.test(phoneVal)){
			id5.innerHTML="请输入11位正确数字";
			return false;
		}else{
			id5.innerHTML="";
		}
		
	}
	
	function spanDate(dateVal){
		id6=$("#id6");
		if(!dateVal){
			id6.innerHTML="不能为空";
			return false;
		}else{
			id6.innerHTML="";
		}
	}
	
	//获取学历 
	function studySpan(){
		var selec=$("#selec");
		ajax("post","residentTest","mark=studys",function(data){
			eval("var selecArray="+data);
			for(var i=0;i<selecArray.length;i++){
				selec.options.add(new Option(selecArray[i],selecArray[i]));
			}
		})
		
	}
	
	//三级连动 楼
	
		var twower=$("#twower");
		var unit=$("#unit");
		var room=$("#room");
	function threeOption(){
		ajax("get","residentTest","mark=tower",function(data){
			eval("var towerArray="+data);
			for(var i=0;i<towerArray.length;i++){
				twower.options.add(new Option(towerArray[i].buildingld,towerArray[i].buildingld));
			}
		})
	}
	
	//三级联动 单元
	function elementOption(unitVal){
		unit.options.length=1;
		room.options.length=1;
		if(unitVal){
		ajax("get","residentTest","mark=unit&unitVal="+unitVal,function(data){
			eval("var unitArray="+data);
			for(var i=0;i<unitArray.length;i++){
				unit.options.add(new Option(unitArray[i].unitId,unitArray[i].unitId));
			}
		})
		}
	}
	//三级联动 房间号
	function roomOption(roomVal){
		room.options.length=1;
		if(roomVal){
		ajax("get","residentTest","mark=room&roomVal="+roomVal,function(data){
			eval("var roomArray="+data);
			for(var i=0;i<roomArray.length;i++){
				room.options.add(new Option(roomArray[i].fangId,roomArray[i].fangId));
				
			}
		})
		}
	}
	
</script>
</html>


