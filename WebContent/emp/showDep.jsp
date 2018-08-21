<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<base href="<%=basePath%>">
	</head>
	<script src="/Extreme/util/echarts.js"></script>
	<script src="/Extreme/util/ajaxUtil.js"></script>
	<body onload="getSalary()">
		<div id="main" style="width: 600px;height:400px;"></div>
	</body>
</html>
<script type="text/javascript">
	//通过ajax给option加载x轴名字和数据
	function getSalary() {
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '部门信息',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : []
			},
			series : [ {
				name : '部门信息',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};
		myChart.on("click",function(params){
	    	window.open("employeeServelet?mark=queryByDepName&depName="+params.name+"&count="+params.data.value,"newWin","width=1000,height=600");
	    });
		ajax("get","employeeServelet","mark=queryDep",function(data){
	    	eval("var depArray="+data);
	    	for(var i=0;i<depArray.length;i++){
	    		var json={};
	    		json.value=depArray[i].number;
	    		json.name=depArray[i].depName;
	    		option.legend.data.push(json.name);
	    		option.series[0].data.push(json);
	    	}
	    	//使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
	    });
	}
</script>