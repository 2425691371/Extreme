<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/Extreme/util/My97DatePicker/WdatePicker.js"></script>
</head>
<body onload="lou()">
	<h4>缴费窗口</h4>
	${msg }
	<form action="/Extreme/xmServlet?mark=pay&id=${xm.zid }" method="post">
		<table style="border-collapse:collapse" border="1" bordercolor="green" width="500px">
			<tr>
				<td align="right">请选择用户住址：</td>
				<td><select onchange="dong(this.value)" id="lou">
						<option>请选择楼栋</option>
					</select>
					<select onchange="dan(this.value)" id="da">
						<option>请选择单元</option>
					</select>
					<select onchange="fa(this.value)" id="fang">
						<option>请选择房间</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">住户姓名：</td>
				<td><input type="text" name="name" id="yonghu" readonly/></td>
			</tr>
			<tr>
				<td align="right">缴费类别：</td>
				<td><input type="text" name="pname" value="${xm.pname }" readonly/></td>
			</tr>
			<tr>
				<td align="right">单价：</td>
				<td><input type="text" name="unitp" value="${xm.unitp }" readonly id="dj"/></td>
			</tr>
			<tr>
				<td align="right">单位：</td>
				<td><input type="text" name="unit" value="${xm.unit }" readonly/></td>
			</tr>
			<tr>
				<td align="right">购买数量：</td>
				<td><input type="text" name="number" onblur="test(this.value)"/></td>
			</tr>
			<tr>
				<td align="right">总金额：</td>
				<td><input type="text" name="price" id="zje" readonly/></td>
			</tr>
			<tr>
				<td align="right">购买日期：</td>
				<td><input type="text" onfocus="WdatePicker()" class="Wdate" /></td>
			</tr>
			<tr>
				<td align="right">购买方式：</td>
				<td><select name="ds">
					 <option value="1" ${xm.ds==1?"selected":"" }>代收</option>
					 <option value="0" ${xm.ds==0?"selected":"" }>不代收</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="缴费"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
<script src="/Extreme/util/util.js"></script>
<script src="/Extreme/util/ajaxUtil.js"></script>
<script>
	function test(Obj){
		var zje=$("#zje");
		if(Obj){
			var dj=$("#dj");
			var newdj=(dj.value).replace("元","");
			zje.value=newdj*Obj+"元";
		}else{
			zje.value="";
		}
	}
	function lou(){
		var lou=$("#lou");
		ajax("get","/Extreme/xmServlet","mark=lou",function(data){
			eval("var fangArray="+data);
			for (var i = 0; i < fangArray.length; i++) {
				var newOpt=new Option(fangArray[i].lou,fangArray[i].lid);
				lou.appendChild(newOpt);
			}
		})
	}
	var fang=$("#fang");
	function dong(Obj){
		if(Obj){
			var dan=$("#da");
			dan.innerHTML="<option>请选择单元</option>";
			fang.options.length=1;
			ajax("get","/Extreme/xmServlet","mark=dong&dan="+Obj,function(data){
				eval("var fangArray="+data);
				for (var i = 0; i < fangArray.length; i++) {
					var newOpt=new Option(fangArray[i].dan,fangArray[i].did);
					dan.appendChild(newOpt);
				}
			})
		}
	}
	function dan(Obj){
		fang.innerHTML="<option>请选择房间</option>";
		ajax("get","/Extreme/xmServlet","mark=fang&fang="+Obj,function(data){
			eval("var fangArray="+data);
			for (var i = 0; i < fangArray.length; i++) {
				var newOpt=new Option(fangArray[i].fang,fangArray[i].fid);
				fang.appendChild(newOpt);
			}
		})
	}
	function fa(Obj){
		var yonghu=$("#yonghu");
		ajax("get","/Extreme/xmServlet","mark=yonghu&fid="+Obj,function(data){
			eval("var fangArray="+data);
			yonghu.value=fangArray.yonghu;
		})
	}
</script>